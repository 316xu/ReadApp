package hust.xujifa.uilib.viewgroup;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hust.xujifa.uilib.module.Catalog;

/**
 * Created by xujifa on 2016/1/31.
 */
public class ReadPage extends BaseViewGroup implements ValueAnimator.AnimatorUpdateListener{

    static final String TAG=ReadPage.class.getSimpleName();
    TextView page0;
    TextView page1;
    TextView page2;
    int chapter=0;
    int widthN,heightN;
    int center=1;
    boolean down=false;
    boolean moving=false;

    List<Catalog>catalogs;
    ChapterChangedListener ccListener;
    ChapterNeedListener cnListener;
    List<String>content0;
    List<String>content1;
    List<String>content2;
    public ReadPage(Context context) {
        super(context);
    }
    public ReadPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReadPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    @Override
    protected void init() {

        page1=new TextView(context);
        page2=new TextView(context);
        page0=new TextView(context);
        page0.setText("Page1");
        page1.setText("Page2");
        page2.setText("Page3");
        addView(page1);
        addView(page2);
        addView(page0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChild(page1, widthMeasureSpec, heightMeasureSpec);
        measureChild(page2, widthMeasureSpec, heightMeasureSpec);
        measureChild(page0, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        page0.layout(-width, 0, 0, height);
        page1.layout(0, 0, width, height);
        page2.layout(width, 0, 2 * width, height);
        if(width>0){
            widthN= (int) (getWidth()/page0.getTextSize());
            heightN=getHeight()/page0.getLineHeight();
            Log.d(TAG,"onLayout()+heightN:"+heightN+",widthN:"+widthN);
        }
    }
    private void relayout(){
        switch (center){
            case 0:
                page0.layout((int) offsetX,0, (int) (width+offsetX),height);
                page1.layout((int)(width+offsetX),0, (int) (2*width+offsetX),height);
                page2.layout((int)(-width+offsetX),0, (int) (offsetX),height);
                break;
            case 1:
                page0.layout((int) (-width+offsetX),0, (int) offsetX,height);
                page1.layout((int)offsetX,0, (int) (width+offsetX),height);
                page2.layout((int)(width+offsetX),0, (int) (2*width+offsetX),height);
                break;
            case 2:
                page1.layout((int) (-width+offsetX),0, (int) offsetX,height);
                page2.layout((int) offsetX, 0, (int) (width + offsetX), height);
                page0.layout((int)(width+offsetX),0, (int) (2*width+offsetX),height);

        }


    }


    float startX;
    float offsetX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                moving=true;
                offsetX=(event.getX()-startX)/1.5f;
                Log.d(TAG,"onTouchEvent()+offsetX:"+offsetX);
                relayout();
                return true;
            case MotionEvent.ACTION_DOWN:
                down=true;
                startX=event.getX();

                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if(!moving){
                    if(event.getX()<width/3){
                        offsetX=width;
                    }else if(event.getX()>width*2/3){
                        offsetX=-width;
                    }
                }
                moving=false;
                down=false;
                homing();
                return false;
            default:
                return false;
        }
    }
    private void homing(){
        if(Math.abs(offsetX)>450){
            if(offsetX<0){
                center=(center+1)>2?0:center+1;
                offsetX=offsetX+width;
            }else{
                center=(center-1)<0?2:center-1;
                offsetX=offsetX-width;
            }
        }
        ValueAnimator va=ValueAnimator.ofInt(0,20);
        startOffsetX=offsetX;
        va.setDuration(300);
        va.addUpdateListener(this);
        va.start();
    }



    public void readReady(){
        String str=catalogs.get(chapter);
    }


    float startOffsetX;
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value= (int) animation.getAnimatedValue();

        offsetX=startOffsetX-startOffsetX*value/20;
        relayout();
    }

    public interface ChapterChangedListener{

    }
    public interface ChapterNeedListener{
        String needChapter(int chapter);
    }
    public void setReadPageListener(ChapterChangedListener ccListener,ChapterNeedListener cnListener){
        this.ccListener=ccListener;
        this.cnListener=cnListener;
    }
}
