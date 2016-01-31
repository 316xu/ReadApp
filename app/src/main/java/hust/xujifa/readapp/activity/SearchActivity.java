package hust.xujifa.readapp.activity;

import android.util.Log;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.SearchAction;
import hust.xujifa.readapp.presenter.SearchPresenter;
import hust.xujifa.readapp.presenter.SearchPresenterImp;

public class SearchActivity extends BaseActivity implements SearchAction {
    private static final String TAG = SearchActivity.class.getSimpleName();
    SearchPresenter presenter;
    String keyword;
    @Override
    public void init() {
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenterImp(this);
        keyword=bundle.getString("keyword");
        presenter.search(keyword);

    }
}
