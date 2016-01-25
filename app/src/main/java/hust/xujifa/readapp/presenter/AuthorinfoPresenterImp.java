package hust.xujifa.readapp.presenter;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void getAuthorinfo(String url)  {
        //Log.d(TAG,url);
        try {
            url=URLDecoder.decode(url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

                        Document authorDoc= Jsoup.parse(s, "utf-8");
                        Elements books= authorDoc.getElementById("content-1")
                                .getElementsByAttributeValue("class",
                                        "row kjlist-manage pad-bottom clearfix");
                        for(Element book:books){
                            Matcher bookMat= Pattern
                                    .compile("img.*src=\"(.*)\">[\\s\\S]+href=\"/book/(\\d+)\" title=\"\">[\\s\\S]+(.*)</a>")
                                    .matcher(book.toString());
                            if(bookMat.find()){
                                Log.d(TAG,bookMat.group(1));
                                Log.d(TAG,bookMat.group(2));
                                Log.d(TAG,bookMat.group(3));
                            }
                        }
                    }
                });
    }
}
