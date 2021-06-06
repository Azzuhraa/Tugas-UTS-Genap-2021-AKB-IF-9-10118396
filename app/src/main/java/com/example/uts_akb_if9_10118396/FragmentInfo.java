package com.example.uts_akb_if9_10118396;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class FragmentInfo extends Fragment {
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_info, null, false);
        List<Fragment> list = new ArrayList<>();

        list.add(new Fragment_Page1());
        list.add(new Fragment_Page2());

        pager = view.findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list);
        pager.setAdapter(pagerAdapter);
        return view;
    }



}
