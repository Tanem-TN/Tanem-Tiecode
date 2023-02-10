package com.example.tiecodeluntan.Pager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.R;


public class PagerAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private int size = 2;
        public PagerAdapter(Context context) {
            mContext = context;
        }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.page, parent, false);
            return new PagerHolderToolTwo(view,mContext);
        }else if (viewType == 1){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list, parent, false);
            return new PagerHolderTool(view);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemViewType(int position){
        if (position == 0) {
            return 0;
        }
        if (position == 1) {
            return 1;
        }
        return position;
    }


    @Override
        public int getItemCount() {
            return size;
        }

}
