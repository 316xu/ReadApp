package hust.xujifa.readapp.presenter;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hust.xujifa.readapp.action.SearchAction;
import hust.xujifa.readapp.helper.App;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    private static final String TAG = SearchPresenterImp.class.getSimpleName();
    private SearchAction action;

    public SearchPresenterImp(SearchAction action) {
        this.action = action;
    }

    @Override
    public void search(String keyword) {
        App.getKJAPI().search(keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"search()"+e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Document doc= Jsoup.parse(s,"utf-8");

                        Elements books=doc.getElementsByAttributeValue("class"
                                ,"row pad-right-desktop double border-bottom gap-bottom");
                        for(Element book:books){
                            Matcher matcher= Pattern
                                    .compile("src=\"([/:.\\w]+)[\\s\\S]+book/(\\d+).*?>(.*)</a>")
                                    .matcher(book.toString());
                            if(matcher.find()){
                                Log.d(TAG,"search()"+matcher.group(1));
                                Log.d(TAG,"search()"+matcher.group(2));
                                Log.d(TAG,"search()"+matcher.group(3));
                            }
                        }
                    }
                });
    }
}
