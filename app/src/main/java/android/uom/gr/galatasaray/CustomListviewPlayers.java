package android.uom.gr.galatasaray;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Κώστας Ποιμενίδης on 27/10/2017.
 */

public class CustomListviewPlayers extends ArrayAdapter<String> {

    private final Activity context;
    private String[] data1;
    private String[] data2;
    private Integer[] data3;

    public CustomListviewPlayers(Activity context, String[] itemname, String[]imgid,Integer[] data3) {
        super(context, R.layout.textviewcustomedplayers, itemname);

        this.context=context;
        this.data1 =itemname;
        this.data2=imgid;
        this.data3 = data3;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.textviewcustomedplayers, null,true);

        TextView teame1 = (TextView) rowView.findViewById(R.id.textviewposition);
        TextView score = (TextView) rowView.findViewById(R.id.Textplayers);
        ImageView team2 = (ImageView) rowView.findViewById(R.id.imageplayer);

        teame1.setText(data1[position]);
        score.setText(data2[position]);
        team2.setImageResource(data3[position]);

        return rowView;

    };

    public void setDatas(String[] data1,String[] data2,Integer[] data3) {
        this.data1=data1;
        this.data2=data2;
        this.data3 = data3;
    }
}