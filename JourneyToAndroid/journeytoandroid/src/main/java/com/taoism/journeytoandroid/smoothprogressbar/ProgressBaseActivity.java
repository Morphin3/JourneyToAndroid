package com.taoism.journeytoandroid.smoothprogressbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.taoism.journeytoandroid.R;

/**
 * Created by chenfei on 14-11-27.
 */
public class ProgressBaseActivity extends Activity {
	private ProgressDialog mProgressDialog;

	private AlertDialog mAlertDialog;

	public static AlertDialog createDialog(Context context, int layout, int animation, boolean showSoftInput) {
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.setContentView(layout);
		Window window = dialog.getWindow();
		if (showSoftInput) {
			window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		}
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		lp.gravity = Gravity.CENTER;
		window.setAttributes(lp);
		window.setWindowAnimations(animation);

		return dialog;
	}


	/**
	 * 通讯提示框
	 *
	 * @param showStatus
	 */
	public void showSmoothProgressDialog(boolean showStatus) {
		if (showStatus) {
			if (mAlertDialog == null) {
				mAlertDialog = new AlertDialog.Builder(this).create();
				mAlertDialog.show();
				mAlertDialog.setCancelable(true);
				mAlertDialog.setContentView(R.layout.dialog_smooth_progress_bar);
				Window window = mAlertDialog.getWindow();
				WindowManager.LayoutParams lp = window.getAttributes();
				lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
				lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
				lp.gravity = Gravity.CENTER_HORIZONTAL;
				window.setAttributes(lp);
				window.setWindowAnimations(R.style.DialogInOutAnimation);

				LinearLayout ll_dialog = (LinearLayout) window.findViewById(R.id.ll_dialog);
				ll_dialog.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mAlertDialog.dismiss();
					}
				});
			} else {
				mAlertDialog.show();
			}
		} else {
			mAlertDialog.dismiss();
		}
	}


	/**
	 * 通讯提示框
	 *
	 * @param msg
	 * @param showStatus
	 */
	public void showProgressDialog(String msg, boolean showStatus) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setCancelable(true);
		}

		if (showStatus) {
			mProgressDialog.setMessage(msg);
			mProgressDialog.show();
		} else {
			mProgressDialog.dismiss();
		}
	}
}
