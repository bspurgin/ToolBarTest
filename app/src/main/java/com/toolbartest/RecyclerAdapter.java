package com.toolbartest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by uspurbr on 4/10/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<ListObject> mData = Collections.emptyList();
    private View.OnClickListener mItemClickListener;

    public RecyclerAdapter(Context context,
                           List<ListObject> data,
                           View.OnClickListener itemClickListener){
        mInflater = LayoutInflater.from(context);
        mData = data;
        mItemClickListener = itemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_navigation_list,parent, false);
        MyViewHolder holder = new MyViewHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListObject current = mData.get(position);
        holder.title.setText(current.title);
        holder.listIcon.setImageResource(current.imageId);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView listIcon;

        public MyViewHolder(View itemView,
                            View.OnClickListener itemClickListener) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.listText);
            listIcon = (ImageView)itemView.findViewById(R.id.listIcon);
            itemView.setOnClickListener(itemClickListener);
        }
    }
}
