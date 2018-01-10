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

public class CustomListviewMatches extends BaseAdapter {

    private int Pos;
    private Activity context;
    private List<String> data1;
    private List<String> data2;
    private List<String> data3;
    private List<String> data4;
    private List<String> data5;

    private LayoutInflater inflater;

    public CustomListviewMatches(Activity context) {

        this.context=context;
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
            view = inflater.inflate(R.layout.textviewcustomematches, null,true);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.team1.setText(data1.get(position));
        holder.score.setText(data2.get(position));
        holder.team2.setText(data3.get(position));

        if(!"FT".equals(data4.get(position))){

            holder.score.setBackgroundResource(R.color.matches);
        }
        else
            holder.score.setBackgroundResource(R.color.main);

        if(holder.team1.getText().equals("Galatasaray")) {
            holder.team1.setTextAppearance(context, R.style.boldText);
            holder.team1.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.team2.setTextAppearance(context, R.style.normalText);
            holder.team2.setTextColor(ContextCompat.getColor(context, R.color.color_black));

        }
        else if(holder.team2.getText().equals("Galatasaray")) {
            holder.team2.setTextAppearance(context, R.style.boldText);
            holder.team2.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.team1.setTextAppearance(context, R.style.normalText);
            holder.team1.setTextColor(ContextCompat.getColor(context, R.color.color_black));
        }
        else{
            holder.team2.setTextAppearance(context, R.style.normalText);
            holder.team2.setTextColor(ContextCompat.getColor(context, R.color.color_black));
            holder.team1.setTextAppearance(context, R.style.normalText);
            holder.team1.setTextColor(ContextCompat.getColor(context, R.color.color_black));
        }

        return view;

    };

    public void setDatas(List<String> data1,List<String> data2,List<String> data3,List<String> data4,List<String> data5) {
        this.context=context;
        this.data1=data1;
        this.data2=data2;
        this.data3 = data3;
        this.data4=data4;
        this.data5=data5;
        notifyDataSetChanged();
    }

     class ViewHolder {
        TextView team1;
        TextView score;
        TextView team2;

        public ViewHolder(View view) {

            team1 = (TextView) view.findViewById(R.id.team1);

            score = (TextView) view.findViewById(R.id.score);

            team2 = (TextView) view.findViewById(R.id.team2);
        }
    }



    public void clear(){
        data1.clear();
        data2.clear();
        data3.clear();
        notifyDataSetChanged();
}

    public void setPos(int pos) {
        Pos = pos;
    }

    public int getPos() {
        return Pos;
    }

    public List<String> getData1() {
        return data1;
    }

    public List<String> getData2() {
        return data2;
    }

    public List<String> getData3() {
        return data3;
    }

    public List<String> getData5() {
        return data5;
    }
}


