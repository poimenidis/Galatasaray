package android.uom.gr.galatasaray;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DetailMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);


        Bundle extras = getIntent().getExtras();
        String code = extras.getString("code");
        String team1 = extras.getString("team1");
        String team2 = extras.getString("team2");
        String todate = extras.getString("todate");
        String fromdate = extras.getString("fromdate");


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailMatchActivityFragment(code,team1,team2, fromdate, todate))
                    .commit();
        }


    }


    public static class DetailMatchActivityFragment extends Fragment {




        private String code;
        private String todate;
        private String fromdate;
        private  String team1;
        private String team2;
        ArrayAdapter<String> ListAdapter;

        String[] data = {
                "NO INTERNET CONNECTION"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

        public DetailMatchActivityFragment(String code, String team1, String team2,String fromdate, String todate) {

            this.code = code;
            this.team1=team1;
            this.team2=team2;
            this.todate = todate;
            this.fromdate = fromdate;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_detail_matches, container, false);




            TextView Team1 = (TextView) rootView.findViewById(R.id.textView6);
            Team1.setText(team1);




            TextView Team2 = (TextView) rootView.findViewById(R.id.textView8);
            Team2.setText(team2);


            ListAdapter = new ArrayAdapter<String>(
                    this.getContext(),
                    R.layout.textviewcustomeddetails,
                    R.id.list_item_texts_textview, weekForecast);

            ListView TextsListView = (ListView)rootView.findViewById(R.id.listview_texts);
            TextsListView.setAdapter(ListAdapter);







            Timer timer = new Timer();
            TimerTask timerTask;
            timerTask = new TimerTask() {
                @Override
                public void run() {


                    updateMatcheDetails(rootView);
                }
            };
            timer.schedule(timerTask, 0, 100000);




            return rootView;
        }

        private void updateMatcheDetails(View view) {
            FetchTask task = new FetchTask(view);
            task.execute();
        }




        @SuppressLint("StaticFieldLeak")
        public class FetchTask extends AsyncTask<String, Void, MatchJsonClass.MatchClass> {

            private View vRef;

            FetchTask(View v) {
                vRef =v;
            }

            @Override
            protected MatchJsonClass.MatchClass doInBackground(String... params) {
                return fetchData();
            }



            @Override
            protected void onPostExecute(MatchJsonClass.MatchClass table) {

                String status;
                String sc;

                if (table != null) {

                    if (vRef != null) {

                        TextView Time = (TextView) vRef.findViewById(R.id.textView9);
                        if (table.getStatus().equals("FT"))
                            status = "END";
                        else if (table.getStatus().equals("HT"))
                            status = "Half Time";
                        else
                            status = table.getStatus();


                        Time.setText(status);


                        TextView score = (TextView) vRef.findViewById(R.id.textView7);
                        if (table.getStatus().equals(""))
                            sc=" " + table.getDate() + "\n " + table.getTime();
                        else
                            sc=" " + table.getHomescore() + ":" + table.getAwayscore();

                        score.setText(sc);

                    }


                    ListAdapter.clear();

                    if(table.getTexts()!=null){

                        Collections.reverse(table.getTexts());

                        for(String t : table.getTexts()){

                            ListAdapter.add(t);

                        }
                    }

                        ListAdapter.add("The game starts "+table.getDate()+" at "+table.getTime());
//

                }

            }


            private MatchJsonClass.MatchClass fetchData() {

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
                            ("https://apifootball.com/api/?action=get_events&from=" + fromdate + "&to=" + todate + "&league_id=376&match_id=" + code +
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


                    MatchJsonClass.MatchClass DetailList =
                            DetailMatchJsonClass.getDetailMatchFromJsonClass(matchJsonStr);



                    return DetailList;

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
}
