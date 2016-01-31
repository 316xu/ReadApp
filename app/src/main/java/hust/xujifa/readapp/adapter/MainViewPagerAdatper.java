package hust.xujifa.readapp.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;



import android.util.Log;

import hust.xujifa.readapp.fragment.ClassficationFragment;
import hust.xujifa.readapp.fragment.SettingsFragment;
import hust.xujifa.readapp.fragment.TestFragment;
/**
 * Created by xujifa on 2016/1/23.
 */
public class MainViewPagerAdatper extends FragmentPagerAdapter {


    public MainViewPagerAdatper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new ClassficationFragment();
            case 2:
                return new SettingsFragment();
            default:
                return new TestFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
