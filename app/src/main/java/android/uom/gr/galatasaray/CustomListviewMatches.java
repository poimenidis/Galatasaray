package android.uom.gr.galatasaray;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Κώστας Ποιμενίδης on 27/10/2017.
 */

public class CustomListviewMatches extends BaseAdapter {

    private final Activity context;
    private List<String> data1;
    private List<String> data2;
    private List<String> data3;
    private LayoutInflater inflater;

    public CustomListviewMatches(Activity context, List<String> itemname, List<String> imgid,List<String> data3) {

        this.context=context;
        this.data1 =itemname;
        this.data2=imgid;
        this.data3 = data3;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data1.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.textviewcustomematches, null,true);

        TextView teame1 = (TextView) rowView.findViewById(R.id.team1);
        TextView score = (TextView) rowView.findViewById(R.id.score);
        TextView team2 = (TextView) rowView.findViewById(R.id.team2);

        teame1.setText(data1.get(position));
        score.setText(data2.get(position));
        team2.setText(data3.get(position));

        return rowView;

    };

    public void setDatas(List<String> data1,List<String> data2,List<String> data3) {
        this.data1=data1;
        this.data2=data2;
        this.data3 = data3;
    }
}


