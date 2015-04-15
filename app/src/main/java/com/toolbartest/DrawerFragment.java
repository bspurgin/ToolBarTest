package com.toolbartest;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;
    private List<ListObject> data = new ArrayList<>();
    private OnNavigationChangeListener mListener;

    public interface OnNavigationChangeListener {
        public void onNavigationChange(String label);
    }

    public DrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Activity parent = getActivity();

        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_drawer, container, false);
        mRecyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);

        data.add(new ListObject("My Courses", R.drawable.ic_action_action_home));
        data.add(new ListObject("Assignments", R.drawable.ic_action_ico_assignment));
        data.add(new ListObject("Scores", R.drawable.ic_action_ico_perf));
        data.add(new ListObject("Notes", R.drawable.ic_action_ico_notes));
        data.add(new ListObject("Table of Contents",R.drawable.ic_action_ico_toc));
        data.add(new ListObject("Glossary", R.drawable.ic_action_ico_glossary));

        // NOTE: We may not need to send the click listener for a ListView.
        // Need more research on RecyclerView.
        adapter = new RecyclerAdapter(parent, data, this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(parent));

        // Looking for the callback instance
        mListener = (null == mListener) && (parent instanceof OnNavigationChangeListener)
                ? (OnNavigationChangeListener) parent : mListener;

        return layout;
    }

    public void setUp(DrawerLayout layout, Toolbar toolbar) {
        mDrawerLayout = layout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),layout, toolbar, R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Revel");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Home");

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (null != mListener) {
            // NOTE: This is UGLY. Thanks to RecyclerView.
            // Need to optimize this!
            TextView textView = (TextView) v.findViewById(R.id.listText);
            mListener.onNavigationChange(textView.getText().toString());
        } else {
            Log.e("TEST", "No Listener!");
        }
    }
}
