package hust.xujifa.readapp.presenter;

import java.util.List;

import hust.xujifa.readapp.module.BookSimple;

/**
 * Created by xujifa on 2016/1/23.
 */
public interface BooklistPresenter {
    void getbBooklist(int type,List<BookSimple> books);
}
