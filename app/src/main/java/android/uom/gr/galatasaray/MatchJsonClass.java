package android.uom.gr.galatasaray;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Κώστας Ποιμενίδης on 29/10/2017.
 */

public class MatchJsonClass {


    public static List<String> getMatchFromJson(String jsonString, int teams) {
        List<String> results = new ArrayList<>();


        try {
            JSONArray jsonArray = new JSONArray(jsonString);//ksekiname etsi na diavazoyme JSON

            for (int i = 0; i < jsonArray.length(); i++) {

                String match_time;
                String match_hometeam_name;
                String match_hometeam_score;
                String match_awayteam_name;
                String match_awayteam_score;
                String match_live;
                String match_date;


                JSONObject Noumero = jsonArray.getJSONObject(i);

                match_hometeam_name = Noumero.getString("match_hometeam_name");
                match_awayteam_name = Noumero.getString("match_awayteam_name");
                if(match_hometeam_name.length() > 20)
                    match_hometeam_name = match_hometeam_name.substring(0,16) ;
                if(match_awayteam_name.length() > 20)
                    match_awayteam_name = match_awayteam_name.substring(0,16) ;

                match_hometeam_score = Noumero.getString("match_hometeam_score");
                match_awayteam_score = Noumero.getString("match_awayteam_score");


                match_date = Noumero.getString("match_date");
                match_time = Noumero.getString("match_time");

                match_live = Noumero.getString("match_live");


                if(match_live.equals("0"))
                results.add(match_hometeam_name+"#"+match_date+"\n"+match_time+"#"+match_awayteam_name);
                else
                    results.add(match_hometeam_name+"#"+match_hometeam_score+":"+match_awayteam_score+"#"+match_awayteam_name);

            }


        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }


        return results;

    }
}