package hust.xujifa.readapp.presenter;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hust.xujifa.readapp.action.BookinfoAction;
import hust.xujifa.readapp.helper.App;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xujifa on 2016/1/24.
 */
public class BookinfoPresenterImp implements BookinfoPresenter {
    private BookinfoAction action;
    private static final String TAG=BookinfoPresenterImp.class.getSimpleName();
    public BookinfoPresenterImp(BookinfoAction action){
        this.action=action;
    }

    @Override
    public void getbookinfo(int bookcode) {
        App.getKJAPI().getBookinfo(bookcode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"getbookinfo() "+e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                       // Log.d(TAG,"getbookinfo()"+s);
                        Document document= Jsoup.parse(s,"utf-8");
                        String title;
                        String coverUrl;
                        Element titleElm=document.getElementsByAttributeValueContaining("class"
                                , "kjbooktitle one small-tablet mobile half").first();
                        Matcher matcher= Pattern.compile("[\\s\\S]+sym_width\">(.*)<span")
                                .matcher(titleElm.toString());
                        if(matcher.find()){
                            title=matcher.group(1);
                           // Log.d(TAG,"getbookinfo()"+title);
                        }

                        Elements elements = document.getElementsByAttributeValueContaining("class",
                                "kjlist-manage row hide-on-small-tablet gap-bottom-mobile recommend_container");
                        Element element = elements.get(0);
                        Matcher matcher2 = Pattern.compile(
                                "data-original=\"(.*)\"[\\s\\S]+作品字数：</span>(\\d+)[\\s\\S]+/read/book/\\d+/\\d+\">(.*)</a>[\\s\\S]+hide-on-mobile\">([\\s\\S]+)</div>"
                        ).matcher(element.toString());
                        if(matcher2.find()){
                          //  Log.d(TAG,matcher2.group(1));
                           // Log.d(TAG,matcher2.group(2));
                           // Log.d(TAG,matcher2.group(3));
                          //  Log.d(TAG,matcher2.group(4));
                        }
                        Element authroElm=document.getElementsByAttributeValueContaining("class",
                                "kjtitle kjrevision-title clearfix following").first();
                       // Log.d(TAG,authroElm.toString());
                        Matcher authroMatcher=Pattern
                                .compile("作者[\\s\\S]+member/(.*)/home\">(.*)</a>[\\s\\S]+<a")
                                .matcher(authroElm.toString());
                        if(authroMatcher.find()){
                            Log.d(TAG,authroMatcher.group(1));
                            action.setAuthorUrl(authroMatcher.group(1));
                            Log.d(TAG,authroMatcher.group(2));
                        }

                    }
                });
    }
}
