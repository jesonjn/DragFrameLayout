package yasin.com.mwgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Yasin on 2016/5/31.
 */
class MyRelativeLayout extends RelativeLayout{

    boolean isLongPress = false;
    float lastx=0,lasty=0,cx=0,cy=0;
    RelativeLayout.LayoutParams params;
    Context context;

    public MyRelativeLayout(Context context) {
        super(context);
        initData(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context){
        this.context = context;
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastx = ev.getRawX();
                lasty = ev.getRawY();
                return false;
            case MotionEvent.ACTION_MOVE:
//                return true;
                cx =(lastx-ev.getRawX());
                cy = (lasty-ev.getRawY());
                if(Math.abs(cx)> ViewConfiguration.get(context).getScaledTouchSlop()&&Math.abs(cy)>ViewConfiguration.get(context).getScaledTouchSlop()){
                    return true;
                }else{
                    return false;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }



    public float getCurrentx(){
        return -cx;
    }
    public float getCurrenty(){
        return -cy;
    }
}
