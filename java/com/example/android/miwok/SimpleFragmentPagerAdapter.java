package com.example.android.miwok;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Numbers", "Family", "Colours","Phrases","Translate" };
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else if(position ==2)
        {
            return new ColorsFragment();
        }
        else if(position==3)
            return new PhrasesFragment();
        else
            return new TranslateFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}

