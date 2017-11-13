package android.uom.gr.galatasaray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);

        Intent mIntent = getIntent();
        String value = mIntent.getStringExtra("code");

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailMatchActivityFragment(value))
                    .commit();
        }
    }




    public static class DetailMatchActivityFragment extends Fragment {

        private String Matchcode;
        private TextView textcode;

        public DetailMatchActivityFragment(String intValue) {

            Matchcode=intValue;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail_matches, container, false);

            textcode = (TextView) rootView.findViewById(R.id.codeMatch);

                textcode.setText(Matchcode);

            return rootView;
        }


    }

}
