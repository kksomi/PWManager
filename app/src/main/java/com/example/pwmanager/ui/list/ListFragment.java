package com.example.pwmanager.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwmanager.R;
import com.example.pwmanager.StoreUtils;
import com.example.pwmanager.model.PasswordItem;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ListViewModel viewModel;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(getActivity()).get(ListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list, container, false);

        adapter = new CustomAdapter();
        recyclerView = root.findViewById(R.id.list_rcview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(item -> {
            viewModel.getSelectItem().setValue(item);
            Fragment f = new DetailFragment();
            getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, f).commit();
        });

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
}