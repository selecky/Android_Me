package com.example.android.android_me.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.data.SharedViewModel;

public class HeadFragment extends Fragment {

    private int mImageId;
    private SharedViewModel viewModel;
    private int clickCounter;


    public HeadFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickCounter++;

                if (clickCounter > (AndroidImageAssets.getHeads().size() - 1)) {
                    clickCounter = 0;
                }

                imageView.setImageResource(AndroidImageAssets.getAll().get(clickCounter));


            }
        });


        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getSelectedImageId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {


                mImageId = integer;

                if (mImageId < AndroidImageAssets.getHeads().size()
                        ) {
                    clickCounter = mImageId;
                    imageView.setImageResource(AndroidImageAssets.getAll().get(mImageId));
                } else {
                    return;
                }

            }
        });


        imageView.setImageResource(AndroidImageAssets.getHeads().get(mImageId));


        return rootView;
    }


}
