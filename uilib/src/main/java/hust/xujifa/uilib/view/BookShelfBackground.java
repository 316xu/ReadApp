package hust.xujifa.uilib.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by xujifa on 2016/1/22.
 */
public class BookShelfBackground extends BaseView {
    Paint mLinePaint;

    Path mPath;
    public BookShelfBackground(Context context) {
        super(context);
    }

    public BookShelfBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookShelfBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        mLinePaint=new Paint();
        mLinePaint.setStyle(Paint.Style.FILL);

    }
}
