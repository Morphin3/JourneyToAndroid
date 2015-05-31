package com.taoism.journeytoandroid.photogallery;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2015-05-30
 * Time: 18:24
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class ThumbnailDownLoader<Token> extends HandlerThread {

    private static final String TAG = "ThumbnailDownloader";

    private static final int MESSAGE_DOWNLOAD = 0;

    Handler mHandler;

    Map<Token, String> requestMap = Collections.synchronizedMap(new HashMap<Token, String>());

    Handler mResponseHandler;

    Listener<Token> mListener;

    public interface Listener<Token> {
        void onThumbnailDownloaded(Token token, Bitmap thumbnail);
    }

    public void setListener(Listener<Token> listener) {
        mListener = listener;
    }

//    public ThumbnailDownLoader(){
//        super(TAG);
//    }

    public ThumbnailDownLoader(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    @SuppressWarnings("unchecked")
                    Token token = (Token) msg.obj;
                    Log.i(TAG, "Got a request for url" + requestMap.get(token));
                    handlerRequest(token);
                }
            }
        };
    }

    public void queenThumbnail(Token token, String url) {
        Log.i(TAG, "Got an URL: " + url);
        requestMap.put(token, url);

        mHandler
                .obtainMessage(MESSAGE_DOWNLOAD, token)
                .sendToTarget();
    }

    private void handlerRequest(final Token token) {
        try {
            final String url = requestMap.get(token);
            if (url == null) {
                return;
            }

            byte[] bitmapBytes = new FlickrFetchr().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            Log.i(TAG, "Bitmap created");

            //以Runnable的形式实现
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (requestMap.get(token) != url)
                        return;
                    requestMap.remove(token);
                    mListener.onThumbnailDownloaded(token, bitmap);
                }

            });


            //自己以sendMessage()的形式实现
//            Message message=mResponseHandler.obtainMessage();
//            Bundle bundle=new Bundle();
//            bundle.putParcelable("bitmap",bitmap);
//            message.setData(bundle);
//            message.obj=token;
//            mResponseHandler.sendMessage(message);


        } catch (IOException ioe) {
            Log.e(TAG, "Error downloading image", ioe);
        }

    }

    public void clearQueue() {
        mHandler.removeMessages(MESSAGE_DOWNLOAD);
        requestMap.clear();
    }

}
