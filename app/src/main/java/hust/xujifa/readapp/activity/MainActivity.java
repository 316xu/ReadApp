package hust.xujifa.readapp.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.MainAction;
import hust.xujifa.readapp.adapter.MainViewPagerAdatper;
import hust.xujifa.readapp.helper.ConstantValue;
import hust.xujifa.readapp.presenter.MainPresenter;
import hust.xujifa.readapp.presenter.MainPresenterImp;
import hust.xujifa.uilib.view.BottomSheet;

/**
 * Created by xujifa on 2016/1/23.
 */
public class MainActivity extends BaseActivity implements MainAction {
    private static final String TAG=MainActivity.class.getSimpleName();
    private Context context;
    private MainPresenter presenter;

    private ViewPager mViewpager;
    private BottomSheet mBottomsheet;
    private FloatingActionButton fab_search;
    private int page=0;
    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        presenter=new MainPresenterImp(this);
        context=this;
        mViewpager= (ViewPager) findViewById(R.id.viewpager);
        fab_search= (FloatingActionButton) findViewById(R.id.fab_search);
        mBottomsheet= (BottomSheet) findViewById(R.id.bottom_sheet);

        Animation animation= AnimationUtils.loadAnimation(context,R.anim.fab_hide);
        fab_search.startAnimation(animation);
        mViewpager.setAdapter(new MainViewPagerAdatper(getFragmentManager()));
        mBottomsheet.setOnPosChangeListener(new BottomSheet.OnPosChangeListener() {
            @Override
            public void posChange(int pos) {
                mViewpager.setCurrentItem(pos-1);
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mBottomsheet.setPos(position+1);
                if(1==position){
                    Animation animation= AnimationUtils.loadAnimation(context,R.anim.fab_out);
                    fab_search.startAnimation(animation);
                }else if(page==1){
                    Animation animation= AnimationUtils.loadAnimation(context,R.anim.fab_hide);
                    fab_search.startAnimation(animation);
                }
                page=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    public void showlist(View v){
        Intent intent=new Intent(MainActivity.this,BooklistActivity.class);
        switch (v.getId()){
            case R.id.class1:
                intent.putExtra("type", ConstantValue.BookType.TOP);
                break;
            default:
                intent.putExtra("type", ConstantValue.BookType.TOP);

        }
        startActivity(intent);
    }
    public void search(View v){
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(context);
        final EditText editText=new EditText(this);
        editText.setContentDescription("search edittext");
        dialogBuilder.setView(editText)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                        intent.putExtra("keyword",editText.getText().toString());
                        context.startActivity(intent);
                    }
                })
        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.create().show();

    }


}
