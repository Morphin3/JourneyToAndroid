package com.taoism.journeytoandroid.customview.copyabletextview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Date: 2015-07-09
 * Time: 22:28
 * Author: cf
 * -----------------------------
 */
public class CopyableTextView extends TextView implements View.OnLongClickListener {


    private boolean mIsCopyable;


    public CopyableTextView(Context context) {
        super(context);
        init();
    }

    public CopyableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CopyableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CopyableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        if(mIsCopyable){
            Toast.makeText(v.getContext(),"长按复制",Toast.LENGTH_SHORT).show();
//            String stringYouExtracted = getText().toString();
//            int startIndex = getSelectionStart();
//            int endIndex = getSelectionEnd();
//            stringYouExtracted = stringYouExtracted.substring(startIndex, endIndex);

            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(getText());
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", getText());
                clipboard.setPrimaryClip(clip);
            }

        }else{
            Toast.makeText(v.getContext(),"作者已禁止复制文本",Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    public boolean isCopyable() {
        return mIsCopyable;
    }

    public void setIsCopyable(boolean isCopyable) {
        mIsCopyable = isCopyable;
    }
}



//    public CopyableTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    // 长按弹出文本选择框menu的关键方法：可以选择复制、剪切等等功能，视该textview的具体实现而定
//// 如果希望不弹出这个menu界面，只要把这个方法返回空就ok
//    @Override
//    protected MovementMethod getDefaultMovementMethod() {
//        // TODO Auto-generated method stub
//        return super.getDefaultMovementMethod();
//    }
//
//    // 点击menu中的选定item的具体处理方法，捕捉点击文本复制、剪切等按钮的动作
//// 如果要在点击复制按钮之后取消该textview的cursor可见性的具体监听写在这里
//    @Override
//    public boolean onTextContextMenuItem(int id) {
//        setCursorVisible(true);
//        boolean flag;
//        if (id != android.R.id.switchInputMethod) {
//            flag = super.onTextContextMenuItem(id);
//        } else {
//            setCursorVisible(false);
//            return false;
//        }
//        if (id == android.R.id.copy) {
//            setCursorVisible(false);
//            cursorStart = -1;
//        }
//        return flag;
//    }
//
//    @Override
//    protected void onCreateContextMenu(ContextMenu menu) {
//        super.onCreateContextMenu(menu);
//        if (isInputMethodTarget()) {
//            menu.removeItem(android.R.id.switchInputMethod);
//        }
//    }
//
//// textview的点击捕捉
//// 如果双击textview选中了具体文字，则使cursor可见
//int cursorStart = -1;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        boolean flag = super.onTouchEvent(event);
//        if (event.getAction() == MotionEvent.ACTION_DOWN && hasSelection()) {
//            if (cursorStart == -1) {// 由于点击选中文字后，再点击其他位置，第一次点击时显示的hasSelection依然为true，这样一来cursor会依然还在，为了避免这种情况，我这里多对selectionStart进行了一次验证
//                setCursorVisible(true);
//                cursorStart = getSelectionStart();
//            } else {
//                setCursorVisible(false);
//                cursorStart = -1;
//            }
//        }
//        return flag;
//    }
//
//    // 当按返回键取消文字复制时，使cursor再次不可见
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        boolean flag = super.onKeyDown(keyCode, event);
//
//        setCursorVisible(false);
//        cursorStart = -1;
//        return flag;
//    }
//
////    @Override
////    protected boolean getDefaultEditable() {
////        return false;
////    }



//    public CopyableTextView(Context context) {
//        super(context);
//    }
//
//    public CopyableTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public CopyableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public CopyableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    public void setCopyable(boolean copyable){
//        if(copyable){
//            setFocusableInTouchMode(true);
//            setFocusable(true);
//            setClickable(true);
//            setLongClickable(true);
//            setMovementMethod(  ArrowKeyMovementMethod.getInstance());
//            setText(getText(), BufferType.SPANNABLE);
//        }
//    }
