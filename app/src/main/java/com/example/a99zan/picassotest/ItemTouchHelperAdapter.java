package com.example.a99zan.picassotest;

/**
 * Created by 99zan on 2018/1/5.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
