package yasin.com.mwgridview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;


public class Fragment1_1 extends Fragment {

    Fragment1 fragment1;
    LinearLayout yule1ll;
    AppRunning appRunning;
    WebView newsWebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appRunning = new AppRunning();
        View view = inflater.inflate(R.layout.fragment_fragment1_1, container, false);
        newsWebView = (WebView) view.findViewById(R.id.news_webview);
        WebSettings webSettings = newsWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        newsWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        newsWebView.loadUrl("http://m.baidu.com/news");
        newsWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        yule1ll = (LinearLayout) getActivity().findViewById(R.id.yule1ll);
        return view;
    }

}
