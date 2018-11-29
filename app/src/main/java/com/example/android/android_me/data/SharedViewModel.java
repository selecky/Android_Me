package com.example.android.android_me.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Integer> selectedImageId = new MutableLiveData<>();

    public void setSelectedImageId(Integer image) {

        selectedImageId.setValue(image);
    }

    public LiveData<Integer> getSelectedImageId() {

        return selectedImageId;
    }
}
