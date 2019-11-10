package com.example.navitest.ui.international;

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
import com.example.navitest.models.International;
import com.example.navitest.models.InternationalAdapter;

import java.util.List;

public class InternationalFragment extends Fragment {


    private InternationalViewModel internationalViewModel;
    private RecyclerView recyclerView;
    private InternationalAdapter internationalAdapter;
    private List<International> internationals;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        internationalViewModel = ViewModelProviders.of(this).get(InternationalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_international, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        internationalViewModel = ViewModelProviders.of(this).get(InternationalViewModel.class);

        internationalViewModel.getInternational().observe(this, new Observer<List<International>>() {


            @Override
            public void onChanged(List<International> internationals) {

                internationalAdapter = new InternationalAdapter(getContext(), internationals);

                recyclerView.setAdapter(internationalAdapter);
            }
        });


        return root;


//        return inflater.inflate(R.layout.fragment_international, container, false);

    }
}