package com.snakeyhips.robotpet;

public interface OnDataChangeListener{
    public void onDataChanged(int size);
    
    OnDataChangeListener mOnDataChangeListener;
    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener){
        mOnDataChangeListener = onDataChangeListener;
    }
}
