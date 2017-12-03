package android.uom.gr.galatasaray;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Κώστας Ποιμενίδης on 27/10/2017.
 */

public class CustomListviewPlayers extends BaseAdapter {

    private final Activity context;
    private ArrayList<String> data1;
    private ArrayList<String> data2;
    private ArrayList<Integer> data3;
    private ArrayList<Integer> data4;
    private LayoutInflater inflater;


    CustomListviewPlayers(Activity context) {


        this.context=context;

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);
         mDatabaseHelper.addElements();
         Cursor data = mDatabaseHelper.getData();
         ArrayList<String> playersName = new ArrayList<>();
         ArrayList<String> playerPosition = new ArrayList<>();
         ArrayList<Integer> playerImage = new ArrayList<>();
         ArrayList<Integer> playerNumber = new ArrayList<>();
//
//
         while(data.moveToNext()){
             //get the value from the database in column 1
             //then add it to the ArrayList
             playersName.add(data.getString(1));
             playerPosition.add(data.getString(6));
             playerImage.add(data.getInt(3));
             playerNumber.add(data.getInt(5));

         }

        this.data1 =playersName;
        this.data2=playerPosition;
        this.data3 = playerImage;
        this.data4 = playerNumber;

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
            view = inflater.inflate(R.layout.textviewcustomedplayers, null,true);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }



        holder.name.setText(data1.get(position));
        holder.pos.setText(data2.get(position));
        holder.imgplayer.setImageResource(data3.get(position));

        if(data2.get(position).equals("Goalkeeper"))
            holder.shirtNoText.setImageResource(R.drawable.goalkeepercustom);
        else
            holder.shirtNoText.setImageResource(R.drawable.number);

        if( data4.get(position)==0) {
            holder.No.setVisibility(TextView.INVISIBLE);

            holder.shirtNoText.setVisibility(TextView.INVISIBLE);

        }
        else{
            String k= data4.get(position).toString();
            holder.No.setText(k);
            holder.No.setVisibility(TextView.VISIBLE);

            holder.shirtNoText.setVisibility(TextView.VISIBLE);
        }

        return view;

    };




     static class ViewHolder {
         final TextView name;
         final TextView pos;
         final ImageView imgplayer;
         final TextView No;
         final ImageView shirtNoText;

         ViewHolder(View view) {
            pos = (TextView) view.findViewById(R.id.Textplayers);
            name = (TextView) view.findViewById(R.id.textviewposition);
            imgplayer = (ImageView) view.findViewById(R.id.imageplayer);
            No = (TextView) view.findViewById(R.id.NNumber);
            shirtNoText = (ImageView) view.findViewById(R.id.imagenum) ;
        }
    }
}