package com.yesh.hotelorder.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.auth.IdpResponse;
import com.yesh.hotelorder.R;

import java.io.File;
import java.util.ArrayList;

import static com.firebase.ui.auth.ui.ExtraConstants.EXTRA_IDP_RESPONSE;

/**
 * Created by yesh on 11/12/17.
 */

public class HomeScreen extends AppCompatActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;

    public static Intent createIntent(
            Context context,
            IdpResponse idpResponse) {

        Intent startIntent = new Intent();
        if (idpResponse != null) {
            startIntent.putExtra(EXTRA_IDP_RESPONSE, idpResponse);
        }

        return startIntent.setClass(context, HomeScreen.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
//                case 0:
//                    // The first section of the app is the most interesting -- it offers
//                    // a launchpad into the other demonstrations in this example application.
//                    return new LaunchpadSectionFragment();

                default:
                    // The other sections of the app are dummy placeholders.
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }

    /**
     * A fragment that launches other parts of the demo application.
     */
//    public static class LaunchpadSectionFragment extends Fragment {
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);
//
//            // Demonstration of a collection-browsing activity.
//            rootView.findViewById(R.id.demo_collection_button)
//                    .setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
//                            startActivity(intent);
//                        }
//                    });
//
//            // Demonstration of navigating to external activities.
//            rootView.findViewById(R.id.demo_external_activity)
//                    .setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // Create an intent that asks the user to pick a photo, but using
//                            // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
//                            // the application from the device home screen does not return
//                            // to the external activity.
//                            Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
//                            externalActivityIntent.setType("image/*");
//                            externalActivityIntent.addFlags(
//                                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                            startActivity(externalActivityIntent);
//                        }
//                    });
//
//            return rootView;
//        }
//    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";
        private final String image_titles[] = {
                "Img1",
                "Img2",
                "Img3",
                "Img4",
                "Img5",
                "Img6",
                "Img7",
                "Img8",
                "Img9",
                "Img10",
                "Img11",
                "Img12",
                "Img13",
        };

        private final Integer image_ids[] = {
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
                R.drawable.com_facebook_button_icon_blue,
        };

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            RecyclerView recyclerView = rootView.findViewById(R.id.imageGallery);
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            ArrayList<CreateList> createLists = prepareData();
            MyAdapter adapter = new MyAdapter(getContext(), createLists);
            recyclerView.setAdapter(adapter);
            return rootView;
        }

//        private ArrayList<CreateList> prepareData() {
//            ArrayList<CreateList> theimage = new ArrayList<>();
//            String path = Environment.getRootDirectory().toString();
//            File f = new File(path);
//            File file[] = f.listFiles();
//            for (int i=0; i < file.length; i++)
//            {
//                CreateList createList = new CreateList();
//                createList.setImage_Location(file[i].getName());
//                theimage.add(createList);
//            }
//            return theimage;
//        }

        private ArrayList<CreateList> prepareData() {
            ArrayList<CreateList> theimage = new ArrayList<>();
            for (int i = 0; i < image_titles.length; i++) {
                CreateList createList = new CreateList();
                createList.setImage_title(image_titles[i]);
                createList.setImage_ID(image_ids[i]);
                theimage.add(createList);
            }
            return theimage;
        }
    }
}
