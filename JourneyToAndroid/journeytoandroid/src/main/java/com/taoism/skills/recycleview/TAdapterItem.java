package com.taoism.skills.recycleview;

/**
 * Created by zhengwen on 15-6-10.
 */
public interface TAdapterItem {
    public int getViewType();
    public int getId();
    public Object getDataModel();
}
