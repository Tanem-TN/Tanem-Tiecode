package com.example.tiecodeluntan.Recycler;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.MainActivity;
import com.example.tiecodeluntan.R;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerHolderTool extends RecyclerView.ViewHolder{
    RecyclerView Recycler;
    RecyclerAdapter Adapter;
    public RecyclerHolderTool(@NonNull View itemView, Context mContext) {
        super(itemView);
        ArrayList<String> aar = new ArrayList<>();
        aar.add("666");
        aar.add("666");
        aar.add("666");
        aar.add("666");
        aar.add("666");
        aar.add("666");
        Adapter  = new RecyclerAdapter(aar,mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        Recycler = itemView.findViewById(R.id.Recycler1);
        Recycler.setAdapter(Adapter);
        Recycler.setLayoutManager(layoutManager);
    }
}
