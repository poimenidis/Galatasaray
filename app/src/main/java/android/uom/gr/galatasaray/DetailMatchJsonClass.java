package android.uom.gr.galatasaray;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

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
        ArrayList<String> Texts;
        String match_hometeam_halftime_score;
        String match_awayteam_halftime_score;

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
                match_date=getDate(match_date);


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

            Texts = new ArrayList<>();


                match_status = Noumero.getString("match_status");

                results.setAwayscore(match_awayteam_score);
                results.setAwayteam(match_awayteam_name);
                results.setDate(match_date);
                results.setHomescore(match_hometeam_score);
                results.setHometeam(match_hometeam_name);
                results.setStatus(match_status);
                results.setTime(match_time);



            match_hometeam_halftime_score = Noumero.getString("match_hometeam_halftime_score");
            if(match_hometeam_halftime_score.equals(""))
                match_hometeam_halftime_score="0";
            match_awayteam_halftime_score = Noumero.getString("match_awayteam_halftime_score");
            if(match_awayteam_halftime_score.equals(""))
                match_awayteam_halftime_score="0";


            if(match_status.equals("HT")) {
                Texts.add("45' - FIRST HALF ENDS,\n" + match_hometeam_name + " " + match_hometeam_halftime_score + " " + match_awayteam_name + " " + match_awayteam_halftime_score);
                Texts.add("KICK OFF! The game has just started!");
            }

            if(match_status.contains("+")) {
                String[] splited = match_status.split("\\+");
                int hour = Integer.parseInt(splited[0]);

                if(hour==45){
                    Texts.add("KICK OFF! The game has just started!");
                }
                else{
                    Texts.add("KICK OFF! The game has just started!");
                    Texts.add("45' - FIRST HALF ENDS,\n"+match_hometeam_name+" "+match_hometeam_halftime_score+" "+match_awayteam_name+" "+match_awayteam_halftime_score);
                    Texts.add("46' - SECOND HALF IS UNDERWAY");
                }

            }
            else if(!match_status.equals("")&&!match_status.equals("FT")&&!match_status.equals("HT")) {
                String[] splited = match_status.split("'");
                int hour = Integer.parseInt(splited[0]);
                if (hour > 45) {
                    Texts.add("45' - FIRST HALF ENDS,\n"+match_hometeam_name+" "+match_hometeam_halftime_score+" "+match_awayteam_name+" "+match_awayteam_halftime_score);
                    Texts.add("46' - SECOND HALF IS UNDERWAY");
                }

                if(hour>=1)
                    Texts.add("KICK OFF! The game has just started!");

            }

            if (match_status.equals("FT")) {
                Texts.add("45' - FIRST HALF ENDS,\n"+match_hometeam_name+" "+match_hometeam_halftime_score+" "+match_awayteam_name+" "+match_awayteam_halftime_score);
                Texts.add("46' - SECOND HALF IS UNDERWAY");
                Texts.add("KICK OFF! The game has just started!");
            }





            JSONArray goalscorer = Noumero.getJSONArray("goalscorer");

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

            JSONArray cards = Noumero.getJSONArray("cards");

            for (int k = 0; k < cards.length(); k++) {

                String text = null;

                String time;
                String home_fault;
                String card;
                String away_fault;

                JSONObject Noumerocards = cards.getJSONObject(k);

                time = Noumerocards.getString("time");
                home_fault = Noumerocards.getString("home_fault");
                card = Noumerocards.getString("card");
                away_fault = Noumerocards.getString("away_fault");


                if(!time.equals("")) {
                    text = time + " - " + card + " for ";

                    if (!home_fault.equals(""))
                        text += home_fault + " " + match_hometeam_name + " player";
                    else if (!away_fault.equals(""))
                        text += away_fault + " " + match_awayteam_name + " player";

                    Texts.add(text);
                }

            }


            Collections.sort(Texts, new Comparator<String>() {
                public int compare(String o1, String o2) {
                     o1 = o1.substring (0,2);
                     o2 = o2.substring (0,2);
                    return extractInt(o1) - extractInt(o2);
                }

                int extractInt(String s) {
                    String num = s.replaceAll("\\D", "");
                    // return 0 if no digits found
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                }
            });


            if(match_status.equals("FT"))
                Texts.add("FULL TIME: \n"+match_hometeam_name+" "+match_hometeam_score+" "+match_awayteam_name+" "+match_awayteam_score);


                results.setTexts(Texts);


        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }


        return results;

    }

    private static String getDate(String match_date) {
        Calendar c=Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = (df.format(c.getTime()));
        c.add(Calendar.DATE, +1);
        String Tomorrow = (df.format(c.getTime()));


        if (today.equals(match_date)){
            match_date="Today";
        }
        else if (Tomorrow.equals(match_date)){
            match_date="Tomorrow";
        }
        else {
            String[] split = match_date.split("-");
            match_date = split[2] + "/" + split[1];
        }

        return match_date;
    }
}
