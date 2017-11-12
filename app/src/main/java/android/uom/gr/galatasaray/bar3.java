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

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar3 extends android.support.v4.app.Fragment {

    private TextView TextVieBar3;
    ArrayAdapter<String> listAdapter;
    ListView playerlistView;
    CustomListviewPlayers customListviewPlayers;

    private String[] playersName = {"Cédric Carrasso", "İsmail Çipe", "Eray İşcan", "Fernando Muslera", "Ahmet Şen", "Serdar Aziz", "Hakan Balta", "Ahmet Çalık", "Tarik Çamdal", "Jason Denayer", "Koray Günter"  , "Iasmin Latovlevici", "Martin Linnes"      , "Maicon"       , "Mariano"       ,
            "Atalay Babacan"    , "Emrah Başsan"    , "Younès Belhanda"       , "Tolga Ciğerci"        , "Nigel de Jong"     , "Ryan Donk"             , "Sofiane Féghouli"     , "Fernando"           , "Recep Gül"         ,
            "Umut Gündoğan"  , "Gökay Güney"     , "Selçuk İnan"     , "Papa N'Diaye"	   , "Yasin Öztekin"     , "Garry Rodrigues"   , "Eren Derdiyok", "Bafétimbi Gomis"   , "Sinan Gümüş"        ,
            "Igor Tudor"};

    private Integer[] playerImage = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15,
            R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27,
            R.drawable.a28, R.drawable.a29, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34};

    private String[] playerPosition={"Goalkeeper", "Goalkeeper", "Goalkeeper", "Goalkeeper", "Goalkeeper", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender",
            "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder",
            "Forward", "Forward", "Forward", "Manager"};

    private Integer[] playerNumber={
            16, 13, 67, 1, 12, 4, 22, 5, 21, 64, 28, 33, 14, 3, 2, 36, 17, 10, 6, 34, 15, 89, 25, 37, 90, 88, 8, 20, 7, 24, 9, 18, 11, 0};





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar3,container,false);



//        listAdapter =
//                new ArrayAdapter<String>
//                        (this.getContext(),R.layout.textviewcustomedplayers, R.id.list_item_players_textview,playersList);
        //(σε ποιο activity ειμαστε, το layout που χρησιμοποιουμε,το id του textview που θελουμε να γινουν τα αντικειμενα, την λιστα)   !!!
        //σκοπος ειναι να κανει τα τα αντικειμενα της λιστας, TextView

        playerlistView = (ListView) view.findViewById(R.id.listview_players);
        customListviewPlayers = new CustomListviewPlayers(this.getActivity(),playersName,playerPosition,playerImage,playerNumber);
        playerlistView.setAdapter(customListviewPlayers);





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
