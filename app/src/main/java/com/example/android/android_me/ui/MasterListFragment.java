package com.example.android.android_me.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.data.SharedViewModel;

public class MasterListFragment extends Fragment {

    private SharedViewModel viewModel;

    public MasterListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        GridView gridView = rootView.findViewById(R.id.grid_view);

        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setSelectedImageId(i);

                Toast.makeText(getActivity(),
                        "PPPosition: " + viewModel.getSelectedImageId().getValue(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });


        return rootView;
    }


}
