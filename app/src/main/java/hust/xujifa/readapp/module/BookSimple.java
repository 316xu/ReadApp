package hust.xujifa.readapp.module;

/**
 * Created by xujifa on 2015/11/30.
 */
public class BookSimple {
    protected int id;
    protected int bookcode;
    protected String author;
    protected String authorcode;
    protected String title;
    protected String updatetitle;
    protected int updatecode;
    protected String updatetime;
    protected int wordcount;
    protected String photourl;
    protected String introduction;

    public void setRead(int read) {
        this.read = read;
    }

    public int getRead() {

        return read;
    }

    protected int read;
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {

        return introduction;
    }

    public int getId() {

        return id;
    }

    public int getRank() {
        return id;
    }

    public int getBookcode() {
        return bookcode;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorcode() {
        return authorcode;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdatetitle() {
        return updatetitle;
    }

    public int getUpdatecode() {
        return updatecode;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public int getWordcount() {
        return wordcount;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookcode(int bookcode) {
        this.bookcode = bookcode;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorcode(String authorcode) {
        this.authorcode = authorcode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdatetitle(String updatetitle) {
        this.updatetitle = updatetitle;
    }

    public void setUpdatecode(int updatecode) {
        this.updatecode = updatecode;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public void setWordcount(int wordcount) {
        this.wordcount = wordcount;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
