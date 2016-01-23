package hust.xujifa.readapp.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.BooklistAction;
import hust.xujifa.readapp.presenter.BooklistPresenter;
import hust.xujifa.readapp.presenter.BooklistPresenterImp;

/**
 * Created by xujifa on 2016/1/23.
 */
public class BooklistActivity extends BaseActivity implements BooklistAction {

    public static enum BookType{
        TOP,SCHOOL,CITY,CLIFFHANG,MIRACULOUS,FANTASY,XIANXIA,HISTORY,GAME,SCIENCE,OTHER
    }
    String[]typeStrs={"top","school","city","cliffhang","miraculous","fantasy","xianxia",
        "history","game","science","other"};
    BooklistPresenter presenter;
    RecyclerView mBooklist;
    int type;
    @Override
    public void init() {

        setContentView(R.layout.activity_booklist);
        type=bundle.getInt("type");
        presenter=new BooklistPresenterImp(this);
        mBooklist= (RecyclerView) findViewById(R.id.booklist);

    }
}
