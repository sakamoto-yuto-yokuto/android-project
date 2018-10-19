package jp.co.sakamoto.androidproject.presentation.viewmodel.fragment;

import android.databinding.Observable;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.data.entity.Gallery;
import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class GalleryFragmentViewModel extends ViewModel {
    public ObservableField<List<Gallery>> galleries = new ObservableField<>(new ArrayList<>());

    @Inject
    public GalleryFragmentViewModel() {
    }

    public void getGalleries() {
        List<Gallery> list = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            list.add(Gallery.newInstance(String.valueOf(index), "Gallery " + index));
        }
        galleries.set(list);
    }
}
