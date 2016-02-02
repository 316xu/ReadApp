package hust.xujifa.readapp.helper;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by xujifa on 2016/1/23.
 */
public class App extends Application {
    private static App mInstance;
    private static API mAPI;
    private static KJAPI mKJAPI;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public void onCreate() {
        super.onCreate();
        mInstance=this;
        mAPI=ServiceGenerator.createService(API.class);
        mKJAPI=ServiceGenerator.createServiceKj(KJAPI.class);

    }

    public static synchronized API getAPI() {
        return mAPI;
    }
    public static synchronized KJAPI getKJAPI() {
        return mKJAPI;
    }
    public static synchronized App getInstance(){
        return mInstance;
    }
}
