package com.taoism.journeytoandroid.photogallery;

///**
// * Date: 2015-05-28
// * Time: 16:47
// * Author: Morphin3
// * WeChat: 398788401
// * E-mail: morphin333@gmail.com
// * -----------------------------
// * FIXME
// */
//public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder> {
//
//    private Context mContext;
//    private String[] mTitles;
//    private ArrayList<GalleryItem> mItems= new ArrayList<GalleryItem>();
//
//    public PhotoGalleryAdapter(Context mContext) {
//        this.mContext = mContext;
//        mTitles= mContext.getResources().getStringArray(R.array.titles);
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_gallery, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        holder.iv_picture.setImageResource(R.drawable.ic_nav_0);
//        GalleryItem item = mItems.get(position);
//        mThum
//
////        holder.tv_title.setText(mItems.get(position).getmCaption());
////        holder.tv_title.setText(mTitles[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItems.size();
////        return mTitles == null? 0:mTitles.length;
//    }
//
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ImageView iv_picture;
//        public TextView tv_title;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            this.iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
//            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
//        }
//    }
//
//
//    public void setData(ArrayList<GalleryItem> items){
//        mItems.clear();
//        mItems.addAll(items);
////        notifyDataSetChanged();
//    }
//
//
//}
