package com.toolbartest;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity{

    private Toolbar mToolbar;
    private TextView mTextView;
    private DrawerLayout mDrawer;
    final private static int COURSE_HOME = 0;
    final private static int ASSIGNMENT_SCREEN = 1;
    final private static int SCORES_SCREEN = 2;
    final private static int NOTES_SCREEN = 3;
    final private static int TOC_SCREEN = 4;
    final private static int GLOSSARY = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_bar);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mTextView = (TextView) findViewById(R.id.text);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
        DrawerFragment drawerFragment = (DrawerFragment)getSupportFragmentManager().findFragmentById(R.id.drawer_fragment);
        
        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), mToolbar);
    }

    public void switchFragment(int position){
        switch (position){
            case COURSE_HOME:
                mTextView.setText("Course Home");
                break;

            case ASSIGNMENT_SCREEN:
                mTextView.setText("Assignments");
                break;

            case SCORES_SCREEN:
                mTextView.setText("Scores");
                break;

            case NOTES_SCREEN:
                mTextView.setText("Notes");
                break;

            case TOC_SCREEN:
                mTextView.setText("Table of Contents");
                break;

            case GLOSSARY:
                mTextView.setText("Glossary");
                break;
        }
            mDrawer.closeDrawers();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
