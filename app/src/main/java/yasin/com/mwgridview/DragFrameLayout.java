package yasin.com.mwgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasin on 2016/5/30.
 */
public class DragFrameLayout extends BaseFrameLayout{
    public DragFrameLayout(Context context) {
        super(context);
    }

    public DragFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private List<View> mChildViewList = new ArrayList();
    private float mDeltaX;
    private float mDeltaY;
    private int mDragDelay;
    private int mDragEventX;
    private int mDragEventY;
    private View mDragView;


}
