package wackycodes.mvvm.multitablayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import wackycodes.mvvm.multitablayout.databinding.ActivityMainBinding;
import wackycodes.mvvm.multitablayout.fragment.FragmentHome;
import wackycodes.mvvm.multitablayout.model.ModelTabCount;
import wackycodes.mvvm.multitablayout.fragment.FragmentTabPlaceholder;
import wackycodes.mvvm.multitablayout.adaptor.AdapterSectionsPager;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , SearchView.OnQueryTextListener {

    private ActivityMainBinding activityMainBinding;

    private List<ModelTabCount> tabCounts = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdapterSectionsPager adapterSectionsPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setToolBar();
        setDrawerToggle();
        setTabLayout();

    }

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private MenuItem menuSearchItem;
    private void setDrawerToggle(){
        drawerLayout = activityMainBinding.drawerLayout;
        if ( toolbar == null )
            toolbar = activityMainBinding.include.appToolbar;
        // Add Menu Toggle Button ------------
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle( ActivityMain.this,
                drawerLayout, toolbar,   R.string.nav_open , R.string.nav_close );
        drawerLayout.addDrawerListener( drawerToggle );
        drawerToggle.syncState();
        // Navigation Item Click Event ----------------
        activityMainBinding.navViewSide.setNavigationItemSelectedListener( ActivityMain.this );
    }
    private void setToolBar(){
        if ( toolbar == null )
            toolbar = activityMainBinding.include.appToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setTabLayout(){

        // This is for Testing----------------
        tabCounts.add( new ModelTabCount("Tab 1") );
        tabCounts.add( new ModelTabCount("Tab Next 2") );
        tabCounts.add( new ModelTabCount("Next Tab 3") );
        tabCounts.add( new ModelTabCount("Tab Next  4") );
        tabCounts.add( new ModelTabCount("Tab Content 5") );
        tabCounts.add( new ModelTabCount("Tab Content 6") );
        tabCounts.add( new ModelTabCount("Tab Content 7") );

        // This is for Testing----------------
        fragmentList.add( FragmentHome.newInstance( "Num" ));
        fragmentList.add( FragmentTabPlaceholder.newInstance( 2, -1 ));
        fragmentList.add( FragmentTabPlaceholder.newInstance( 3, -1 ));
        fragmentList.add( FragmentTabPlaceholder.newInstance( 4, -1 ));
        fragmentList.add( FragmentTabPlaceholder.newInstance( 5, -1 ));
        fragmentList.add( FragmentTabPlaceholder.newInstance( 6, -1 ));
        fragmentList.add( FragmentTabPlaceholder.newInstance( 7, -1 ));

        adapterSectionsPager =
                new AdapterSectionsPager( getSupportFragmentManager(), tabCounts, fragmentList );
        tabLayout = activityMainBinding.include.tabLayout;
        viewPager = activityMainBinding.include.viewPager;
        viewPager.setAdapter(adapterSectionsPager);
        tabLayout.setupWithViewPager(viewPager);

        // Tab Action...
        setAction( tabLayout );
    }

    private void setAction(TabLayout tabLayout){
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout) );

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition() );
                adapterSectionsPager.setCurrentItem( tab.getPosition() );
                if ( menuSearchItem != null){
                    if( tab.getPosition() == 0){
                        menuSearchItem.setVisible(false);
                    }else{
                        menuSearchItem.setVisible(true);
                    }
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // To Home ToolBar Options..
        getMenuInflater().inflate(R.menu.menu_home_option, menu);

        menuSearchItem = menu.findItem(R.id.menu_search);
        // Hide Search Menu option at home page!
        menuSearchItem.setVisible( false );
        SearchView searchView = menuSearchItem.getActionView().findViewById(R.id.searchView);
        // Search Text Change or Submit Listener...!
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_notification){
            startActivity( new Intent( ActivityMain.this, ActivityNotifications.class ));
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public void onBackPressed() {
        if (tabLayout.getSelectedTabPosition() != 0){
            // Set Tab -> 1st
            viewPager.setCurrentItem( 0 );
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//          Call your code...
        if( fragmentList.get( tabLayout.getSelectedTabPosition() ) instanceof FragmentTabPlaceholder){
            ((FragmentTabPlaceholder)fragmentList.get( tabLayout.getSelectedTabPosition() )).onSearchQuery( query );
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // : Call your code...
        if( fragmentList.get( tabLayout.getSelectedTabPosition() ) instanceof FragmentTabPlaceholder){
            ((FragmentTabPlaceholder)fragmentList.get( tabLayout.getSelectedTabPosition() )).onSearchQuery( newText );
        }
        return false;
    }



}