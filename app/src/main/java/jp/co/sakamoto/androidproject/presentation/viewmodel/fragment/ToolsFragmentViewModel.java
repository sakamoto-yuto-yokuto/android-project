package jp.co.sakamoto.androidproject.presentation.viewmodel.fragment;

import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.data.entity.Tool;
import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class ToolsFragmentViewModel extends ViewModel {
    private static final int FIRST_LOAD_DATA_COUNT = 100;
    private static final int ADD_PAGE_ITEM_COUNT = 20;
    private static final int MAX_PAGE_INDEX = 5;

    public ObservableField<List<Tool>> tools = new ObservableField<>(new ArrayList<>());

    private int pageIndex = 0;

    @Inject
    public ToolsFragmentViewModel() {
    }

    public void getFirstData() {
        List<Tool> list = new ArrayList<>();
        for (int index = 0; index < FIRST_LOAD_DATA_COUNT; index++) {
            list.add(Tool.newInstance(String.valueOf(index), "Tool " + index));
        }
        this.tools.set(list);
    }

    public void loadNextPage() {
        if (pageIndex <= MAX_PAGE_INDEX) {
            int startIndex = FIRST_LOAD_DATA_COUNT + pageIndex * ADD_PAGE_ITEM_COUNT;
            int endCount = startIndex + ADD_PAGE_ITEM_COUNT + 1;
            for (int index = startIndex; index < endCount; index++) {
                this.tools.get().add(Tool.newInstance(String.valueOf(index), "Tool " + index));
            }
            this.tools.notifyChange();
            pageIndex++;
        }
    }
}
