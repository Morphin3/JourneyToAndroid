package com.taoism.journeytoandroid.video;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.os.Build;

/**
 * Date: 2017-04-27
 * Time: 12:50
 * Author: cf
 * -----------------------------
 */

public class VideoUtil {

    public static final String TAG = VideoUtil.class.getSimpleName();

    /**
     * 获取视频截图
     */
    public static Bitmap getScaleVideoFrame(String path, long position,
                                            int height, int width) {
        Bitmap bitmap = getVideoFrame(position, path);
        bitmap = scaleCrop(bitmap, height, width, false);
        return bitmap;
    }

    /**
     * 获取视频截图
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    public static Bitmap getVideoFrame(long position, String filePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        Bitmap bitmap = getVideoFrame(position, filePath, retriever);
        try {
            retriever.release();
        } catch (Exception e) {
        }
        return bitmap;
    }

    /**
     * 获取视频截图,要自己释放retriever
     *
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    public static Bitmap getVideoFrame(long position, String filePath, MediaMetadataRetriever retriever) {

        Bitmap bitmap = null;
        try {
            retriever.setDataSource(filePath);
            if (position <= 0) {
                bitmap = retriever.getFrameAtTime(MediaMetadataRetriever.OPTION_NEXT_SYNC);
                if (bitmap == null) {
                    bitmap = retriever.getFrameAtTime(MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                }
                if (bitmap == null) {
                    bitmap = retriever.getFrameAtTime(MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
                }
            } else {
                bitmap = retriever.getFrameAtTime(position, MediaMetadataRetriever.OPTION_NEXT_SYNC);
                if (bitmap == null) {
                    bitmap = retriever.getFrameAtTime(position, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                }
                if (bitmap == null) {
                    bitmap = retriever.getFrameAtTime(position, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
                }
            }

        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        }

        return bitmap;
    }


    public static Bitmap scaleCrop(Bitmap source, int newHeight, int newWidth,
                                   boolean center) {
        if (source == null) {
            return source;
        }
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width,
        // respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) (newWidth / 10) / sourceWidth;
        float yScale = (float) (newHeight / 10) / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = 0;
        if (center) {
            top = (newHeight - scaledHeight) / 2;
        }

        // The target rectangle for the new, scaled version of the source bitmap
        // will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top
                + scaledHeight);

        // Finally, we create a new bitmap of the specified size and draw our
        // new,
        // scaled bitmap onto it.
        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight,
                getConfig(source));
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;


//        Matrix matrix = new Matrix();
//        matrix.postScale(scale, scale);
//
//        Bitmap newBm = Bitmap.createBitmap(source, 0, 0, sourceWidth, sourceHeight, matrix,
//                true);
//        if (!source.isRecycled()) {
//            source.recycle();
//            source = null;
//        }
//        return newBm;
    }


    public static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return config;
    }

}
