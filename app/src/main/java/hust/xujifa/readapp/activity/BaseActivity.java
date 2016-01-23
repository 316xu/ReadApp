package hust.xujifa.readapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xujifa on 2016/1/23.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getIntent().getExtras();
        init();
    }

    public abstract void init();



}
