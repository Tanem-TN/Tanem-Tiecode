package com.example.tiecodeluntan.Pager;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class PagerHolderTool extends RecyclerView.ViewHolder {
    //        static TextView textView;
    static TextView Texe;

    PagerHolderTool(@NonNull View itemView) {
        super(itemView);
        Texe = itemView.findViewById(com.example.tiecodeluntan.R.id.textView);
    }

}