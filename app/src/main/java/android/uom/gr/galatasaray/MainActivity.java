package android.uom.gr.galatasaray;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; //logt

    private SectionPageAdapter sectionsPageAdapter;

    private ViewPager ViewPagerr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "onCreate: Starting."); //logd
        sectionsPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        ViewPagerr = (ViewPager) findViewById(R.id.container);
        setupViewPager(ViewPagerr);

        TabLayout  tablelayout = (TabLayout) findViewById(R.id.tabs);
        tablelayout.setupWithViewPager(ViewPagerr);

    }

    public void setupViewPager(ViewPager viewpager){

        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new bar1(), "Matches");
        adapter.addFragment(new bar2(), "Table");
        adapter.addFragment(new bar3(), "Squad");

        viewpager.setAdapter(adapter);



    }

    public class SectionPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> FragmentList = new ArrayList<>();
        private final List<String> FragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title){
            FragmentList.add(fragment);
            FragmentTitleList.add(title);
        }

        public SectionPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentList.get(position);
        }

        @Override
        public int getCount() {
            return FragmentList.size();
        }
    }


}