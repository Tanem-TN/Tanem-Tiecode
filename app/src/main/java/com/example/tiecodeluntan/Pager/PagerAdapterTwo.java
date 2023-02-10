package com.example.tiecodeluntan.Pager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.MagicIndicatorTool.CommonNavigatorTool;
import com.example.tiecodeluntan.MagicIndicatorTool.ViewPagerHelperTool;
import com.example.tiecodeluntan.R;
import com.example.tiecodeluntan.Recycler.RecyclerHolderTool;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;


public class PagerAdapterTwo extends RecyclerView.Adapter {

        private Context mContext;
        private int size = 1;
        public PagerAdapterTwo(Context mContext){
        }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.listtwo, parent, false);
            return new RecyclerHolderTool(view,mContext);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemViewType(int position){
        return position;
    }



    @Override
        public int getItemCount() {
            return size;
        }

}
