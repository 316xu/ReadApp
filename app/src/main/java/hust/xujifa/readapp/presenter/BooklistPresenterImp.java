package hust.xujifa.readapp.presenter;

import hust.xujifa.readapp.action.BooklistAction;

/**
 * Created by xujifa on 2016/1/23.
 */
public class BooklistPresenterImp implements BooklistPresenter {
    BooklistAction action;
    public BooklistPresenterImp(BooklistAction action){
        this.action=action;
    }
}
