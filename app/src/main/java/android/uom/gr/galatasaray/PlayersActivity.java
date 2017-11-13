package android.uom.gr.galatasaray;

import android.content.Intent;
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

        public PlayersActivityFragment(Integer intValue) {

            playerChosen=intValue;

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_players, container, false);

            PlayersClass player= new PlayersClass(playerChosen);

            img=(ImageView) rootView.findViewById(R.id.imageViewPaixti);
            img.setImageResource(player.getPlayerImage());

            nameOfPlayer = (TextView) rootView.findViewById(R.id.onomapaixtiID);
            nameOfPlayer.setText(player.getplayersName());

            noPlayer = (TextView) rootView.findViewById(R.id.textViewNo);
            if(player.getPlayerNumber()!=0) {

                noPlayer.setText(player.getPlayerNumber().toString());
            }
            else{
                noPlayer.setVisibility(TextView.INVISIBLE);
                TextView shirtNoText = (TextView) rootView.findViewById(R.id.textView4) ;
                shirtNoText.setVisibility(TextView.INVISIBLE);
            }

            natPlayer = (TextView) rootView.findViewById(R.id.textViewNationality);
            natPlayer.setText(player.getPlayerNationality());

            bornPlayer = (TextView) rootView.findViewById(R.id.textViewBorn);
            bornPlayer.setText(player.getPlayerAge());

            posPlayer = (TextView) rootView.findViewById(R.id.textViewPosition);
            posPlayer.setText(player.getPlayerPosition());


            return rootView;
        }
    }

    public static class PlayersClass{
        private String[] playersName = {"Cédric Carrasso", "İsmail Çipe", "Eray İşcan", "Fernando Muslera", "Ahmet Şen", "Serdar Aziz", "Hakan Balta", "Ahmet Çalık", "Tarik Çamdal", "Jason Denayer", "Koray Günter"  , "Iasmin Latovlevici", "Martin Linnes"      , "Maicon"       , "Mariano"       ,
                "Atalay Babacan"    , "Emrah Başsan"    , "Younès Belhanda"       , "Tolga Ciğerci"        , "Nigel de Jong"     , "Ryan Donk"             , "Sofiane Féghouli"     , "Fernando"           , "Recep Gül"         ,
                "Umut Gündoğan"  , "Gökay Güney"     , "Selçuk İnan"     , "Papa N'Diaye"	   , "Yasin Öztekin"     , "Garry Rodrigues"   , "Eren Derdiyok", "Bafétimbi Gomis"   , "Sinan Gümüş"        ,
                "Igor Tudor"};

        private Integer[] playerImage = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15,
                R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27,
                R.drawable.a28, R.drawable.a29, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34};

        private String[] playerNationality={"France", "Turkey", "Turkey", "Uruguay", "Turkey", "Turkey", "Turkey", "Turkey", "Turkey", "Belgium", "Germany", "Romania", "Norway", "Brazil", "Brazil",
            "Turkey", "Turkey", "Morocco", "Turkey", "Netherlands", "Netherlands", "Algeria", "Brazil", "Turkey", "Belgium", "Turkey", "Turkey", "Senegal", "Turkey", "Cape Verde",
            "Switzerland", "France", "Germany", "Croatia"};

        private String[] playerAge = {"30/12/1981", "05/01/1995", "19/07/1991", "16/06/1986", "03/02/1999", "23/10/1990", "23/03/1983", "26/02/1994", "24/03/1991", "28/06/1995", "16/08/1994", "11/05/1986", "20/09/1991", "14/09/1988", "23/06/1986", "28/06/2000", "17/04/1992",
                "25/02/1990", "23/03/1992", "30/11/1984", "30/03/1986", "26/12/1989", "25/07/1987", "05/11/2000", "12/06/1990", "19/05/1999", "10/02/1985", "27/10/1990", "19/03/1987", "27/11/1990", "12/06/1988", "06/08/1985", "15/01/1994",
                "16/04/1978"};

        private Integer[] playerNumber={
                16, 13, 67, 1, 12, 4, 22, 5, 21, 64, 28, 33, 14, 3, 2, 36, 17, 10, 6, 34, 15, 89, 25, 37, 90, 88, 8, 20, 7, 24, 9, 18, 11, 0};

        private String[] playerPosition={"Goalkeeper", "Goalkeeper", "Goalkeeper", "Goalkeeper", "Goalkeeper", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender",
                "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Midfielder",
                "Forward", "Forward", "Forward", "Manager"};

        private Integer num;

        public PlayersClass(Integer numm){
            num=numm;
        }

        public Integer getPlayerImage() {
            Integer img=playerImage[num];
            return img;
        }

        public String getplayersName() {
            String name=playersName[num];
            return name;
        }

        public Integer getPlayerNumber() {
            Integer number = playerNumber[num];
            return number;
        }

        public String getPlayerAge() {
            String age = playerAge[num];
            return age;
        }

        public String getPlayerNationality() {
            String nat = playerNationality[num];
            return nat;
        }

        public String getPlayerPosition() {
            String pos = playerPosition[num];
            return pos;
        }
    }
}
