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
import android.widget.Button;
import android.widget.LinearLayout;


public class Fragment2_1 extends Fragment {

    Fragment3 fragment3;
    LinearLayout yule1ll;
    AppRunning appRunning;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appRunning = new AppRunning();
        View view = inflater.inflate(R.layout.fragment_fragment1_1, container, false);
        yule1ll = (LinearLayout) getActivity().findViewById(R.id.yule1ll);
        fragment3 = new Fragment3();
        return view;
    }

}
