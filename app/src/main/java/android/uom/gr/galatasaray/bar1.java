package android.uom.gr.galatasaray;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar1 extends android.support.v4.app.Fragment {


    private TextView TextVieBar1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar1,container,false);

        final SwipeRefreshLayout swip = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swip.setColorSchemeResources(R.color.refresh,R.color.refresh1,R.color.refresh2);

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        swip.setRefreshing(false);
                    }
                }, 3000);
            }
        });


        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();

        today = DateUtil.addDays(today, 5);
        String todate = formatter.format(today);

        today = DateUtil.addDays(today,-7);
        String fromdate = formatter.format(today);


        TextVieBar1 = (TextView) view.findViewById(R.id.textview1);

        TextVieBar1.setText(fromdate);


        return view;
    }

    public static class DateUtil
    {
        public static Date addDays(Date date, int days)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }
    }

}