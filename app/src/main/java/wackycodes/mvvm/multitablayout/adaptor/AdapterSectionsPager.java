package wackycodes.mvvm.multitablayout.adaptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import wackycodes.mvvm.multitablayout.fragment.FragmentTabPlaceholder;
import wackycodes.mvvm.multitablayout.model.ModelTabCount;

public class AdapterSectionsPager extends FragmentPagerAdapter {

    private List<ModelTabCount> tabCounts;
    private List<Fragment> fragmentList;

    public AdapterSectionsPager(@NonNull FragmentManager fm, List<ModelTabCount> tabCounts, List<Fragment> fragmentList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCounts = tabCounts;
        this.fragmentList = fragmentList;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragmentList.get( position );
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabCounts.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return tabCounts.size();
    }

    // To Lazy Loading....
    public void setCurrentItem( int position ){
        if( fragmentList.get( position ) instanceof FragmentTabPlaceholder){
            ((FragmentTabPlaceholder)fragmentList.get( position)).setUIData( );
        }
    }

}