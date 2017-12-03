package android.uom.gr.galatasaray;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Κώστας Ποιμενίδης on 11/10/2017.
 */

public class bar3 extends android.support.v4.app.Fragment {


    ListView playerlistView;
    CustomListviewPlayers customListviewPlayers;
    DatabaseHelper mDatabaseHelper ;



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
        customListviewPlayers = new CustomListviewPlayers(this.getActivity());
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

    private void sqlitecode() {


        mDatabaseHelper = new DatabaseHelper(getContext());
        mDatabaseHelper.addElements();
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
//
//
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
//
//
////
//
//
//
//        if(listData.isEmpty()){
//
//            for(int i = 0; i <playerNumber.length; i++) {
//                boolean insertData = mDatabaseHelper.addData(playersName[i], playerNationality[i], playerImage[i], playerAge[i], playerNumber[i], playerPosition[i]);
//
//                Log.i("skataaa", String.valueOf(insertData));
//            }
//        }
//        else
//            Log.i("skataa","false");
//
//
        if(listData.isEmpty())
            Log.i("skataaa", "yes");
        else
            Log.i("skataaa", "no");

        for(String k : listData) {
            Log.i("skataaa", k);
        }

//
//        mDatabaseHelper.deleteName(7,"makis");
//
//        Cursor dataa = mDatabaseHelper.getItemID("makis"); //get the id associated with that name
////        int itemID = -1;
////        while(data.moveToNext()){
////            itemID = data.getInt(0);
////        }
////
////        Log.i("skataaa",Integer.toString(itemID));
//





//        getContext().deleteDatabase("people_table");



    }

}
