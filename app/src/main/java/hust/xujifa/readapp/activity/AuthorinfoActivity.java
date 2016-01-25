package hust.xujifa.readapp.activity;

import android.util.Log;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.AuthorinfoAction;
import hust.xujifa.readapp.presenter.AuthorinfoPresenter;
import hust.xujifa.readapp.presenter.AuthorinfoPresenterImp;

/**
 * Created by xujifa on 2016/1/24.
 */
public class AuthorinfoActivity extends BaseActivity implements AuthorinfoAction {
    private static final String TAG=AuthorinfoActivity.class.getSimpleName();
    private AuthorinfoPresenter presenter;
    String authorUrl;

    public void init() {
        setContentView(R.layout.activity_authorinfo);
        authorUrl=bundle.getString("authorUrl");

        presenter=new AuthorinfoPresenterImp(this);
        Log.d(TAG,authorUrl );
        presenter.getAuthorinfo(authorUrl);


    }
}
