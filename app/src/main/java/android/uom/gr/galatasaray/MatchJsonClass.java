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
                String match_status;
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
                String[] split = match_date.split("-");
                match_date=split[2]+"/"+split[1];



                match_time = Noumero.getString("match_time");

//
//                String[] splited = match_time.split(":");
//                Integer hour = Integer.parseInt(splited[0]);
//                TimeZone tz = TimeZone.getDefault();
//                String ss =tz.getDisplayName(false, TimeZone.SHORT);
//                String[] splited2 = ss.split("T");
//                String[] splited3 = splited2[1].split(":");
//                Integer hourplus = Integer.parseInt(splited3[0]);
//                hour+=hourplus-1;
//                splited[0]=hour.toString();



                match_status = Noumero.getString("match_status");


                if(match_status.equals(""))
                results.add(match_hometeam_name+"#"+match_date+"\n"+match_time+"#"+match_awayteam_name+"#"+" ");
                else if(!"FT".equals(match_status))
                        results.add(match_hometeam_name+"#"+"Today"+"\n"+match_time+"#"+match_awayteam_name+"#"+" ");
                else
                    if("FT".equals(match_status))
                    results.add(match_hometeam_name + "#" + match_hometeam_score + ":" + match_awayteam_score + "#" + match_awayteam_name+"#"+"FT");
                    else
                        results.add(match_hometeam_name + "#" + match_hometeam_score + ":" + match_awayteam_score + "#" + match_awayteam_name+"#"+" ");

            }




        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }


        return results;

    }
}