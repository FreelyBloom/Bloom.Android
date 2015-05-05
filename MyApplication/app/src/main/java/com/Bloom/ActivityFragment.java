package com.Bloom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The child fragment is no different than any other fragment other than it is now being maintained by
 * a child FragmentManager.
 */
public class ActivityFragment extends Fragment {

    public static ActivityFragment newInstance(String text) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg",text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_activity, container, false);
        return root;
    }


}