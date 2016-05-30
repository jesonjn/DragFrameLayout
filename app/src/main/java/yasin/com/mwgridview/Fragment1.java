package yasin.com.mwgridview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class Fragment1 extends Fragment {

    WebView web;
    Button btn;
    Fragment1_1 fragment1_1;
    int mode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1,container,false);
        mode = getArguments().getInt("mode");
        fragment1_1 = new Fragment1_1();
        btn = (Button) view.findViewById(R.id.to_down);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("layout1");
                getActivity().sendBroadcast(intent);//每一个点击事件都要发送一个定位布局的广播
                Bundle bundle = new Bundle();
                bundle.putInt("mode",mode);
                fragment1_1.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace((mode==1?R.id.yule1:R.id.yule2),fragment1_1).commit();
            }
        });

        return view;
    }



}
