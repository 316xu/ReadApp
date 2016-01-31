package hust.xujifa.readapp.helper;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by xujifa on 2016/1/24.
 */
public interface KJAPI {
    @GET("/book/{bookcode}")
    Observable<String> getBookinfo(
            @Path("bookcode") int bookcode
    );

    @GET("/member/{author}/bookman")
    Observable<String> getAuthorinfo(
            @Path("author") String author
    );
    @GET("/book/catalog/{bookcode}")
    Observable<String>getCatlog(
            @Path("bookcode") int bookCode
    );

    @GET("/search")
    Observable<String>search(
        @Query("keyword")String keyword
    );
}
