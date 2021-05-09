package com.example.pwmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private List<String> list = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listview, parent, false);
        return new ItemViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.

        int itemposition = position;
        holder.label.setText(list.get(itemposition));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return list.size();
    }

    void add(String data) {
        // 외부에서 item을 추가시킬 함수입니다.
        list.add(data);
    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView label;

        ItemViewHolder(View itemView) {
            super(itemView);

            label = (TextView)itemView.findViewById(R.id.label);
        }


    }
}

