package hust.xujifa.readapp.presenter;

import android.util.Log;

import java.util.List;

import hust.xujifa.readapp.action.BooklistAction;
import hust.xujifa.readapp.helper.App;
import hust.xujifa.readapp.helper.ConstantValue;
import hust.xujifa.readapp.module.BookSimple;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xujifa on 2016/1/23.
 */
public class BooklistPresenterImp implements BooklistPresenter {
    private BooklistAction action;
    private static final String TAG=BooklistPresenterImp.class.getSimpleName();



    public BooklistPresenterImp(BooklistAction action){
        this.action=action;
    }


    @Override
    public void getbBooklist(int type, final List<BookSimple> books) {
        App.getAPI().getbooklist("school","list1")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Subscriber<List<BookSimple>>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG,"com");
            }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG,"error:"+e.toString());
                }

                @Override
                public void onNext(List<BookSimple> bookSimples) {
                    books.addAll(bookSimples);
                    Log.d(TAG,"ok");
                    action.booklistUppdate();
                }
            });

    }
}
