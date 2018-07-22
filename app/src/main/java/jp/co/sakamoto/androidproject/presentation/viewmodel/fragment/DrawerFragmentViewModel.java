package jp.co.sakamoto.androidproject.presentation.viewmodel.fragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;
import jp.co.sakamoto.androidproject.presentation.viewmodel.adapter.DrawerItemViewModel;

public class DrawerFragmentViewModel extends ViewModel {
    @Inject
    public DrawerFragmentViewModel() { }

    private List<DrawerItemViewModel> getDrawerItemList() {
        List<DrawerItemViewModel> drawerItemViewModels = new ArrayList<>();
        return drawerItemViewModels;
    }
}
