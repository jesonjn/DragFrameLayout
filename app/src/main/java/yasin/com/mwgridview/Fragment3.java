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


public class Fragment3 extends Fragment {

    WebView web;
    Button btn;
    Fragment2_1 fragment2_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3,container,false);
//        fragment2_1 = new Fragment2_1();
//        btn = (Button) view.findViewById(R.id.to_down);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction("layout2");
//                getActivity().sendBroadcast(intent);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.yule2,fragment2_1).commit();
//            }
//        });

        return view;
    }

}
