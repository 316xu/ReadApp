package hust.xujifa.readapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.BooklistAction;
import hust.xujifa.readapp.adapter.BooklistAdapter;
import hust.xujifa.readapp.helper.App;
import hust.xujifa.readapp.module.BookSimple;
import hust.xujifa.readapp.presenter.BooklistPresenter;
import hust.xujifa.readapp.presenter.BooklistPresenterImp;

/**
 * Created by xujifa on 2016/1/23.
 */
public class BooklistActivity extends BaseActivity implements BooklistAction {


    BooklistPresenter presenter;
    RecyclerView mBooklist;
    int type;
    List<BookSimple>books;
    BooklistAdapter adapter;
    @Override
    public void init() {

        setContentView(R.layout.activity_booklist);
        type=bundle.getInt("type");
        presenter=new BooklistPresenterImp(this);
        mBooklist= (RecyclerView) findViewById(R.id.booklist);
        books=new ArrayList<>();
        presenter.getbBooklist(type,books);
        adapter=new BooklistAdapter(books,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mBooklist.setLayoutManager(layoutManager);
        mBooklist.setHasFixedSize(true);
        mBooklist.setAdapter(adapter);

    }

    @Override
    public void booklistUppdate() {
        adapter.notifyDataSetChanged();
    }
}
