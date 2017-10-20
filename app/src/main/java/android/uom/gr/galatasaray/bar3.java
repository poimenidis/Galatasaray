package android.uom.gr.galatasaray;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar3 extends android.support.v4.app.Fragment {

    private TextView TextVieBar3;
    ArrayAdapter<String> listAdapter;
    ListView playerlistView;

    String[] playersData = {

            "Cédric Carrasso\nGoalkeeper",
            "İsmail Çipe\nGoalkeeper",
            "Eray İşcan\nGoalkeeper",
            "Fernando Muslera\nGoalkeeper",
            "Ahmet Şen\nGoalkeeper",

            "Serdar Aziz\nDefender",
            "Hakan Balta\nDefender",
            "Ahmet Çalık\nDefender",
            "Tarik Çamdal\nDefender",
            "Jason Denayer\nDefender",
            "Koray Günter\nDefender",
            "Iasmin Latovlevici\nDefender",
            "Martin Linnes\nDefender",
            "Maicon Brazil\nDefender",
            "Mariano Brazil\nDefender",

            "Atalay Babacan\nMidfielder",
            "Emrah Başsan\nMidfielder",
            "Younès Belhanda\nMidfielder",
            "Tolga Ciğerci\nMidfielder",
            "Nigel de Jong\nMidfielder",
            "Ryan Donk\nMidfielder",
            "Sofiane Féghouli\nMidfielder",
            "Fernando\nMidfielder",
            "Recep Gül\nMidfielder",
            "Umut Gündoğan\nMidfielder",
            "Gökay Güney\nMidfielder",
            "Selçuk İnan\nMidfielder",
            "Papa N'Diaye\nMidfielder",
            "Yasin Öztekin\nMidfielder",
            "Garry Rodrigues \nMidfielder",

            "Eren Derdiyok\nForward",
            "Bafétimbi Gomis\nForward",
            "Sinan Gümüş\nForward",

            "Igor Tudor\nManager"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar3,container,false);

        List<String> playersList = Arrays.asList(playersData);            // sos απο πινακα σε λιστα                   !!!

        listAdapter =
                new ArrayAdapter<String>
                        (this.getContext(),R.layout.textviewcustomed, R.id.list_item_players_textview,playersList);
        //(σε ποιο activity ειμαστε, το layout που χρησιμοποιουμε,το id του textview που θελουμε να γινουν τα αντικειμενα, την λιστα)   !!!
        //σκοπος ειναι να κανει τα τα αντικειμενα της λιστας, TextView

        playerlistView = (ListView) view.findViewById(R.id.listview_players);
        playerlistView.setAdapter(listAdapter);
        //βαζει τα textview του adapter στην λιστα μας


        playerlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String player = listAdapter.getItem(position);
                Intent myIntent = new Intent(getActivity(), PlayersActivity.class);
                myIntent.putExtra("player", position); //Optional parameters
                startActivity(myIntent);

            }
        });




        return view;
    }



}
