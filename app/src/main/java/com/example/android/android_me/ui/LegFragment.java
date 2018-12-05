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

public class LegFragment extends Fragment {

    private int mImageId;
    private SharedViewModel viewModel;
    private int clickCounter;


    public LegFragment() {
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

                /**
                 * Handling the case during the start of an app when clickCounter = 0
                 */
                if (clickCounter == 1) {
                    clickCounter = AndroidImageAssets.getHeads().size()
                            + AndroidImageAssets.getBodies().size()
                            + 1;
                }


                /**
                 * When the last leg image is displayed, on-click displays the first again.
                 */
                if (clickCounter > AndroidImageAssets.getHeads().size()
                        + AndroidImageAssets.getBodies().size()
                        + AndroidImageAssets.getLegs().size()
                        - 1) {
                    clickCounter = AndroidImageAssets.getHeads().size()
                            + AndroidImageAssets.getBodies().size();
                }

                imageView.setImageResource(AndroidImageAssets.getAll().get(clickCounter));


            }
        });


        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getSelectedImageId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                mImageId = integer;

                /**
                 * Only leg images to be displayed in LegFragment
                 */
                if (mImageId > AndroidImageAssets.getHeads().size()
                        + AndroidImageAssets.getBodies().size()
                        - 1 ) {
                    clickCounter = mImageId;
                    imageView.setImageResource(AndroidImageAssets.getAll().get(mImageId));
                } else {
                    return;
                }

            }
        });


        imageView.setImageResource(AndroidImageAssets.getLegs().get(mImageId));


        return rootView;
    }


}
