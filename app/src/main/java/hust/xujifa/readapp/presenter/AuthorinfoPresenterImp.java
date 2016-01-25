package hust.xujifa.readapp.presenter;

import android.util.Log;

import hust.xujifa.readapp.action.AuthorinfoAction;
import hust.xujifa.readapp.helper.App;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xujifa on 2016/1/24.
 */
public class AuthorinfoPresenterImp implements AuthorinfoPresenter {
    private static final String TAG=AuthorinfoPresenterImp.class.getSimpleName();
    private AuthorinfoAction action;

    public AuthorinfoPresenterImp(AuthorinfoAction action){
        this.action=action;
    }

    @Override
    public void getAuthorinfo(String url) {
        Log.d(TAG,url);
        App.getKJAPI().getAuthorinfo(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG,s );
                    }
                });
    }
}
