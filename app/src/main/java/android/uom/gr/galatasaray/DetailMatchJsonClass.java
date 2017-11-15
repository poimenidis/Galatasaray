package android.uom.gr.galatasaray;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Κώστας Ποιμενίδης on 15/11/2017.
 */

public class DetailMatchJsonClass {

    public static MatchJsonClass.MatchClass getDetailMatchFromJsonClass(String jsonString) {

        MatchJsonClass.MatchClass results = new MatchJsonClass.MatchClass();

        String match_time;
        String match_hometeam_name;
        String match_hometeam_score;
        String match_awayteam_name;
        String match_awayteam_score;
        String match_status;
        String match_date;

        try {
            JSONArray jsonArray = new JSONArray(jsonString);//ksekiname etsi na diavazoyme JSON








                JSONObject Noumero = jsonArray.getJSONObject(0);

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

                results.setAwayscore(match_awayteam_score);
                results.setAwayteam(match_awayteam_name);
                results.setDate(match_date);
                results.setHomescore(match_hometeam_score);
                results.setHometeam(match_hometeam_name);
                results.setStatus(match_status);
                results.setTime(match_time);


                JSONArray goalscorer = Noumero.getJSONArray("goalscorer");


                ArrayList<String> Texts = new ArrayList<>();

                for (int j = 0; j < goalscorer.length(); j++) {

                    String text;

                    String time;
                    String home_scorer;
                    String score;
                    String away_scorer;

                    JSONObject Noumerogoal = goalscorer.getJSONObject(j);

                    time = Noumerogoal.getString("time");
                    home_scorer = Noumerogoal.getString("home_scorer");
                    score = Noumerogoal.getString("score");
                    away_scorer = Noumerogoal.getString("away_scorer");

                    text = time+" - gooooooooaallll for ";

                    if(!home_scorer.equals(""))
                        text+=match_hometeam_name+".\nIt was scored by "+home_scorer+".\nNow the scrore is "+score;
                    else if(!away_scorer.equals(""))
                        text+=match_awayteam_name+".\nIt was scored by "+away_scorer+".\nNow the scrore is "+score;

                    Texts.add(text);

                }

                results.setTexts(Texts);


                Log.i("skataaa", results.getTexts().toString());



        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }


        return results;

    }
}
