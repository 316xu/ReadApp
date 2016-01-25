package hust.xujifa.readapp.helper;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by xujifa on 2016/1/24.
 */
public interface KJAPI {
    @GET("/book/{bookcode}")
    Observable<String> getBookinfo(
            @Path("bookcode") int bookcode
    );

    @GET("/member/{author}/home")
    Observable<String> getAuthorinfo(
            @Path("author") String author
    );

}
