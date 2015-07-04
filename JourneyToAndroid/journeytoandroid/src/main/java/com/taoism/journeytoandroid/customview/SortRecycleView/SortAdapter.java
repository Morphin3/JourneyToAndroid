package com.taoism.journeytoandroid.customview.sortrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.customview.sortrecycleview.model.SortModel;

import java.util.List;

/**
 * Date: 2015-07-04
 * Time: 19:15
 * Author: cf
 * -----------------------------
 */
public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> implements SectionIndexer {

    private List<SortModel> mSortModels = null;
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public SortAdapter(Context context,List<SortModel> sortModels) {
        mContext = context;
        mSortModels = sortModels;
        mLayoutInflater=LayoutInflater.from(context);

    }


    public void updateListView(List<SortModel> sortModels){
        this.mSortModels = sortModels;
        notifyDataSetChanged();
    }

    @Override
    public SortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SortViewHolder(mLayoutInflater.inflate(R.layout.item_sort,parent,false));
    }

    @Override
    public void onBindViewHolder(SortViewHolder holder, int position) {
        int section = getSectionForPosition(position);

        SortModel sortModel = mSortModels.get(position);

        if(position == getPositionForSection(section)){
            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(sortModel.getSortLetters());
        }else{
            holder.tvLetter.setVisibility(View.GONE);
        }
        holder.tvTitle.setText(sortModel.getName());

    }


    @Override
    public int getItemCount() {
        return mSortModels.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
         for(int i = 0;i< getItemCount(); i++){
             String sortStr = mSortModels.get(i).getSortLetters();
             char firstChar = sortStr.toUpperCase().charAt(0);
             if(firstChar == sectionIndex){
                 return i;
             }
         }
        return  -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return mSortModels.get(position).getSortLetters().charAt(0);
    }

    /**
     * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String  sortStr = str.trim().substring(0, 1).toUpperCase();
        // ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    public static class SortViewHolder extends RecyclerView.ViewHolder {

         TextView tvTitle;
         TextView tvLetter;

        public SortViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.title);
            tvLetter = (TextView)itemView.findViewById(R.id.catalog);
        }
    }

}
