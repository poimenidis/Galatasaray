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

public class CustomListviewTable extends BaseAdapter {

    private final Activity context;
    private List<String> data1;
    private List<String> data2;
    private List<String> data3;
    private List<String> data4;
    private List<String> data5;
   private LayoutInflater inflater;


    public CustomListviewTable(Activity context, List<String> data1, List<String> data2,List<String> data3,List<String> data4,List<String> data5) {


        this.context=context;
        this.data1 =data1;
        this.data2=data2;
        this.data3 = data3;
        this.data4=data4;
        this.data5 = data5;
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

        View rowView=inflater.inflate(R.layout.textviewcustomedtable, null);

        TextView number = (TextView) rowView.findViewById(R.id.Position);
        TextView teamname = (TextView) rowView.findViewById(R.id.TeamName);
        TextView played = (TextView) rowView.findViewById(R.id.Played);
        TextView gd = (TextView) rowView.findViewById(R.id.GD);
        TextView points = (TextView) rowView.findViewById(R.id.Points);

        number.setText(data1.get(position));
        teamname.setText(data2.get(position));
        played.setText(data3.get(position));
        gd.setText(data4.get(position));
        points.setText(data5.get(position));

        return rowView;

    };

    public void setDatas(List<String> data1, List<String> data2,List<String> data3,List<String> data4,List<String> data5) {
        this.data1=data1;
        this.data2=data2;
        this.data3 = data3;
        this.data4=data4;
        this.data5 = data5;
    }
}