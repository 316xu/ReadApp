package hust.xujifa.readapp.activity;

import android.util.Log;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.ReadAction;
import hust.xujifa.readapp.presenter.ReadPresenter;
import hust.xujifa.readapp.presenter.ReadPresenterImp;

public class ReadActivity extends BaseActivity implements ReadAction {
    private static final String TAG = ReadActivity.class.getSimpleName();
    ReadPresenter presenter;
    int bookCode;
    @Override
    public void init() {

        setContentView(R.layout.read_activity);

        bookCode=bundle.getInt("bookcode");
        Log.d(TAG,"init()"+bookCode);
        presenter = new ReadPresenterImp(this);

        presenter.getCatolog(bookCode);
    }
}
