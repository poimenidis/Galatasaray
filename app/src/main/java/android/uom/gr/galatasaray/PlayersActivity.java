package android.uom.gr.galatasaray;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        Intent mIntent = getIntent();
        Integer value = mIntent.getIntExtra("player", 0);


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlayersActivityFragment(value))
                    .commit();
        }





    }





    public static class PlayersActivityFragment extends Fragment {

        private Integer playerChosen;
        private TextView nameOfPlayer;
        private ImageView img;
        private TextView noPlayer;
        private TextView natPlayer;
        private TextView bornPlayer;
        private TextView posPlayer;
        private String playersName;
        private String playerPosition;
        private Integer playerImage;
        private Integer playerNumber;
        private String playerNationality;
        private String playerAge;

        public PlayersActivityFragment(Integer intValue) {

            playerChosen=intValue;



        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_players, container, false);



            DatabaseHelper mDatabaseHelper = new DatabaseHelper(this.getContext());
            mDatabaseHelper.addElements();
            Cursor data = mDatabaseHelper.getData();

//
//
            data.moveToPosition(playerChosen);
                //get the value from the database in column 1
                //then add it to the ArrayList
                playersName=data.getString(1);
                playerPosition=data.getString(6);
                playerImage=data.getInt(3);
                playerNumber=data.getInt(5);
                playerNationality = data.getString(2);
                playerAge = data.getString(4);




            img=(ImageView) rootView.findViewById(R.id.imageViewPaixti);
            img.setImageResource(playerImage);

            nameOfPlayer = (TextView) rootView.findViewById(R.id.onomapaixtiID);
            nameOfPlayer.setText(playersName);

            noPlayer = (TextView) rootView.findViewById(R.id.textViewNo);
            if(playerNumber!=0) {

                noPlayer.setText(playerNumber.toString());
            }
            else{
                noPlayer.setVisibility(TextView.INVISIBLE);
                TextView shirtNoText = (TextView) rootView.findViewById(R.id.textView4) ;
                shirtNoText.setVisibility(TextView.INVISIBLE);
            }

            natPlayer = (TextView) rootView.findViewById(R.id.textViewNationality);
            natPlayer.setText(playerNationality);

            bornPlayer = (TextView) rootView.findViewById(R.id.textViewBorn);
            bornPlayer.setText(playerAge);

            posPlayer = (TextView) rootView.findViewById(R.id.textViewPosition);
            posPlayer.setText(playerPosition);


            return rootView;
        }
    }


}
