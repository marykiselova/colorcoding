package lv.kiselova.colorcoding;

import java.util.ArrayList;

import lv.kiselova.colorcoding.dao.CardDAO;
import lv.kiselova.colorcoding.dao.CellDAO;
import lv.kiselova.colorcoding.db.DatabaseHelper;
import lv.kiselova.colorcoding.model.Cell;
import lv.kiselova.colorcoding.view.CardGridViewAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ColorcodingActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorcoding);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
                
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        CardDAO cardDAO = new CardDAO(helper);
        //cardDAO.createCard("test", 5, 5);
        cardDAO.listCards();
       // cardDAO.deleteAll();
        //cardDAO.deleteCard(1);
        
        CellDAO cellDAO = new CellDAO(helper);
//        cellDAO.createCell("#2FD69F", "1", 1, 1, 1);
//        cellDAO.createCell("#9CD62F", "2", 1, 1, 1);
//        cellDAO.createCell("#C6E68C", "3", 1, 1, 1);
//        cellDAO.createCell("#F0C5AA", "4", 1, 1, 1);
//        cellDAO.createCell("#DFF5B8", "5", 1, 1, 1);
        //cellDAO.listCells();
       // cellDAO.deleteCell(1);
        //cellDAO.deleteAll();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_colorcoding, menu);
        return true;
    }

    


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_section1).toUpperCase();
                //case 1: return getString(R.string.title_section2).toUpperCase();
                //case 2: return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        public DummySectionFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	GridView gridView = new GridView(getActivity().getApplicationContext());
        	
        	DatabaseHelper helper = new DatabaseHelper(getActivity().getApplicationContext());
            CellDAO cellDAO = new CellDAO(helper);            
            ArrayList<Cell> cells =  cellDAO.listCells();
            
        	CardGridViewAdapter adapter = new CardGridViewAdapter(getActivity().getApplicationContext(), cells);
            gridView.setAdapter(adapter);
            gridView.setNumColumns(3);
            gridView.setVerticalSpacing(2);
            gridView.setHorizontalSpacing(2);
            
            return gridView;
        }
    }
}
