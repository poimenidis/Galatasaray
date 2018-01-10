package android.uom.gr.galatasaray;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar1 extends android.support.v4.app.Fragment {

    CustomListviewMatches customListviewMatches;
    private ListView listmatches;
    private String fromdate;
    private String todate;
    private ArrayAdapter<String> tableListAdapter;
    private ListView listTable;
    private SwipeRefreshLayout swip;
    private String key="dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6";



    private String[][] dates =new String[][]{
            {"2017/8/11" , "2017/8/14"},
            {"2017/8/18","2017/8/21"},
            {"2017/8/24","2017/8/27"},
            {"2017/9/8","2017/9/11"},
            {"2017/9/15","2017/9/18"},
            {"2017/9/22","2017/9/25"},
            {"2017/9/29","2017/10/2"},
            {"2017/10/13","2017/10/15"},
            {"2017/10/20","2017/10/23"},
            {"2017/10/27","2017/10/30"},
            {"2017/11/3","2017/11/6"},
            {"2017/11/17","2017/11/20"},
            {"2017/11/24","2017/11/27"},
            {"2017/12/1","2017/12/4"},
            {"2017/12/8","2017/12/11"},
            {"2017/12/15","2017/12/18"},
            {"2017/12/22","2017/12/25"},
            {"2018/1/19","2018/1/22"},
            {"2018/1/26","2018/1/29"},
            {"2018/2/2","2018/2/5"},
            {"2018/2/9","2018/2/12"},
            {"2018/2/16","2018/2/19"},
            {"2018/2/23","2018/2/26"},
            {"2018/3/2","2018/3/5"},
            {"2018/3/9","2018/3/12"},
            {"2018/3/16","2018/3/19"},
            {"2018/3/30","2018/4/2"},
            {"2018/4/6","2018/4/9"},
            {"2018/4/13","2018/4/16"},
            {"2018/4/20","2018/4/23"},
            {"2018/4/27","2018/4/30"},
            {"2018/5/4","2018/5/7"},
            {"2018/5/11","2018/5/14"},
            {"2018/5/18","2018/5/21"}};

    private String[] machdays={"Machday 1", "Machday 2","Machday 3", "Machday 4", "Machday 5", "Machday 6", "Machday 7", "Machday 8", "Machday 9", "Machday 10", "Machday 11", "Machday 12", "Machday 13", "Machday 14", "Machday 15", "Machday 16", "Machday 17", "Machday 18",
                "Machday 19", "Machday 20", "Machday 21", "Machday 22", "Machday 23", "Machday 24", "Machday 25", "Machday 26", "Machday 27", "Machday 28", "Machday 29",
            "Machday 30", "Machday 31", "Machday 32", "Machday 33", "Machday 34"};


    private String[] d1 = {
            null    };

    private int pos;
    private Spinner spinMatchBar;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar1,container,false);

        swip = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swip.setColorSchemeResources(R.color.refresh1,R.color.refresh2);

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    updateMatches();
            }
        });



        Calendar c=Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);

        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            DateFormat df = new SimpleDateFormat("yyyy/M/d");
            c.add(Calendar.DATE, -7);
            fromdate = (df.format(c.getTime()));
            c.add(Calendar.DATE, 3);
            todate = (df.format(c.getTime()));

        }
        else {

            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            DateFormat df = new SimpleDateFormat("yyyy/M/d");
             fromdate = (df.format(c.getTime()));
            c.add(Calendar.DATE, 3);
             todate = (df.format(c.getTime()));
        }




        if(fromdate.equals("2017/11/10")){
            fromdate="2017/11/17";
            todate="2017/11/20";
        }
        else if(fromdate.equals("2017/12/29")||fromdate.equals("2018/1/5")||fromdate.equals("2018/1/12")){
            fromdate="2018/1/19";
            todate="2018/1/22";
        }
        else if(fromdate.equals("2018/3/23")){
            fromdate="2018/3/30";
            todate="2018/3/02";
        }

        List<String> data1 = new ArrayList<>(Arrays.asList(d1));

        listmatches = (ListView) view.findViewById(R.id.listMatches);
        customListviewMatches=new CustomListviewMatches(this.getActivity());
        customListviewMatches.setDatas(data1,data1,data1,data1,null);
        listmatches.setAdapter(customListviewMatches);


        listmatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                customListviewMatches.setPos(position);
                Intent myIntent = new Intent(getActivity(), DetailMatchActivity.class);


                myIntent.putExtra("code",customListviewMatches.getData5().get(position));
                myIntent.putExtra("team1",customListviewMatches.getData1().get(position));
                myIntent.putExtra("team2",customListviewMatches.getData3().get(position));
                myIntent.putExtra("todate",todate);
                myIntent.putExtra("fromdate",fromdate);

                startActivity(myIntent);
                getActivity().overridePendingTransition(R.anim.down_to_up,R.anim.stay);


            }
        });



        for (int i=0; i<dates.length;i++){
            if(fromdate.equals(dates[i][0]))
                pos=i;
        }



        spinMatchBar = (Spinner) view.findViewById(R.id.spindates);
        ArrayAdapter<String> matchdatesAdap = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item, machdays);
        spinMatchBar.setAdapter(matchdatesAdap);

        spinMatchBar.setSelection(pos);


        spinMatchBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                swip.setRefreshing(true);

                if(customListviewMatches!=null)
                customListviewMatches.clear();

                fromdate=dates[position][0];
                todate = dates[position][1];
                updateMatches();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Timer timer = new Timer();
        TimerTask timerTask;
        timerTask = new TimerTask() {
            @Override
            public void run() {


                updateMatches();
            }
        };
        timer.schedule(timerTask, 0, 200000);






        return view;
    }




    private void updateMatches() {
        FetchMatchTask task = new FetchMatchTask();
        task.execute();
    }


    public class FetchMatchTask extends AsyncTask<String, Void, List<MatchJsonClass.MatchClass> >{

        @Override
        protected List<MatchJsonClass.MatchClass> doInBackground(String... params) {
            return fetchMatchData();
        }

        @Override
        protected void onPostExecute(List<MatchJsonClass.MatchClass> strings) {
            List<String> data1 = new ArrayList<>();
            List<String> data2 = new ArrayList<>();
            List<String> data3 = new ArrayList<>();
            List<String> data4 = new ArrayList<>();
            List<String> data5 = new ArrayList<>();

            swip.setRefreshing(true);
            if(strings != null){
                for(MatchJsonClass.MatchClass table : strings){
//                    tableListAdapter.add(table);
                    data1.add(table.getHometeam());

                    if(table.getStatus().equals(""))
                    data2.add(table.getDate()+"\n"+table.getTime());
                    else
                        data2.add(table.getHomescore()+":"+table.getAwayscore());


                    data3.add(table.getAwayteam());
                    data4.add(table.getStatus());
                    data5.add(table.getMatch_id());

                }
                customListviewMatches.setDatas(data1,data2,data3,data4,data5);
            }
            swip.setRefreshing(false);
        }

        private List<MatchJsonClass.MatchClass> fetchMatchData() {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String matchJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                //MODIFIED FOR CITY OF THESSALONIKI, GREECE
                URL url = new URL
                        ("https://apifootball.com/api/?action=get_events&from="+fromdate+"&to="+todate+"&league_id=376&APIkey=dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                }
                matchJsonStr = buffer.toString();

                Log.i("TABLEe: ","https://apifootball.com/api/?action=get_events&from=" +
                        fromdate+"&to="+todate+"&league_id=376&APIkey=dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6");
                Log.i("TABLE: ",matchJsonStr);

                List<MatchJsonClass.MatchClass> MatchList =
                        MatchJsonClass.getMatchFromJson(matchJsonStr);


                return MatchList;

            } catch (IOException e) {
                Log.e("ForecastFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            return null;
        }
    }

}