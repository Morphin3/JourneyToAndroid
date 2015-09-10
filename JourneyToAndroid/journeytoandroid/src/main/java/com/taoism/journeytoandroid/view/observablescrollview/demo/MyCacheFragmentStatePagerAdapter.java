package com.taoism.journeytoandroid.view.observablescrollview.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.taoism.journeytoandroid.view.observablescrollview.FlexibleSpaceWithImageBaseFragment;
import com.taoism.journeytoandroid.view.observablescrollview.FlexibleSpaceWithImageRecyclerViewFragment;

/**
 * Date: 2015-09-09
 * Time: 10:37
 * Author: cf
 * -----------------------------
 */
public class MyCacheFragmentStatePagerAdapter extends CacheFragmentStatePagerAdapter {

    private static final String[] TITLES = new String[]{"全部", "话题", "插画", "COS", "文章"};

    private int mScrollY;

    public void setScrollY(int scrollY) {
        mScrollY = scrollY;
    }


    public MyCacheFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    protected Fragment createItem(int position) {
        FlexibleSpaceWithImageBaseFragment f;
        f = new FlexibleSpaceWithImageRecyclerViewFragment();
        f.setArguments(mScrollY);
        return f;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
