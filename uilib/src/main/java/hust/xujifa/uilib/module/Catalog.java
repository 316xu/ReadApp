package hust.xujifa.uilib.module;

import java.util.List;

/**
 * Created by xujifa on 2016/2/1.
 */
public class Catalog {
    int chapter;
    String title;
    String content;
    boolean hasContent=false;

    public Catalog(int chapter, String title) {
        this.chapter = chapter;
        this.title = title;
    }

    public int getChapter() {

        return chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setContents(int widthN,int heightN,String content){

    }
    public List<String> getContents(int widthN,int heightN){



    }

    public void setContent(String content) {
        this.content = content;
    }
}
