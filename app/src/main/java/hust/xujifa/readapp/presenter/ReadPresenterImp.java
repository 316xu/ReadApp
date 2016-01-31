package hust.xujifa.readapp.presenter;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hust.xujifa.readapp.action.ReadAction;
import hust.xujifa.readapp.helper.App;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReadPresenterImp implements ReadPresenter {
    private static final String TAG = ReadPresenterImp.class.getSimpleName();
    private ReadAction action;

    public ReadPresenterImp(ReadAction action) {
        this.action = action;
    }

    @Override
    public void getCatolog(int bookCode) {

        App.getKJAPI().getCatlog(bookCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"getCatlog()"+e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        //Log.d(TAG,s);
                        Document document= Jsoup.parse(s,"utf-8");
                        Element catalog= document.getElementsByAttributeValueMatching("class", "unstyled kjlist").first();

                        Matcher catalogMat= Pattern
                                .compile("\"><a href=\"([/\\w]+).*>(.*)</a>")
                                .matcher(catalog.toString());
                        while (catalogMat.find()){
                            Log.d(TAG,catalogMat.group(2));
                        }




                    }
                });
    }
}
