package com.example.navitest.ui.tourism;

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
import com.example.navitest.models.Tourism;
import com.example.navitest.models.TourismAdapter;

import java.util.List;

public class TourismFragment extends Fragment {

    private TourismViewModel tourismViewModel;
    private RecyclerView recyclerView;
    private TourismAdapter tourismAdapter;
    private List<Tourism> tourisms;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tourismViewModel = ViewModelProviders.of(this).get(TourismViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tourism, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        tourismViewModel = ViewModelProviders.of(this).get(TourismViewModel.class);

        tourismViewModel.getTourism().observe(this, new Observer<List<Tourism>>() {
            @Override
            public void onChanged(List<Tourism> tourisms) {

                tourismAdapter = new TourismAdapter(getActivity(), tourisms);



                recyclerView.setAdapter(tourismAdapter);
                tourismAdapter.notifyDataSetChanged();

            }
        });

        return root;
    }
}