package com.example.pwmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WebAdapter extends RecyclerView.Adapter<WebAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public WebAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;

        ItemViewHolder(View itemView) {
            super(itemView) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, PassInfo.class);
                        intent.putExtra("TEXT",list.get(pos));
                        context.startActivity(intent);
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.web) ;
        }
    }




    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        Context context = parent.getContext();
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listview, parent, false);
        //return new ItemViewHolder(view);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.row_web, parent, false) ;
        ItemViewHolder vh = new ItemViewHolder(view) ;

        return vh ;

    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.

        //int itemposition = position;
        //holder.label.setText(list.get(itemposition));
        String item = list.get(position);
        holder.textView1.setText((CharSequence) item);

    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return list.size();
    }



}

