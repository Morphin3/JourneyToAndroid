package com.taoism.journeytoandroid.customview.lyricview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-11-09
 * Time: 15:53
 * Author: cf
 * -----------------------------
 */

public class LrcViewDemoAcitivty extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrc_view_demo);

        LyricTextView lyricTextView = (LyricTextView) findViewById(R.id.lyric_text_view);

        SpannableString msp = null;
        //创建一个 SpannableString对象
        msp = new SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合/bot");

        //设置字体前景色
        msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 3, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);  //设置前景色为洋红色

        lyricTextView.setText(msp);

    }

}
