package com.example.navitest.ui.agriculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navitest.MainActivity;
import com.example.navitest.R;
import com.example.navitest.models.Agriculture;
import com.example.navitest.models.AgricultureAdapter;

import java.util.List;

public class AgricultureFragment extends Fragment {

    private AgricultureViewModel agricultureViewModel;
    private RecyclerView recyclerView;
    private AgricultureAdapter agricultureAdapter;
    private List<Agriculture> agricultures;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        agricultureViewModel = ViewModelProviders.of(this).get(AgricultureViewModel.class);
        View root = inflater.inflate(R.layout.fragment_agriculture, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        agricultureViewModel = ViewModelProviders.of(this).get(AgricultureViewModel.class);

        agricultureViewModel.getAgriculture().observe(this, new Observer<List<Agriculture>>() {
            @Override
            public void onChanged(List<Agriculture> agricultures) {

                agricultureAdapter = new AgricultureAdapter(getContext(), agricultures);

                recyclerView.setAdapter(agricultureAdapter);

//                agricultureAdapter.setOnItemClickListener(MainActivity.this);
            }
        });


        return root;
    }
}