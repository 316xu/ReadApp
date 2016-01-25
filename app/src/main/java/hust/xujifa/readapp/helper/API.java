package hust.xujifa.readapp.helper;

import java.util.List;

import hust.xujifa.readapp.module.BookSimple;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by xujifa on 2016/1/23.
 */
public interface API {

    @GET("kjread/{folder}/{file}")
    Observable<List<BookSimple>>getbooklist(
            @Path("folder")String folder,
            @Path("file") String filename
    );


}
