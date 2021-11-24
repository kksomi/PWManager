package com.example.pwmanager.ui.list;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pwmanager.R;
import com.example.pwmanager.StoreUtils;
import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements ItemTouchHelperListener, OnDialogListener {


    private ArrayList<PasswordItem> items;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private ArrayList<String> unFilteredlist;
    private ArrayList<String> filteredList;

    // 아이템을 가지고 있는 역할
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView urlText;

        public ViewHolder(View view) {
            super(view); // 슈퍼를 이용해 view 생성
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
        context = viewGroup.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PasswordItem item = items.get(position);
        viewHolder.getNameText().setText(item.getName());
        viewHolder.getNameText().setTag(position);
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

    @Override
    public boolean onItemMove(int from_position, int to_position) {
//        PasswordItem number = items.get(from_position);
//        items.remove(from_position);
//        items.add(to_position, number);
//
//        new StoreUtils(context).updateItems(items);
//
//        notifyItemMoved(from_position, to_position);

        return true;

    }

    //아이템 삭제
    @Override
    public void onItemSwipe(int position) {
//        PasswordItem number = numbook.get(position);
        items.remove(position);
        Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);
        notifyDataSetChanged();

        new StoreUtils(context).updateItems(items);
    }

    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {


        //수정 버튼 클릭시 다이얼로그 생성
        CustomDialog dialog = new CustomDialog(context, position, items.get(position));

        //화면 사이즈 구하기
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
//
        //다이얼로그 사이즈 세팅
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
        wm.copyFrom(dialog.getWindow().getAttributes());
        wm.width = (int) (width);
        wm.height = height;

        //다이얼로그 Listener 세팅
        dialog.setDialogListener(this);

        //다이얼로그 띄우기
        dialog.show();
    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        items.remove(position);
        Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);
        notifyDataSetChanged();

        new StoreUtils(context).updateItems(items);
    }

    @Override
    public void onFinish(int position, PasswordItem item) {
        items.set(position, item);
        notifyItemChanged(position);
        notifyDataSetChanged();

        new StoreUtils(context).updateItems(items);
    }

    public void filterList(ArrayList<PasswordItem> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }
}