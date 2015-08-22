package com.blmsr.manager.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blmsr.manager.CategoriesFragment;
import com.blmsr.manager.CategoryConstants;
import com.blmsr.manager.CategoryEditorFragment;

/**
 * Created by LakshmiMadhav on 8/22/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        Bundle bundle = new Bundle();
        String tab = "";
        Fragment aFragment = null;
        switch (index) {
            case 0:
                // Top Rated fragment activity
                tab = CategoryConstants.CATEGORIES;
                aFragment = new CategoriesFragment();
                break;
            case 1:
                // Top Rated fragment activity
                tab = CategoryConstants.CATEGORY_EDITOR;
                aFragment = new CategoryEditorFragment();
                break;
        }

        bundle.putString(CategoryConstants.TAB_TYPE, tab);
        aFragment.setArguments(bundle);

        return aFragment;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
}
