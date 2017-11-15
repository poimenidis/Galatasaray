package android.uom.gr.galatasaray;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);



        Bundle extras = getIntent().getExtras();
        String code = extras.getString("code");
        String todate = extras.getString("todate");
        String fromdate = extras.getString("fromdate");


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailMatchActivityFragment(code,fromdate,todate))
                    .commit();
        }


    }




    public static class DetailMatchActivityFragment extends Fragment {

        private String home_team_name;
        private String away_team_name;
        private String score;
        private String match_status;
        private String matchTime;
        private ArrayList<String> Texts;
        private String code;
        private String todate;
        private String fromdate;
        private DetailMatchJsonClass dj;
        private SwipeRefreshLayout swip;

        public DetailMatchActivityFragment(String intValue,String fromdate,String todate) {

            code=intValue;
            this.todate=todate;
            this.fromdate=fromdate;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail_matches, container, false);

            TextView textcode = (TextView) rootView.findViewById(R.id.codeMatch);

            swip = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
            swip.setColorSchemeResources(R.color.refresh1,R.color.refresh2);

            swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateMatcheDetails();
                }
            });


            textcode.setText(code+fromdate+todate);


            return rootView;
        }

        private void updateMatcheDetails() {
            FetchMatchTask task = new FetchMatchTask();
            task.execute();
        }



        public class FetchMatchTask extends AsyncTask<String, Void, MatchJsonClass.MatchClass > {

            @Override
            protected MatchJsonClass.MatchClass doInBackground(String... params) {
                return fetchMatchData();
            }

            @Override
            protected void onPostExecute(MatchJsonClass.MatchClass table) {
                List<String> data1 = new ArrayList<>();
                List<String> data2 = new ArrayList<>();
                List<String> data3 = new ArrayList<>();
                List<String> data4 = new ArrayList<>();
                List<String> data5 = new ArrayList<>();

//                swip.setRefreshing(true);
                if(table != null){

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
                        data5.add(table.getMatch_id());

                    }
//                    customListviewMatches.setDatas(data1,data2,data3,data4,data5);
                }
//                swip.setRefreshing(false);
            }

            private MatchJsonClass.MatchClass fetchMatchData() {

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
                            ("https://apifootball.com/api/?action=get_events&from="+fromdate+"&to="+todate+"&league_id=376&match_id="+code+
                                    "&APIkey=dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6");

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

                    Log.i("TABLEe: ","https://apifootball.com/api/?action=get_events&from="+fromdate+"&to="+todate+"&league_id=376&match_id="+code+
                            "&APIkey=dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6");
                    Log.i("TABLE: ",matchJsonStr);

                    MatchJsonClass.MatchClass MatchList =
                            DetailMatchJsonClass.getDetailMatchFromJsonClass(matchJsonStr);


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
