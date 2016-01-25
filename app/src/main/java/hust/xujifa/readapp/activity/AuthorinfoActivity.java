package hust.xujifa.readapp.activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.AuthorinfoAction;
import hust.xujifa.readapp.adapter.AuthorBooklistAdapter;
import hust.xujifa.readapp.presenter.AuthorinfoPresenter;
import hust.xujifa.readapp.presenter.AuthorinfoPresenterImp;

/**
 * Created by xujifa on 2016/1/24.
 */
public class AuthorinfoActivity extends BaseActivity implements AuthorinfoAction {
    private static final String TAG=AuthorinfoActivity.class.getSimpleName();
    private AuthorinfoPresenter presenter;
    String authorUrl;
    RecyclerView booklist;
    public void init() {
        setContentView(R.layout.activity_authorinfo);
        booklist= (RecyclerView) findViewById(R.id.author_booklist);

        authorUrl=bundle.getString("authorUrl");
        presenter=new AuthorinfoPresenterImp(this);
        Log.d(TAG, authorUrl);
        presenter.getAuthorinfo(authorUrl);



    }
}
