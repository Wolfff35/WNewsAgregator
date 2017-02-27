package com.wolfff.wnewsagregator;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wolfff.wnewsagregator.Fragments.ChannelFragment;
import com.wolfff.wnewsagregator.Fragments.GroupFragment;
import com.wolfff.wnewsagregator.Fragments.ListChannelsFragment;
import com.wolfff.wnewsagregator.Fragments.ListNewsFragment;
import com.wolfff.wnewsagregator.Fragments.NewsFragment;
import com.wolfff.wnewsagregator.Tools.Tools;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ListNewsFragment.ListNewsFragmentListener {
long mCurrentChannel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //FRAGMENTS
        ChannelFragment         channelFragment        = new ChannelFragment();
        GroupFragment           groupFragment          = new GroupFragment();
        ListChannelsFragment    listChannelsFragment   = new ListChannelsFragment();
        NewsFragment            newsFragment           = new NewsFragment();
        //==========================================================================================
        //make menu
        //===============================================
        Tools tools = new Tools();
        //tools.test_fillData(getApplicationContext());
        tools.makeMainMenu(getApplicationContext(),navigationView.getMenu());
        tools.updateAllNews(getApplicationContext());
        //update list channel in menu
        mCurrentChannel=1;
        ListNewsFragment listNewsFragment = ListNewsFragment.newInstance(mCurrentChannel);
        displayFragment(listNewsFragment);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_switch_theme) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_edit_groups) {

        } else if (id == R.id.nav_exit) {


        }else {
            Log.e("MENUITEM","id = "+id);
            //Tools tools = new Tools();
            //tools.showNews(getApplicationContext(),id);
            mCurrentChannel=id;
            ListNewsFragment listNewsFragment = ListNewsFragment.newInstance(mCurrentChannel);
            displayFragment(listNewsFragment);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onNewsSelected(long idNews,long idChannel) {
        NewsFragment newsFragment = NewsFragment.newInstance(idNews,idChannel);
        Log.e("NEWS_SEL","id news -  "+idNews+"; id channel - "+idChannel);
        displayFragment(newsFragment);


    }
}