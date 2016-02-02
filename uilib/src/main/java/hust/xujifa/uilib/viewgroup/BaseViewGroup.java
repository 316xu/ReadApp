package hust.xujifa.uilib.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by xujifa on 2016/1/31.
 */
public abstract class BaseViewGroup extends ViewGroup {

    protected final float scale = getResources().getDisplayMetrics().density;
    protected int width;
    protected int height;
    protected int desiredWidth ;
    protected int desiredHeight ;
    protected Context context;
    public BaseViewGroup(Context context) {
        this(context,null);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
        this.width=getMeasuredWidth();
        this.height=getMeasuredHeight();
    }

    protected abstract void init();
}
