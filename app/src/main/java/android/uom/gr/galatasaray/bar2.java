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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar2 extends android.support.v4.app.Fragment {

    ArrayAdapter<String> tableListAdapter;
    ListView listTable;
    SwipeRefreshLayout swip;
    String key="dffbf01eecc3cef8a8dab1e3d05b720f9d2335be742cf048b86161544d4f91b6";
    CustomListviewTable customListviewTable;


    private String[] d1 = {
            null};


    public bar2() {
    }

    @Override
    public void onStart() {
        super.onStart();
        updateTable();
    }

    private void updateTable() {
        FetchTableTask task = new FetchTableTask();
        task.execute();
    }

    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar2,container,false);

         swip = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swip.setColorSchemeResources(R.color.refresh1,R.color.refresh2);

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                updateTable();

//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        swip.setRefreshing(false);
//                    }
//                }, 3000);
            }
        });

        List<String> Data1 = new ArrayList<>(Arrays.asList(d1));
        List<String> Data2 = new ArrayList<>(Arrays.asList(d1));
        List<String> Data3 = new ArrayList<>(Arrays.asList(d1));
        List<String> Data4 = new ArrayList<>(Arrays.asList(d1));
        List<String> Data5 = new ArrayList<>(Arrays.asList(d1));


        customListviewTable = new CustomListviewTable
                (this.getActivity(),Data1,Data2,Data3,Data4,  Data5);

        listTable = (ListView) view.findViewById(R.id.listTable);
        listTable.setAdapter(customListviewTable);

//        FetchTableTask task = new FetchTableTask();
//        task.execute();


        return view;
    }


    public class FetchTableTask   extends AsyncTask<String, Void, List<TableJsonClass.Table>> {

        @Override
        protected List<TableJsonClass.Table> doInBackground(String... params) {
            return fetchTableData();
        }

        @Override
        protected void onPostExecute(List<TableJsonClass.Table> strings) {
            List<String> data1 = new ArrayList<>();
            List<String> data2 = new ArrayList<>();
            List<String> data3 = new ArrayList<>();
            List<String> data4 = new ArrayList<>();
            List<String> data5 = new ArrayList<>();

            swip.setRefreshing(true);
            if(strings != null){
                for(TableJsonClass.Table table : strings){
//                    tableListAdapter.add(table);
                    data1.add(Integer.toString(table.getPosition()));
                    data2.add(table.getName());
                    data3.add(table.getPlayed());
                    data4.add(Integer.toString(table.getTermata()));
                    data5.add(table.getLeaguePoints());



                }
                customListviewTable.setDatas(data1,data2,data3,data4,data5);
            }
            swip.setRefreshing(false);
        }



        private List<TableJsonClass.Table> fetchTableData() {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String tableJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                //MODIFIED FOR CITY OF THESSALONIKI, GREECE
                URL url = new URL("https://apifootball.com/api/?action=get_standings&league_id=376&APIkey="+key);

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
                tableJsonStr = buffer.toString();

                Log.i("TABLE: ",tableJsonStr);

                List<TableJsonClass.Table> tableList =
                        TableJsonClass.getTableFromJson(tableJsonStr, 18);

                return tableList;

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