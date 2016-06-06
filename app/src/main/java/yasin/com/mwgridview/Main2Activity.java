package yasin.com.mwgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    MyRelativeLayout myLinearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myLinearLayout = (MyRelativeLayout) findViewById(R.id.mylinearlayout);
        myLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.e("yasin","Main2AC down");
                        break;
                }
                return false;
            }
        });
    }
}
