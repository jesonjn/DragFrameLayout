package yasin.com.mwgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Yasin on 2016/5/31.
 */
public class MyLinearLayout extends LinearLayout{

    boolean isLongPress = false;
    float lastx=0,lasty=0;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastx = ev.getRawX();
                lasty = ev.getRawY();
                return false;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs((lastx-ev.getRawX()))>2&&Math.abs((lasty-ev.getRawY()))>2){
                    return true;
                }else{
                    return false;
                }
            case MotionEvent.ACTION_UP:
                break;
        }


        return super.onInterceptTouchEvent(ev);
    }
}
