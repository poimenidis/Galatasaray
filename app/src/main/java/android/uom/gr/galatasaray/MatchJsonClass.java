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


    public static List<MatchClass> getMatchFromJson(String jsonString) {
        List<MatchClass> results = new ArrayList<>();


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
                String match_id;
                MatchClass mm = new MatchClass();


                JSONObject Noumero = jsonArray.getJSONObject(i);

                match_hometeam_name = Noumero.getString("match_hometeam_name");
                match_awayteam_name = Noumero.getString("match_awayteam_name");
                if(match_hometeam_name.length() > 20)
                    match_hometeam_name = match_hometeam_name.substring(0,16) ;
                if(match_awayteam_name.length() > 20)
                    match_awayteam_name = match_awayteam_name.substring(0,16) ;

                match_hometeam_score = Noumero.getString("match_hometeam_score");
                match_awayteam_score = Noumero.getString("match_awayteam_score");

                match_id = Noumero.getString("match_id");


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

                mm.setAwayscore(match_awayteam_score);
                mm.setAwayteam(match_awayteam_name);
                mm.setDate(match_date);
                mm.setHomescore(match_hometeam_score);
                mm.setHometeam(match_hometeam_name);
                mm.setStatus(match_status);
                mm.setTime(match_time);
                mm.setMatch_id(match_id);



                JSONArray goalscorer = Noumero.getJSONArray("goalscorer");



                results.add(mm);



            }




        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }


        return results;

    }

    public static class MatchClass{

        private String hometeam;
        private String awayteam;
        private String date;
        private String time;
        private String homescore;
        private String awayscore;
        private String status;
        private String match_id;
        private ArrayList<String> Texts;

        public MatchClass(){

        }

        public void setTexts(ArrayList<String> texts) {
            Texts = texts;
        }

        public ArrayList<String> getTexts() {

            return Texts;
        }

        public String getMatch_id() {
            return match_id;
        }

        public void setMatch_id(String match_id) {
            this.match_id = match_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAwayscore() {
            return awayscore;
        }

        public String getAwayteam() {
            return awayteam;
        }

        public String getDate() {
            return date;
        }

        public String getHomescore() {
            return homescore;
        }

        public String getHometeam() {
            return hometeam;
        }

        public String getTime() {
            return time;
        }

        public void setAwayscore(String awayscore) {
            this.awayscore = awayscore;
        }

        public void setAwayteam(String awayteam) {
            this.awayteam = awayteam;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setHomescore(String homescore) {
            this.homescore = homescore;
        }

        public void setHometeam(String hometeam) {
            this.hometeam = hometeam;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }


}