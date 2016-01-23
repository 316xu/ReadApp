package hust.xujifa.readapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.MainAction;
import hust.xujifa.readapp.adapter.MainViewPagerAdatper;
import hust.xujifa.readapp.presenter.MainPresenter;
import hust.xujifa.readapp.presenter.MainPresenterImp;
import hust.xujifa.uilib.view.BottomSheet;

/**
 * Created by xujifa on 2016/1/23.
 */
public class MainActivity extends BaseActivity implements MainAction {
    private static final String TAG=MainActivity.class.getSimpleName();

    private MainPresenter presenter;

    private ViewPager mViewpager;
    private BottomSheet mBottomsheet;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        presenter=new MainPresenterImp(this);
        mViewpager= (ViewPager) findViewById(R.id.viewpager);
        mBottomsheet= (BottomSheet) findViewById(R.id.bottom_sheet);

        mViewpager.setAdapter(new MainViewPagerAdatper(getSupportFragmentManager()));
        mBottomsheet.setOnPosChangeListener(new BottomSheet.OnPosChangeListener() {
            @Override
            public void posChange(int pos) {
                mViewpager.setCurrentItem(pos-1);
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mBottomsheet.setPos(position+1);
            }

            @Override
            public void onPageSelected(int position) {
                mBottomsheet.setPos(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void showlist(View v){

    }


}
