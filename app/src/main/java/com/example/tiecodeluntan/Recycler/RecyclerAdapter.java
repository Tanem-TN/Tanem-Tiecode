package com.example.tiecodeluntan.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {
    Context mContext;
    public RecyclerAdapter(ArrayList<String> aar,Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist, parent, false);
            return new RecyclerViewHolderTool(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 99;
    }
    public static class RecyclerViewHolderTool extends RecyclerView.ViewHolder {
        TextView Texe;

        RecyclerViewHolderTool(@NonNull View itemView) {
            super(itemView);
            Texe = itemView.findViewById(R.id.textView2);
        }

    }
}

