package yasin.com.mwgridview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    Button btn1,btn2,btn3,btn4;//右侧图标
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;//以上为不同功能的fragment
    Fragment1_1 fragment1_1;
    LinearLayout yule1ll,yule2ll;//两个fragment父类布局，包含顶部黑框和framelayout
    int lastX,lastY,cX,cY,screenWidth,screenHeight;//lastx,y是用来保存拖放停止后的状态,screenWidth,Height代表屏幕宽高
    AppRunning appRunning;//存放两个linearlayout的位置信息和是否可用
    MyReceiver myReceiver;//接受位置信息
    RelativeLayout main;//没用
    int id1,id2;//表示是哪个图标启动的哪个应用，以便关掉应用后知道复原哪个图标
    Button close1,close2;//两个顶部红叉
    FrameLayout yule1,yule2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initLayout();
        IntentFilter filter = new IntentFilter();
        filter.addAction("layout1");
        filter.addAction("layout2");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver,filter);



        appRunning = new AppRunning();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

    }

    private void initLayout() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getEnable(btn1.getId());
                if(id==0){
                    Toast.makeText(MainActivity.this,"最多开启两个应用",Toast.LENGTH_LONG).show();
                }else{
                    //fragment1 = new Fragment1();
                    fragment1_1 = new Fragment1_1();
                    appRunning.initData();
                    setLayout((id==R.id.yule1?1:2));
                    Bundle bundle = new Bundle();
                    bundle.putInt("mode",(id==R.id.yule1?1:2));
                    fragment1_1.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(id,fragment1_1).commit();
                    btn1.setVisibility(View.GONE);
                    findViewById((id==R.id.yule1?R.id.yule1ll:R.id.yule2ll)).setVisibility(View.VISIBLE);
                }

            }
        });
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getEnable(btn2.getId());
                if(id==0){
                    Toast.makeText(MainActivity.this,"最多开启两个应用",Toast.LENGTH_LONG).show();
                }else{
                    fragment2 = new Fragment2();
                    appRunning.initData();
                    setLayout((id==R.id.yule1?1:2));
                    getSupportFragmentManager().beginTransaction().replace(id,fragment2).commit();
                    btn2.setVisibility(View.GONE);
                    findViewById((id==R.id.yule1?R.id.yule1ll:R.id.yule2ll)).setVisibility(View.VISIBLE);
                }
            }
        });
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getEnable(btn3.getId());
                if(id==0){
                    Toast.makeText(MainActivity.this,"最多开启两个应用",Toast.LENGTH_LONG).show();
                }else{
                    fragment3 = new Fragment3();
                    appRunning.initData();
                    setLayout((id==R.id.yule1?1:2));
                    getSupportFragmentManager().beginTransaction().replace(id,fragment3).commit();
                    btn3.setVisibility(View.GONE);
                    findViewById((id==R.id.yule1?R.id.yule1ll:R.id.yule2ll)).setVisibility(View.VISIBLE);
                }
            }
        });
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getEnable(btn4.getId());
                if(id==0){
                    Toast.makeText(MainActivity.this,"最多开启两个应用",Toast.LENGTH_LONG).show();
                }else{
                    fragment4 = new Fragment4();
                    appRunning.initData();
                    setLayout((id==R.id.yule1?1:2));
                    getSupportFragmentManager().beginTransaction().replace(id,fragment4).commit();
                    btn4.setVisibility(View.GONE);
                    findViewById((id==R.id.yule1?R.id.yule1ll:R.id.yule2ll)).setVisibility(View.VISIBLE);
                }
            }
        });
        yule1ll = (LinearLayout) findViewById(R.id.yule1ll);
        yule1ll.setOnTouchListener(this);
        yule2ll = (LinearLayout) findViewById(R.id.yule2ll);
        yule2ll.setOnTouchListener(this);
        yule1 = (FrameLayout) findViewById(R.id.yule1);
        yule2 = (FrameLayout) findViewById(R.id.yule2);
        close1 = (Button) findViewById(R.id.close1);
        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(id1).setVisibility(View.VISIBLE);
                yule1ll.setVisibility(View.GONE);
                appRunning.w1=true;
            }
        });
        close2 = (Button) findViewById(R.id.close2);
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(id2).setVisibility(View.VISIBLE);
                yule2ll.setVisibility(View.GONE);
                appRunning.w2=true;
            }
        });
    }

    /*
    * 监听滑动事件
    * */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getRawX()-lastX);
                int dy = (int) (event.getRawY()-lastY);

                int left = v.getLeft()+dx;
                int top = v.getTop()+dy;
                int right = v.getRight()+dx;
                int bottom = v.getBottom()+dy;

                if(left<0){
                    left = 0;
                    right = v.getWidth();
                }
                if(right>screenWidth){
                    right = screenWidth;
                    left = right-v.getWidth();
                }
                if(top<0){
                    top=0;
                    bottom = v.getHeight();
                }
                if(bottom > screenHeight){
                    bottom=screenHeight;
                    top = bottom-v.getHeight();
                }
                v.layout(left,top,right,bottom);
                if(v.getId()==R.id.yule1ll){
                    appRunning.left1 = left;
                    appRunning.top1=top;
                    appRunning.bottom1=bottom;
                    appRunning.right1 = right;
                }else if(v.getId()==R.id.yule2ll){
                    appRunning.left2 = left;
                    appRunning.top2=top;
                    appRunning.bottom2=bottom;
                    appRunning.right2 = right;
                }
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent();
                if(v.getId()==R.id.yule1ll){
                    intent.setAction("layout1");
                }else if(v.getId()==R.id.yule2ll){
                    intent.setAction("layout2");
                }
                sendBroadcast(intent);
                break;
        }
        return true;
    }

    private class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("layout1")){
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(770,515);
                params.setMargins(appRunning.left1,appRunning.top1,0,0);
                yule1ll.setLayoutParams(params);
            }else if(action.equals("layout2")){
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(770,515);
                params.setMargins(appRunning.left2,appRunning.top2,0,0);
                yule2ll.setLayoutParams(params);
            }
        }
    }

    /*
    * 初始化两个linearlayout位置信息
    * mode 1-代表是上面的linearlayout,2代表下面的linearlayout
    * */
    public void setLayout(int mode){
        if(mode==1){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(770,515);
            params.setMargins(appRunning.left1,appRunning.top1,0,0);
            yule1ll.setLayoutParams(params);
        }else if(mode == 2){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(770,515);
            params.setMargins(appRunning.left2,appRunning.top2,0,0);
            yule2ll.setLayoutParams(params);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    /*
    * 获取哪个framelayout可用
    * 输入id 代表是哪个button传进来的
    * */
    public int getEnable(int id){
        if(appRunning.w1){
            appRunning.w1=false;
            id1 = id;
            return R.id.yule1;
        }else if(appRunning.w2){
            appRunning.w2=false;
            id2 = id;
            return R.id.yule2;
        }else{
            return 0;
        }
    }

}
