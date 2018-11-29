package com.example.android.android_me.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.data.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class BodyFragment extends Fragment {


    private List<Integer> mImageList;
    private int mImageId;
    private int rest = mImageId % 12;
    private SharedViewModel viewModel;


    public BodyFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mImageId < mImageList.size() - 1) {
                    mImageId++;
                } else {
                    mImageId = 0;
                }

                imageView.setImageResource(mImageList.get(mImageId));


            }
        });


        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getSelectedImageId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mImageId = integer;

                if (mImageId > AndroidImageAssets.getHeads().size() -1 &&
                        mImageId < (AndroidImageAssets.getAll().size() - AndroidImageAssets.getLegs().size()) -1
                        ) {
                    imageView.setImageResource(AndroidImageAssets.getAll().get(mImageId));
                }
                else {
                    return;
                }

            }
        });


        if (mImageList != null) {
                imageView.setImageResource(mImageList.get(mImageId));
        } else {
            Toast.makeText(getActivity(), "List of images in fragment is empty", Toast.LENGTH_SHORT).show();
        }


        return rootView;
    }


    public void setImageList(List<Integer> ImageList) {
        this.mImageList = ImageList;
    }

    public void setImageId(int imageId) {
        this.mImageId = imageId;
    }


}
