package hust.xujifa.readapp.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.BookinfoAction;
import hust.xujifa.readapp.presenter.BookinfoPresenter;
import hust.xujifa.readapp.presenter.BookinfoPresenterImp;

/**
 * Created by xujifa on 2016/1/24.
 */
public class BookinfoActivity extends BaseActivity implements BookinfoAction {
    private static final String TAG = BookinfoActivity.class.getSimpleName();
    private BookinfoPresenter presenter;
    int bookcode;
    String authorUrl;
    public void init() {
        setContentView(R.layout.activity_bookinfo);
        bookcode = bundle.getInt("bookcode");
        presenter = new BookinfoPresenterImp(this);
        presenter.getbookinfo(bookcode);

    }




    @Override
    public void setAuthorUrl(String authorUrl) {
        Log.d(TAG, authorUrl);
        this.authorUrl=authorUrl;
    }


    public void aboutAuthor(View v){
        Intent authorIntent=new Intent(BookinfoActivity.this,AuthorinfoActivity.class);

        authorIntent.putExtra("authorUrl", authorUrl);

        startActivity(authorIntent);
    }
    public void startRead(View v){
        Intent readIntent=new Intent(BookinfoActivity.this,ReadActivity.class);
        readIntent.putExtra("bookcode",bookcode);
        startActivity(readIntent);
    }

}
