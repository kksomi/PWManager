package com.example.pwmanager;

/**
 * Created by Administrator on 2017-08-07.
 */
/* 수정 필요

public class SearchAdapter extends BaseAdapter {

    private Context context = null;
    private List<String> list;
    private LayoutInflater inflate = null;
    private ViewHolder viewHolder;


    public SearchAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.inflate = LayoutInflater.from(this.context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.label.setText(list.get(position));

        return convertView;
    }

    class ViewHolder{
        public TextView label;
    }

}

 */