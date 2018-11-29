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

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.data.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class BodyFragment extends Fragment {

    private static final String TAG = "BodyPartFragment";
    public static final String IMAGE_LIST = "com.example.android.android_me.IMAGE_LIST";
    public static final String LIST_INDEX = "com.example.android.android_me.LIST_INDEX";


    private List<Integer> mImageList;
    private int mListIndex;
    private int rest = mListIndex % 12;
    private SharedViewModel viewModel;


    public BodyFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mImageList = savedInstanceState.getIntegerArrayList(IMAGE_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }




        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListIndex < mImageList.size() - 1) {
                    mListIndex++;
                } else {
                    mListIndex = 0;
                }

                imageView.setImageResource(mImageList.get(mListIndex));


            }
        });


        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getSelectedImageId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mListIndex = integer;
                if (mListIndex/12 == 1) {
                    imageView.setImageResource(AndroidImageAssets.getBodies().get(rest));
                }
                else {
                    return;
                }

            }
        });



        if (mImageList != null) {

                imageView.setImageResource(mImageList.get(mListIndex));



        } else

        {
            Log.d(TAG, "List of images in fragment is empty");
        }

        return rootView;
    }


    public void setImageList(List<Integer> ImageList) {
        this.mImageList = ImageList;
    }

    public void setListIndex(int ListIndex) {
        this.mListIndex = ListIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_LIST, (ArrayList<Integer>) mImageList);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
