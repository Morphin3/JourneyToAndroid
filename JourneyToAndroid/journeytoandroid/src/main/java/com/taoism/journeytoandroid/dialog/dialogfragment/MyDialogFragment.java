package com.taoism.journeytoandroid.dialog.dialogfragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-11-03
 * Time: 09:45
 * Author: cf
 * -----------------------------
 */
public class MyDialogFragment extends DialogFragment {

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullScreenDialogStyle);
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        View view = inflater.inflate(R.layout.dialog_my_dialog_fragment, null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.FullScreenDialogStyle);
//        builder.setView(view);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//
////        Window window = alertDialog.getWindow();
////        WindowManager.LayoutParams Params = alertDialog.getWindow().getAttributes();
////        Params.width = WindowManager.LayoutParams.MATCH_PARENT;
////        Params.height = WindowManager.LayoutParams.MATCH_PARENT;
////        // 默认居中
////        Params.gravity = Gravity.CENTER;
////
//////        Drawable d = new ColorDrawable(ResourcesUtil.getColor(R.color.text_black_trans_40));
//////        d.setAlpha(130);
//////        window.setBackgroundDrawable(d);
////        window.setAttributes(Params);
//        return alertDialog;
//
//
////        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FullScreenDialogStyle);
////        AlertDialog alertDialog = builder.create();
////        alertDialog.setCancelable(true);
////        alertDialog.setContentView(view);
////        return alertDialog;
//
//
////        Dialog dialog = new Dialog(getActivity(),R.style.FullScreenDialogStyle);
////        dialog.setCancelable(false);
////        dialog.setContentView(view);
////
////        return dialog;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if(dialog != null){
//            int width = ViewGroup.LayoutParams.MATCH_PARENT;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            dialog.getWindow().setLayout(width,height);
//        }
//
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
//            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_my_dialog_fragment, container, false);
        return root;
    }

}
