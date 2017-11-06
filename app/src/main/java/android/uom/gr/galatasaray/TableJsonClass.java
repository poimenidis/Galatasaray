package android.uom.gr.galatasaray;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Κώστας Ποιμενίδης on 23/10/2017.
 */

public class TableJsonClass {


    public static List<Table> getTableFromJson(String jsonString, int teams) {
        List<Integer> rr = new ArrayList<>();
        ArrayList<Table> results = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);//ksekiname etsi na diavazoyme JSON

            for(int i =0; i< jsonArray.length(); i++){

                String name;
                int position;
                String league_position;
                int overall_league_GF;
                int overall_league_GA;
                int termata;
                String leaguePoints;
                Table t= new Table();
                String league_payed;


                JSONObject Noumero = jsonArray.getJSONObject(i);

                name = Noumero.getString("team_name");
                t.setName(name);

                league_position= Noumero.getString("overall_league_position");
                position = Integer.parseInt(league_position);
                t.setPosition(position);

                overall_league_GF =Integer.parseInt(Noumero.getString("overall_league_GF"));
                overall_league_GA =Integer.parseInt(Noumero.getString("overall_league_GA"));
                termata=overall_league_GF-overall_league_GA;
                t.setTermata(termata);

                leaguePoints= Noumero.getString("overall_league_PTS");
                t.setLeaguePoints(leaguePoints);

                league_payed= Noumero.getString("overall_league_payed");
                t.setPlayed(league_payed);

                results.add(t);

            }

        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }


        Collections.sort(results, new Comparator<Table>()
        {
            @Override
            public int compare(Table o1, Table o2)
            {
                return o1.getPosition() - o2.getPosition();
            }
        });


//        results.add(t.getPosition()+"#"+t.getName()+"#"+t.getPlayed()+"#"+t.getTermata()+"#"+t.getLeaguePoints());

    return results;
    }

    public static class Table{

        private String name;
        private int position;
        private int termata;
        private String leaguePoints;
        private String played;

        public void setPlayed(String played) {
            this.played = played;
        }

        public String getPlayed() {
            return played;
        }

        public Table() {
        }

        public int getPosition() {
            return position;
        }

        public int getTermata() {
            return termata;
        }

        public String getLeaguePoints() {
            return leaguePoints;
        }

        public String getName() {
            return name;
        }

        public void setLeaguePoints(String leaguePoints) {
            this.leaguePoints = leaguePoints;
        }

        public void setName(String name) {
            if(name.length() > 20)
            name = name.substring(0,16);

            this.name = name;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void setTermata(int termata) {
            this.termata = termata;
        }
    }


}

