package com.example.pwmanager.ui.list;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwmanager.R;
import com.example.pwmanager.StoreUtils;
import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ListViewModel viewModel;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private ItemTouchHelper helper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(getActivity()).get(ListViewModel.class);
        //fragment_list.xml 화면 불러오기
        View root = inflater.inflate(R.layout.fragment_list, container, false);

        //어답터 생성 및 리사이클러뷰와 연결
        adapter = new CustomAdapter();
        recyclerView = root.findViewById(R.id.list_rcview);
        recyclerView.setAdapter(adapter);

        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        helper.attachToRecyclerView(recyclerView);

        //item 클릭 시 DetailFragment 로 화면 전환
        adapter.setOnItemClickListener(item -> {
            viewModel.getSelectItem().setValue(item);
            Fragment f = new DetailFragment();
            getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, f).addToBackStack(null).commit();
        });


        //전체 비밀번호 정보리스트를 연결
        //livedata가 제공하는 observe()메소드를 사용 -> 뷰모델의 데이터 값이 바뀌면 안의 람다식 실행
        viewModel.getPasswordList().observe(getViewLifecycleOwner(), passwordItems -> {
            adapter.setItems(passwordItems);
        });
        return root;
    }

    @Override
    public void onResume() {
        ArrayList<PasswordItem> data = new StoreUtils(getContext()).getItems();
        viewModel.getPasswordList().setValue(data);
        super.onResume();
    }

    private void setUpRecyclerView() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c,@NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
                helper.onDraw(c, parent, state);
            }
        });
    }
}