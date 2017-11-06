package android.uom.gr.galatasaray;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar1 extends android.support.v4.app.Fragment {

    CustomListviewMatches customListviewMatches;
    private ListView listmatches;
    private String fromdate;
    private String todate;
    ArrayAdapter<String> tableListAdapter;
    ListView listTable;
    SwipeRefreshLayout swip;
    String key="dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6";


    private String[] d1 = {
            null    };






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar1,container,false);

        swip = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swip.setColorSchemeResources(R.color.refresh,R.color.refresh1,R.color.refresh2);

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    updateMatches();
            }
        });



        Calendar c=Calendar.getInstance();

        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY||c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

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
        List<String> data2 = new ArrayList<>(Arrays.asList(d1));
        List<String> data3 = new ArrayList<>(Arrays.asList(d1));
        List<String> data0 = new ArrayList<>(Arrays.asList(d1));

        listmatches = (ListView) view.findViewById(R.id.listMatches);
        customListviewMatches=new CustomListviewMatches(this.getActivity(),data1,data2,data3,data0);
        listmatches.setAdapter(customListviewMatches);




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMatches();
    }

    private void updateMatches() {
        FetchMatchTask task = new FetchMatchTask();
        task.execute();
    }


    public class FetchMatchTask   extends AsyncTask<String, Void, List<MatchJsonClass.MatchClass> >{

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

            swip.setRefreshing(true);
            if(strings != null){
                for(MatchJsonClass.MatchClass table : strings){
//                    tableListAdapter.add(table);
                    data1.add(table.getHometeam());

                    if(table.getStatus().equals(""))
                    data2.add(table.getDate()+"\n"+table.getTime());
                    else if(!"FT".equals(table.getStatus()))
                    data2.add("Today"+"\n"+table.getTime());
                    else
                        data2.add(table.getHomescore()+":"+table.getAwayscore());


                    data3.add(table.getAwayteam());
                    data4.add(table.getStatus());

                }
                customListviewMatches.setDatas(data1,data2,data3,data4);
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
                        MatchJsonClass.getMatchFromJson(matchJsonStr, 9);


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