package hust.xujifa.readapp.helper;


import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by xujifa on 2015/11/4.
 */
public class ServiceGenerator {

    private  static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(ConstantValue.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    public static <S>S createService(Class<S>c){
        Retrofit retrofit = builder
                .build();
        return retrofit.create(c);

    }

    private static Retrofit.Builder kjbuilder=new Retrofit.Builder()
            .baseUrl(ConstantValue.URL_KJ)
            .addConverterFactory(new MyConverterFactory())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S>S createServiceKj(Class<S>c){
        return kjbuilder.build().create(c);
    }

}
