package hust.xujifa.readapp.presenter;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hust.xujifa.readapp.action.ReadAction;
import hust.xujifa.readapp.helper.App;
import hust.xujifa.uilib.module.Catalog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReadPresenterImp implements ReadPresenter {
    private static final String TAG = ReadPresenterImp.class.getSimpleName();
    private ReadAction action;
    List<Catalog> catalogs;
    int chapter = 0;
    int bookCode;



    public ReadPresenterImp(ReadAction action, List<Catalog> catalogs) {
        this.catalogs = catalogs;
        this.action = action;
    }

    @Override
    public void getCatalog(int bookCode) {
        this.bookCode = bookCode;

        App.getKJAPI().getCatlog(bookCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        action.catalogReady();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getCatlog()" + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        //Log.d(TAG,s);
                        Document document = Jsoup.parse(s, "utf-8");
                        Element catalog = document.getElementsByAttributeValueMatching("class", "unstyled kjlist").first();

                        Matcher catalogMat = Pattern
                                .compile("<a href=.*?\\d+\\/(\\d+).*?>(.*)<\\/a>")
                                .matcher(catalog.toString());
                        while (catalogMat.find()) {

                            catalogs.add(new Catalog(Integer.valueOf(catalogMat.group(1))
                                    , catalogMat.group(2)));
                        }
                    }
                });
    }

    @Override
    public void getContent() {
        App.getKJAPI().getContent(bookCode, catalogs.get(chapter).getChapter())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        action.readReady();
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getContent()+error:" + e.toString());
                    }
                    @Override
                    public void onNext(String s) {
                        StringBuilder contentStr = new StringBuilder();
                        Document doc = Jsoup.parse(s, "utf-8");

                        Element content = doc.getElementById("endText");

                        Matcher contentMat = Pattern.compile("<p>(.*)<\\/p>")
                                .matcher(content.toString());
                        while (contentMat.find()) {
                            contentStr.append(contentMat.group(1) + "\n");
                        }
                        Log.d(TAG, contentStr.toString());
                        catalogs.get(chapter++).setContent(contentStr.toString());
                    }
                });
    }


    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }



}
