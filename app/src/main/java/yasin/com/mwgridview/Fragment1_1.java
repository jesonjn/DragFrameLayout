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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Fragment1_1 extends Fragment {

    Fragment1 fragment1;
    AppRunning appRunning;
    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appRunning = new AppRunning();
        View view = inflater.inflate(R.layout.fragment_fragment1_1, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        initData();
        listView.setAdapter(new MyAdapter(getActivity(),data));
        return view;
    }

    public void initData(){
        for(int i=0;i<30;i++){
            data.add("yasin"+i);
        }
    }

    public class MyAdapter extends BaseAdapter{

        List<String> data;
        Context context;
        LayoutInflater mInflater;

        public MyAdapter(Context context,List<String> data){
            this.data = data;
            this.context = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView = mInflater.inflate(R.layout.layout,null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(data.get(position));

            return convertView;
        }

        private class ViewHolder{
            TextView textView;
        }
    }



}
