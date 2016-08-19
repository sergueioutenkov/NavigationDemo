package com.serguei.navigationdemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        if (drawerLayout != null) {
            drawerLayout.addDrawerListener(toggle);
        }
        toggle.syncState();

        //Navigation View
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        //Set first fragment as default
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FirstFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Fragment Manager
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment;
        String title = null;

        switch (id)
        {
            case R.id.menuHome:
                fragment = new FirstFragment();
                title = "First Fragment";
                break;
            case R.id.menuBombas:
                fragment = new SecondFragment();
                title = "Second Fragment";
                break;
            default:
                fragment = new Fragment();
                break;
        }

        transaction.replace(R.id.container, fragment);
        transaction.commit();

        getSupportActionBar().setTitle(title);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
