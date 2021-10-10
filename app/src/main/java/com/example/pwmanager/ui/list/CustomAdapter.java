package com.example.pwmanager.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pwmanager.R;
import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<PasswordItem> items;
    private OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView urlText;

        public ViewHolder(View view) {
            super(view);
            nameText = (TextView) view.findViewById(R.id.item_name);
            urlText = (TextView) view.findViewById(R.id.item_url);
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getUrlText() {
            return urlText;
        }
    }

    public interface OnItemClickListener {
        public void onClick(PasswordItem item);
    }

    public CustomAdapter() {
        this.items = new ArrayList<>();
    }
    public CustomAdapter(ArrayList<PasswordItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.web_listitem, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PasswordItem item = items.get(position);
        viewHolder.getNameText().setText(item.getName());
        viewHolder.getUrlText().setText(item.getUrl());
        if(onItemClickListener!=null) {
            viewHolder.itemView.setOnClickListener(v -> {
                onItemClickListener.onClick(item);
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<PasswordItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PasswordItem> items) {
        this.items = items;
        notifyDataSetChanged();;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}