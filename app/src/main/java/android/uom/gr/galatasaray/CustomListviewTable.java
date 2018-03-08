package android.uom.gr.galatasaray;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
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

        ViewHolder holder ;

        if (view == null) {
            view = inflater.inflate(R.layout.textviewcustomedtable, null,true);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.number.setText(data1.get(position));
        holder.teamname.setText(data2.get(position));
        holder.played.setText(data3.get(position));
        holder.gd.setText(data4.get(position));
        holder.points.setText(data5.get(position));


        if(holder.teamname.getText().equals("Aston Villa")) {
            holder.teamname.setTextAppearance(context, R.style.boldText);
            holder.teamname.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.number.setTextAppearance(context, R.style.boldText);
            holder.number.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.played.setTextAppearance(context, R.style.boldText);
            holder.played.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.gd.setTextAppearance(context, R.style.boldText);
            holder.gd.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.points.setTextAppearance(context, R.style.boldText);
            holder.points.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        else {
            holder.teamname.setTextAppearance(context, R.style.normalText);
            holder.teamname.setTextColor(ContextCompat.getColor(context, R.color.color_black));
            holder.number.setTextAppearance(context, R.style.normalText);
            holder.number.setTextColor(ContextCompat.getColor(context, R.color.color_black));
            holder.played.setTextAppearance(context, R.style.normalText);
            holder.played.setTextColor(ContextCompat.getColor(context, R.color.color_black));
            holder.gd.setTextAppearance(context, R.style.normalText);
            holder.gd.setTextColor(ContextCompat.getColor(context, R.color.color_black));
            holder.points.setTextAppearance(context, R.style.normalText);
            holder.points.setTextColor(ContextCompat.getColor(context, R.color.color_black));
        }

        return view;

    };

    public void setDatas(List<String> data1, List<String> data2,List<String> data3,List<String> data4,List<String> data5) {
        this.data1=data1;
        this.data2=data2;
        this.data3 = data3;
        this.data4=data4;
        this.data5 = data5;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView number;
        TextView teamname;
        TextView played;
        TextView gd;
        TextView points;


        public ViewHolder(View rowView) {


            number = (TextView) rowView.findViewById(R.id.Position);

            teamname = (TextView) rowView.findViewById(R.id.TeamName);

            played = (TextView) rowView.findViewById(R.id.Played);

            gd = (TextView) rowView.findViewById(R.id.GD);

            points = (TextView) rowView.findViewById(R.id.Points);
        }
    }
}