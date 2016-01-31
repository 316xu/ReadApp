package hust.xujifa.uilib.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import hust.xujifa.uilib.R;

/**
 * Created by xujifa on 2016/1/22.
 */
public class BottomSheet extends BaseView implements ValueAnimator.AnimatorUpdateListener {

    private static final String TAG = BottomSheet.class.getSimpleName();
    private int mColor, mTextColor;
    private Paint mTextPaint, mOutlinePaint;

    private Path mOutline;

    private int left, right, bottom, top;
    private float center, cleft, cright, middleY, outlineY;


    private String text1, text2, text3;
    private boolean mCreate = true;

    private int pos = 1;
    private float posX;
    private boolean firstDraw=true;
    private OnPosChangeListener listener;

    private Bitmap cacheBitmap;


    public BottomSheet(Context context) {
        super(context);
    }

    public BottomSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {

        TypedArray mTa = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomSheet, 0, 0);

        mColor = mTa.getColor(R.styleable.BottomSheet_background_color,
                getResources().getColor(R.color.colorPrimary));

        mTextColor = mTa.getColor(R.styleable.BottomSheet_text_color,
                getResources().getColor(R.color.colorPrimary));
        text1 = mTa.getString(R.styleable.BottomSheet_text1);
        text2 = mTa.getString(R.styleable.BottomSheet_text2);
        text3 = mTa.getString(R.styleable.BottomSheet_text3);
        mTa.recycle();

        mTextPaint = new Paint();
        mOutlinePaint = new Paint();

        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(this.scale * 16);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mOutlinePaint.setColor(mColor);
        mOutlinePaint.setStyle(Paint.Style.FILL);

        mOutline = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mCreate&&getRight()!=0) {
            left = getLeft();
            right = getRight();
            top = getTop();
            bottom = getBottom();
            center = (right - left) / 2f;
            cleft = (right - 3 * left) / 4f;
            cright = center + cleft;
            middleY = (bottom - top) / 2f;
            outlineY = (bottom - top) / 4f;
            posX = cleft;
            mCreate=false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        if(firstDraw&& right!=0){
            cacheBitmap=Bitmap.createBitmap(right, bottom, Bitmap.Config.ARGB_8888);
            Canvas cacheC=new Canvas(cacheBitmap);

            mOutline.reset();
            mOutline.moveTo(0, outlineY);
            mOutline.lineTo(right, outlineY);
            mOutline.lineTo(right, bottom);
            mOutline.lineTo(0, bottom);
            mOutline.lineTo(0, outlineY);

            cacheC.drawPath(mOutline, mOutlinePaint);

            cacheC.drawText(text1, cleft, middleY, mTextPaint);
            cacheC.drawText(text2, center, middleY, mTextPaint);
            cacheC.drawText(text3, cright, middleY, mTextPaint);

            canvas.drawBitmap(cacheBitmap,0,0,mOutlinePaint);
            firstDraw=false;
        }

        if(right!=0){
            canvas.drawBitmap(cacheBitmap,0,0,mTextPaint);
            mOutline.reset();
            mOutline.moveTo(0, 0);
            mOutline.lineTo(posX, outlineY);
            mOutline.lineTo(right, 0);
            mOutline.lineTo(right, outlineY);
            mOutline.lineTo(0, outlineY);
            mOutline.lineTo(0, 0);
            canvas.drawPath(mOutline, mOutlinePaint);


        }


       /* mOutline.reset();
        mOutline.moveTo(0, 0);
        mOutline.lineTo(posX, outlineY);
        mOutline.lineTo(right, 0);
        mOutline.lineTo(right, bottom - top);
        mOutline.lineTo(0, bottom-top);
        mOutline.lineTo(0, 0);
        canvas.drawPath(mOutline, mOutlinePaint);

        canvas.drawText(text1, cleft, middleY, mTextPaint);
        canvas.drawText(text2, center, middleY, mTextPaint);
        canvas.drawText(text3, cright, middleY, mTextPaint);*/


    }

    float startX, startPos;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startPos = posX;
                startX = event.getX();
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                if (Math.abs(x - startX) < 10 * scale) {
                    endMove(x);
                } else {
                    endMove(posX);
                }
                return false;
            case MotionEvent.ACTION_MOVE:
                posX = startPos - (event.getX() - startX) / 3.5f;
                posX = posX < cleft ? cleft - 20 : (posX > cright + 20 ? cright + 20 : posX);

                invalidate();

                return true;
            default:
                return false;
        }
    }

    private float animX, scaleX;

    private void endMove(float nowX) {

        pos = nowX < (cleft + center) / 2 ? 1 : (nowX < (center + cright) / 2 ? 2 : 3);

        if (listener != null) {
            listener.posChange(pos);
        }
        animX = pos == 1 ? cleft : (pos == 2 ? center : cright);

        scaleX = (animX - posX) / 20;
        bV = 0;
        ValueAnimator animator = ValueAnimator.ofInt(0, 20);

        animator.setDuration(500);
        animator.addUpdateListener(this);
        animator.start();
    }

    int bV = 0;

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();


        posX += scaleX * (value - bV);
        bV = value;
        invalidate();
    }
    public void setPos(int animpos){
        animX = animpos == 1 ? cleft : (animpos == 2 ? center : cright);

        scaleX = (animX - posX) / 20;
        bV = 0;
        ValueAnimator animator = ValueAnimator.ofInt(0, 20);

        animator.setDuration(500);
        animator.addUpdateListener(this);
        animator.start();
    }
    public interface OnPosChangeListener {
        void posChange(int pos);
    }

    public void setOnPosChangeListener(OnPosChangeListener listener) {
        this.listener = listener;
    }
}
