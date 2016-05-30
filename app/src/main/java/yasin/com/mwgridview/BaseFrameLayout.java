package yasin.com.mwgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Yasin on 2016/5/30.
 */
public class BaseFrameLayout extends FrameLayout{

    private boolean mIsEnable = true;

    public BaseFrameLayout(Context context) {
        super(context);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setmIsEnable(boolean paramEnable){
        this.mIsEnable = paramEnable;
    }

    /*
    * 手势事件是否继续传递
    * true继续监听传递
    * false不进行传递
    *例如：先接受down,然后出发该方法，如果返回true，进行对后续事件进行处理
    * 如果返回false，则对后续事件不处理（例如move,up...）
    * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(mIsEnable)
            return super.dispatchTouchEvent(ev);
        return true;
    }
}
