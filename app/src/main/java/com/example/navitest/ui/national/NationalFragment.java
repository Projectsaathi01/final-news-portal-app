package com.example.navitest.ui.national;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navitest.R;
import com.example.navitest.models.National;
import com.example.navitest.models.NationalAdapter;

import java.util.List;

public class NationalFragment extends Fragment {

    private NationalViewModel nationalViewModel;
    private RecyclerView recyclerView;
    private NationalAdapter nationalAdapter;
    private List<National> nationals;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nationalViewModel = ViewModelProviders.of(this).get(NationalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_national, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        nationalViewModel = ViewModelProviders.of(this).get(NationalViewModel.class);

        nationalViewModel.getNationalArticles().observe(this, new Observer<List<National>>() {
            @Override
            public void onChanged(List<National> nationals) {

                nationalAdapter = new NationalAdapter(nationals, getActivity());



                recyclerView.setAdapter(nationalAdapter);
                nationalAdapter.notifyDataSetChanged();

            }
        });

        return root;
    }
}