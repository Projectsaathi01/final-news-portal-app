package com.example.navitest.ui.finance;

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
import com.example.navitest.models.Finance;
import com.example.navitest.models.FinanceAdapter;

import java.util.List;

public class FinanceFragment extends Fragment {

    private FinanceViewModel financeViewModel;
    private RecyclerView recyclerView;
    private FinanceAdapter financeAdapter;
    private List<Finance> finances;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        financeViewModel = ViewModelProviders.of(this).get(FinanceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_finance, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        financeViewModel = ViewModelProviders.of(this).get(FinanceViewModel.class);

        financeViewModel.getFinances().observe(this, new Observer<List<Finance>>() {
            @Override
            public void onChanged(List<Finance> finances) {

                financeAdapter = new FinanceAdapter(getContext(), finances);

                recyclerView.setAdapter(financeAdapter);
            }
        });


        return root;
    }
}