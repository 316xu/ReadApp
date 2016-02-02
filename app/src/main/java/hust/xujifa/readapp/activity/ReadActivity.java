package hust.xujifa.readapp.activity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.ReadAction;
import hust.xujifa.readapp.presenter.ReadPresenter;
import hust.xujifa.readapp.presenter.ReadPresenterImp;
import hust.xujifa.uilib.module.Catalog;
import hust.xujifa.uilib.viewgroup.ReadPage;

public class ReadActivity extends BaseActivity implements ReadAction {
    private static final String TAG = ReadActivity.class.getSimpleName();
    ReadPresenter presenter;
    int bookCode;
    List<Catalog> catalogs;
    ReadPage readPage;
    @Override
    public void init() {

        setContentView(R.layout.activity_read);
        catalogs=new ArrayList<>();
        readPage= (ReadPage) findViewById(R.id.read_page);
        readPage.setCatalogs(catalogs);
       // bookCode=bundle.getInt("bookcode");
        bookCode=2579;
        Log.d(TAG,"init()"+bookCode);
        presenter = new ReadPresenterImp(this,catalogs);

        presenter.getCatalog(bookCode);
    }

    @Override
    public void catalogReady() {
        Log.d(TAG,"catalogReady()");
        presenter.getContent();

    }

    @Override
    public void readReady() {

    }
}
