package com.taoism.journeytoandroid.utils.viewsizeutil;

import android.view.View;

/**
 * Date: 2015-07-08
 * Time: 13:13
 * Author: cf
 * -----------------------------
 */
public class ViewSizeUtil {
    //    --------------------------   获取控件宽度  ----------------------------


    public static int getViewWidth(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
        return width;
    }

    public static int getViewHeight(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = view.getMeasuredHeight();
        return height;
    }

    public static int[] getViewSize(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        int[] size = new int[]{width, height};
        return size;

    }


//    public static void setFrame(View view, int leftMargin, int topMargin, int width, int height) {
//        ViewGroup.LayoutParams lp = view.getLayoutParams();
//        if (lp != null) {
//            if (lp instanceof FrameLayout.LayoutParams) {
//                lp.height = height;
//                lp.width = width;
////				view.setX(leftMargin);
////				view.setY(topMargin);
//                ((FrameLayout.LayoutParams) lp).leftMargin = leftMargin;
//                ((FrameLayout.LayoutParams) lp).topMargin = topMargin;
//                view.setLayoutParams(lp);
//            } else if (lp instanceof LinearLayout.LayoutParams) {
//                lp.height = height;
//                lp.width = width;
//                view.setLayoutParams(lp);
//            } else if (lp instanceof AbsListView.LayoutParams) {
//                lp.width = width;
//                lp.height = height;
//                view.setLayoutParams(lp);
//            } else if (lp instanceof ViewPager.LayoutParams) {
//                lp.width = width;
//                lp.height = height;
//                view.setLayoutParams(lp);
//            } else {
//                ColorUILog.LOGE("setFrame,the parent should not others");
//            }
//        } else {
//
//            // root layout
//            lp = new FrameLayout.LayoutParams(width, height);
//            lp.height = height;
//            lp.width = width;
//            view.setLayoutParams(lp);
//        }
//    }
//
//
//    public static String getViewSize(View v, int height, int width) {
//        if (v instanceof SwitchButton) {
////				double radio = ScreenUtils.getRadio(v.getContext());
//            int measureHeight = ScreenUtils.dip2px(v.getContext(), 36);
//            int measureWidth = ScreenUtils.dip2px(v.getContext(), 58);
////				return "58_36";
//            return measureWidth + "_" + measureHeight;
//        } else if (v instanceof TextView || v instanceof Button) {
////			double radio = ScreenUtils.getRadio(v.getContext());
////			double dHeight = height;
////			double dWidth = width;
//            int heightMs = 0;
//            if (height == 5000) {
//
//                heightMs = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            } else {
////				dHeight =ScreenUtils.dip2px(v.getContext(),height) ;
//                //viewport
////						ScreenUtils.getRadio(v.getContext()) * height;
//                heightMs = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
//            }
//            int widthMs = 0;
//            if (width == 5000) {
//                widthMs = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//            } else {
////				dWidth = ScreenUtils.getRadio(v.getContext()) * width;
//                widthMs = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
//            }
//            v.setPadding(0, 0, 0, 0);
//            ((TextView) v).setTextScaleX(1.0f);
//            ((TextView) v).measure(widthMs, heightMs);
//            int measureHeight = v.getMeasuredHeight();
//            int measureWidth = v.getMeasuredWidth();
////viewport
////			return  (measureWidth / radio) + "_" +  (measureHeight / radio );
//            return measureWidth + "_" + measureHeight;
//        } else if (v instanceof ImageView) {
//            if (v instanceof CachedLuaThumbImageView) {
//                CachedLuaThumbImageView luaThumbImageView = (CachedLuaThumbImageView) v;
//                return luaThumbImageView.getPreferSize();
//            }
//
//            return "10_10";
//        } else {
//            int heightMs = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);
//            int widthMs = MeasureSpec.makeMeasureSpec(width, MeasureSpec.UNSPECIFIED);
//            v.measure(widthMs, heightMs);
//            return v.getMeasuredWidth() + "_" + v.getMeasuredHeight();
//        }
//    }
}
