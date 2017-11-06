package android.uom.gr.galatasaray;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Κώστας Ποιμενίδης on 27/10/2017.
 */

public class CustomListviewPlayers extends BaseAdapter {

    private final Activity context;
    private String[] data1;
    private String[] data2;
    private Integer[] data3;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ROW = 1;
    private LayoutInflater inflater;





    public CustomListviewPlayers(Activity context, String[] itemname, String[]imgid,Integer[] data3) {


        this.context=context;
        this.data1 =itemname;
        this.data2=imgid;
        this.data3 = data3;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data1.length;
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



        holder.name.setText(data1[position]);
        holder.pos.setText(data2[position]);
        holder.img.setImageResource(data3[position]);

        return view;

    };

    public void setDatas(String[] data1,String[] data2,Integer[] data3) {
        this.data1=data1;
        this.data2=data2;
        this.data3 = data3;
    }


    public static class ViewHolder {
        public final TextView name;
        public final TextView pos;
        public final ImageView img;

        public ViewHolder(View view) {
            pos = (TextView) view.findViewById(R.id.Textplayers);
            name = (TextView) view.findViewById(R.id.textviewposition);
            img = (ImageView) view.findViewById(R.id.imageplayer);
        }
    }
}