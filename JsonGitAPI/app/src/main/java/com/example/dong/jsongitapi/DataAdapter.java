package com.example.dong.jsongitapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolderData> {

    private List<ItemGit> mItemGits;
    private Context mContext;

    public DataAdapter(Context context, List<ItemGit> itemGits) {
        this.mContext = context;
        this.mItemGits = itemGits;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_data, viewGroup, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData viewHolderData, int position) {
        ItemGit itemGit = mItemGits.get(position);
        viewHolderData.mTextName.setText(itemGit.getName());
        viewHolderData.mTextFullName.setText(itemGit.getFullName());
        viewHolderData.mTextUrl.setText(itemGit.getUrl());
    }

    @Override
    public int getItemCount() {
        return mItemGits == null ? 0 : mItemGits.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextFullName;
        private TextView mTextUrl;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.textName);
            mTextFullName = itemView.findViewById(R.id.textFullName);
            mTextUrl = itemView.findViewById(R.id.textUri);
        }

    }

}
