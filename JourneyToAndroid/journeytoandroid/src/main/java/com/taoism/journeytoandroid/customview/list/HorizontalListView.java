package com.taoism.journeytoandroid.customview.list;

/*
 * HorizontalListView.java v1.5
 *
 * 
 * The MIT License
 * Copyright (c) 2011 Paul Soucy (paul@dev-smart.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.screenutil.ScreenUtil;

import java.util.LinkedList;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {

	public boolean mAlwaysOverrideTouch = true;
	protected ListAdapter mAdapter;
	private int mLeftViewIndex = -1;
	private int mRightViewIndex = 0;
	protected int mCurrentX;
	protected int mNextX;
	private int mMaxX = Integer.MAX_VALUE;
	private int mDisplayOffset = 0;
	protected Scroller mScroller;
	private GestureDetector mGesture;
	private Queue<View> mRemovedViewQueue = new LinkedList<View>();
	private OnItemSelectedListener mOnItemSelected;
	private OnItemClickListener mOnItemClicked;
	private OnItemLongClickListener mOnItemLongClicked;
	private boolean mDataChanged = false;
	private Runnable requestLayout;
	private boolean forceFocus = false;
	private static final int MAX_X_OVERSCROLL_DISTANCE = 100;
	private Context mContext;
	private int mMaxXOverscrollDistance;
	private Drawable androidEdge;
	private Drawable androidGlow;
	private float mLastMotionX;
	private float mLastMotionY;
	private boolean isIntercept = false;


	public  static final int MODE_SCROLL_CONTENT = 0;
	public  static final int MODE_SCROLL_CONTAINER = 1;
	private int mScrollMode =  MODE_SCROLL_CONTENT;

	private float mMaxScrollX = 0;

	private boolean mIsListViewReachRightEdge = false;


	public HorizontalListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
		mContext = context;
//		initBounceDistance();
	}

	public void setForceFocus(boolean flag) {
		this.forceFocus = flag;
	}

	private synchronized void initView() {
		mLeftViewIndex = -1;
		mRightViewIndex = 0;
		mDisplayOffset = 0;
		mCurrentX = 0;
		mNextX = 0;
		mMaxX = Integer.MAX_VALUE;
		mScroller = new Scroller(getContext());
		mGesture = new GestureDetector(getContext(), mOnGesture);
		requestLayout = new Runnable() {
			@Override
			public void run() {
				requestLayout();
			}
		};
	}

	@Override
	public void setOnItemSelectedListener(
			AdapterView.OnItemSelectedListener listener) {
		mOnItemSelected = listener;
	}

	@Override
	public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
		mOnItemClicked = listener;
	}

	@Override
	public void setOnItemLongClickListener(
			AdapterView.OnItemLongClickListener listener) {
		mOnItemLongClicked = listener;
	}

	private DataSetObserver mDataObserver = new DataSetObserver() {

		@Override
		public void onChanged() {
			synchronized (HorizontalListView.this) {
				mDataChanged = true;
			}
			invalidate();
			requestLayout();
		}

		@Override
		public void onInvalidated() {
			reset();
			invalidate();
			requestLayout();
		}

	};

	@Override
	public ListAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public View getSelectedView() {
		// TODO: implement
		return null;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		if (mAdapter != null) {
			mAdapter.unregisterDataSetObserver(mDataObserver);
		}
		mAdapter = adapter;
		mAdapter.registerDataSetObserver(mDataObserver);
		reset();
	}

	private synchronized void reset() {
		initView();
		removeAllViewsInLayout();
		requestLayout();
	}

	@Override
	public void setSelection(int position) {
		// TODO: implement
	}

	private void addAndMeasureChild(final View child, int viewPos) {
		LayoutParams params = child.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT);
		}

		addViewInLayout(child, viewPos, params, true);
		child.measure(
				MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST),
				MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST));
	}

	public float getCurrentX() {
		return (float) mCurrentX;
	}

	@Override
	protected synchronized void onLayout(boolean changed, int left, int top,
										 int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		if (mAdapter == null) {
			return;
		}

		if (mDataChanged) {
			int oldCurrentX = mCurrentX;
			initView();
			removeAllViewsInLayout();
			mNextX = oldCurrentX;
			mDataChanged = false;
		}

		if (mScroller.computeScrollOffset()) {
			int scrollx = mScroller.getCurrX();
			mNextX = scrollx;
		}

		if (mNextX <= 0) {
			mNextX = 0;
			mScroller.forceFinished(true);
		}

		boolean checkEdgeFlag = false;

		if (mNextX >= mMaxX) {
			mNextX = mMaxX;
			mScroller.forceFinished(true);

			checkEdgeFlag = true;
		}
//		else{
//			mContainerScrollListener.onLeaveContainerEdge();
//		}

		int dx = mCurrentX - mNextX;

		removeNonVisibleItems(dx);
		fillList(dx);
		positionItems(dx);

		mCurrentX = mNextX;


		if (!mScroller.isFinished()) {
			post(requestLayout);
		} else {
			if (mScrollListener != null) {
//			if (dx != 0 && mScrollListener != null) {
				mScrollListener.onScroll(null, mLeftViewIndex, mRightViewIndex
						- mLeftViewIndex, mAdapter.getCount());
			}
		}

		if(checkEdgeFlag){
			mContainerScrollListener.onReachContainerEdge();
		}
	}

	private void fillList(final int dx) {
		int edge = 0;
		View child = getChildAt(getChildCount() - 1);
		if (child != null) {
			edge = child.getRight();
		}
		fillListRight(edge, dx);

		edge = 0;
		child = getChildAt(0);
		if (child != null) {
			edge = child.getLeft();
		}
		fillListLeft(edge, dx);

	}

	private void fillListRight(int rightEdge, final int dx) {
		while (rightEdge + dx < getWidth()
				&& mRightViewIndex < mAdapter.getCount()) {

			View child = mAdapter.getView(mRightViewIndex,
					mRemovedViewQueue.poll(), this);
			addAndMeasureChild(child, -1);
			rightEdge += child.getMeasuredWidth();

			if (mRightViewIndex == mAdapter.getCount() - 1) {
				mMaxX = mCurrentX + rightEdge - getWidth();
			}

			if (mMaxX < 0) {
				mMaxX = 0;
			}
			mRightViewIndex++;
		}

	}

	private void fillListLeft(int leftEdge, final int dx) {
		while (leftEdge + dx > 0 && mLeftViewIndex >= 0) {
			View child = mAdapter.getView(mLeftViewIndex,
					mRemovedViewQueue.poll(), this);
			addAndMeasureChild(child, 0);
			leftEdge -= child.getMeasuredWidth();
			mLeftViewIndex--;
			mDisplayOffset -= child.getMeasuredWidth();
		}
	}

	private void removeNonVisibleItems(final int dx) {
		View child = getChildAt(0);
		while (child != null && child.getRight() + dx <= 0) {
			mDisplayOffset += child.getMeasuredWidth();
			mRemovedViewQueue.offer(child);
			removeViewInLayout(child);
			mLeftViewIndex++;
			child = getChildAt(0);

		}

		child = getChildAt(getChildCount() - 1);
		while (child != null && child.getLeft() + dx >= getWidth()) {
			mRemovedViewQueue.offer(child);
			removeViewInLayout(child);
			mRightViewIndex--;
			child = getChildAt(getChildCount() - 1);
		}
	}

	private void positionItems(final int dx) {
		if (getChildCount() > 0) {
			mDisplayOffset += dx;
			int left = mDisplayOffset;
			for (int i = 0; i < getChildCount(); i++) {
				View child = getChildAt(i);
				int childWidth = child.getMeasuredWidth();
				child.layout(left, 0, left + childWidth,
						child.getMeasuredHeight());
				left += childWidth + child.getPaddingRight();
			}
		}
	}

	public synchronized void scrollTo(int x) {
		mScroller.startScroll(mNextX, 0, x - mNextX, 0);
		requestLayout();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		boolean handled = super.dispatchTouchEvent(ev);
//		handled |= mGesture.onTouchEvent(ev);
//		if (forceFocus)
//		    getParent().requestDisallowInterceptTouchEvent(true);
//		return handled;

		boolean handled = super.dispatchTouchEvent(ev);
//		mGesture.onTouchEvent(ev);
		if (forceFocus)
			getParent().requestDisallowInterceptTouchEvent(true);

		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			isIntercept = false;
			mLastMotionX = ev.getX();
			mLastMotionY = ev.getY();
		} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			if (Math.abs(ev.getX() - mLastMotionX) > ScreenUtil.dip2px(10))
				isIntercept = true;

//			mLastMotionX = ev.getX();
//			mLastMotionY = ev.getY();
		} else if (ev.getAction() == MotionEvent.ACTION_UP) {
			isIntercept = false;
		}
		Log.e("HorizontaolListView", "action = " + ev.getAction() + "--- handled = " + handled);
		return handled;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
//		mGesture.onTouchEvent(ev);
//		return super.onTouchEvent(ev);

//		return mGesture.onTouchEvent(ev);

		if (mScrollMode == MODE_SCROLL_CONTENT) {
			mLastMotionX = ev.getX();
			return mGesture.onTouchEvent(ev);
		} else {
			if(ev.getAction() == MotionEvent.ACTION_MOVE){
				float distanceX = mLastMotionX - ev.getX();
				mLastMotionX = ev.getX();

				if (getScrollX() + distanceX > mMaxScrollX) {
					Log.i("HorizontalListView","getScrollX() = " +getScrollX() + "getScrollX() = " + distanceX + "---" + (getScrollX() + distanceX) + ">" + mMaxScrollX);
					Log.i("HorizontalListView", "scrollTo(mMaxScrollX, 0);");
					scrollTo((int) mMaxScrollX, 0);
				} else if (getScrollX() + distanceX < 0) {
					Log.i("HorizontalListView", "scrollTo(0, 0);");
					scrollTo(0, 0);
					if(mContainerScrollListener != null){
						mContainerScrollListener.onLeaveContainerEdge();
					}
				} else {
					Log.i("HorizontalListView", "scrollBy((int) distanceX, 0);");
					scrollBy((int) distanceX, 0);
					if(mContainerScrollListener != null){
						mContainerScrollListener.onContainerScroll();
					}
				}



				return true;
			}else{
				return true;
			}
		}
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//		} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//		} else if (ev.getAction() == MotionEvent.ACTION_UP) {
//		}
		return isIntercept;
	}

	protected boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
							  float velocityY) {
		synchronized (HorizontalListView.this) {
			mScroller.fling(mNextX, 0, (int) -velocityX, 0, 0, mMaxX, 0, 0);
		}
		requestLayout();

		return true;
	}

	protected boolean onDown(MotionEvent e) {
		mScroller.forceFinished(true);
		return true;
	}

	private OnScrollListener mScrollListener;

	public void setOnScrollListener(OnScrollListener l) {
		this.mScrollListener = l;
	}

	private OnContainerScrollListener mContainerScrollListener;

	public interface OnContainerScrollListener {
		void onContainerScroll();
		void onReachContainerEdge();
		void onLeaveContainerEdge();
	}

	public void setOnContainerScrollListener(OnContainerScrollListener onContainerScrollListener){
        this.mContainerScrollListener = onContainerScrollListener;
	}


	private OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {

		@Override
		public boolean onDown(MotionEvent e) {
			return HorizontalListView.this.onDown(e);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
							   float velocityY) {

			return HorizontalListView.this
					.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
								float distanceX, float distanceY) {
			synchronized (HorizontalListView.this) {
				mNextX += (int) distanceX;
			}
			requestLayout();

			return true;
		}



		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			for (int i = 0; i < getChildCount(); i++) {
				View child = getChildAt(i);
				if (isEventWithinView(e, child)) {
					if (mOnItemClicked != null) {
						mOnItemClicked.onItemClick(HorizontalListView.this,
								child, mLeftViewIndex + 1 + i,
								mAdapter.getItemId(mLeftViewIndex + 1 + i));
					}
					if (mOnItemSelected != null) {
						mOnItemSelected.onItemSelected(HorizontalListView.this,
								child, mLeftViewIndex + 1 + i,
								mAdapter.getItemId(mLeftViewIndex + 1 + i));
					}
					break;
				}

			}
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			int childCount = getChildCount();
			for (int i = 0; i < childCount; i++) {
				View child = getChildAt(i);
				if (isEventWithinView(e, child)) {
					if (mOnItemLongClicked != null) {
						mOnItemLongClicked.onItemLongClick(
								HorizontalListView.this, child, mLeftViewIndex
										+ 1 + i,
								mAdapter.getItemId(mLeftViewIndex + 1 + i));
					}
					break;
				}

			}
		}

		private boolean isEventWithinView(MotionEvent e, View child) {
			Rect viewRect = new Rect();
			int[] childPosition = new int[2];
			child.getLocationOnScreen(childPosition);
			int left = childPosition[0];
			int right = left + child.getWidth();
			int top = childPosition[1];
			int bottom = top + child.getHeight();
			viewRect.set(left, top, right, bottom);
			return viewRect.contains((int) e.getRawX(), (int) e.getRawY());
		}
	};

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
								   int scrollY, int scrollRangeX, int scrollRangeY,
								   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
				scrollRangeX, scrollRangeY, mMaxXOverscrollDistance,
				maxOverScrollY, isTouchEvent);
	}

	private void initBounceDistance() {
		final DisplayMetrics metrics = mContext.getResources()
				.getDisplayMetrics();
		mMaxXOverscrollDistance = (int) (metrics.density * MAX_X_OVERSCROLL_DISTANCE);
		if (Build.VERSION.SDK_INT >= 21) {
			// edgeEffect.setColor(color);
			return;
		}
		// setEdgeGlowColor(this, getResources().getColor(R.color.trans));
		try {
			int glowDrawableId = mContext.getResources().getIdentifier(
					"overscroll_glow", "drawable", "android");
			androidGlow = mContext.getResources().getDrawable(glowDrawableId);
			// edge
			int edgeDrawableId = mContext.getResources().getIdentifier(
					"overscroll_edge", "drawable", "android");
			androidEdge = mContext.getResources().getDrawable(edgeDrawableId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void brandGlowEffect(boolean reset) {
		if (androidGlow == null || androidEdge == null) {
			return;
		}
		if (reset) {
			androidGlow.clearColorFilter();
			androidEdge.clearColorFilter();
		} else {
			int brandColor = getResources().getColor(R.color.transparent);
			androidGlow.setColorFilter(brandColor, PorterDuff.Mode.SRC_IN);
			androidEdge.setColorFilter(brandColor, PorterDuff.Mode.SRC_IN);
		}
	}

	public void setScrollMode(int mode){
		mScrollMode = mode;
	}

	public void setMaxScrollX(float maxScrollX){
        mMaxScrollX = maxScrollX;
		if(mMaxScrollX < 0 ){
			mMaxScrollX = 0;
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
//		brandGlowEffect(false);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
//		brandGlowEffect(true);
	}

}

