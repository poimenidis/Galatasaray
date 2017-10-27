package android.uom.gr.galatasaray;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar1 extends android.support.v4.app.Fragment {


    private ListView listmatches;
    private String fromdate;
    private String todate;
    ArrayAdapter<String> tableListAdapter;
    ListView listTable;
    SwipeRefreshLayout swip;
    String key="dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6";


    private String[] d1 = {
            "Mon ",
            "Tue ",
            "Wed",
            "Thurs",
            "Fri",
            "Sat",
            "Sun"         };
    private String[] d0 = {
            "saka ",
            "Tue ",
            "Wed",
            "kaka",
            "Fri",
            "Sat",
            "Sun"         };
    private String[] d2 = {
            "Mon ",
            "Tue ",
            "Wed",
            "Thurs",
            "Fri",
            "Sat",
            "Sun"         };
    private String[] d3 = {
            "Mon ",
            "Tue ",
            "Wed",
            "Thurs",
            "Fri",
            "Sat",
            "Sun"         };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar1,container,false);

        swip = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swip.setColorSchemeResources(R.color.refresh,R.color.refresh1,R.color.refresh2);

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });



        Calendar c=Calendar.getInstance();

        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY||c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            c.add(Calendar.DATE, -7);
            fromdate = (df.format(c.getTime()));
            c.add(Calendar.DATE, 3);
            todate = (df.format(c.getTime()));

        }
        else {

            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
             fromdate = (df.format(c.getTime()));
            c.add(Calendar.DATE, 3);
             todate = (df.format(c.getTime()));
        }


        if(fromdate.equals("2017/11/10")){
            fromdate="2017/11/17";
            todate="2017/11/20";
        }
        else if(fromdate.equals("2017/12/29")||fromdate.equals("2018/01/05")||fromdate.equals("2018/01/12")){
            fromdate="2018/01/19";
            todate="2018/01/22";
        }
        else if(fromdate.equals("2018/03/23")){
            fromdate="2018/03/30";
            todate="2018/03/02";
        }

        List<String> data1 = new ArrayList<>(Arrays.asList(d1));
        List<String> data2 = new ArrayList<>(Arrays.asList(d2));
        List<String> data3 = new ArrayList<>(Arrays.asList(d3));
        List<String> data0 = new ArrayList<>(Arrays.asList(d0));

        listmatches = (ListView) view.findViewById(R.id.listMatches);
        CustomListviewMatches customListviewMatches=new CustomListviewMatches(this.getActivity(),data1,data2,data3);
        listmatches.setAdapter(customListviewMatches);


        customListviewMatches.setDatas(data0,data2,data3);


        return view;
    }



}