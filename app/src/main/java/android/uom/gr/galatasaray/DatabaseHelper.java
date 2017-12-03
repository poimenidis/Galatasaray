package android.uom.gr.galatasaray;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "Nationality";
    private static final String COL4 = "image";
    private static final String COL5 = "age";
    private static final String COL6 = "number";
    private static final String COL7 = "position";

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


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, "+COL3+" TEXT, "+COL4+" INTEGER, "+COL5+" TEXT, "+COL6+" INTEGER, "+COL7+" TEXT)";
        db.execSQL(createTable);


    }

    public void addElements(){
        Cursor data = getData();
        ArrayList<String> listData = new ArrayList<>();


        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }


        if(listData.isEmpty()) {

            for (int i = 0; i < playerNumber.length; i++) {
                boolean insertData = addData(playersName[i], playerNationality[i], playerImage[i], playerAge[i], playerNumber[i], playerPosition[i]);


            }
        }

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item,String nationality,int img,String age,int num,String pos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, nationality);
        contentValues.put(COL4, img);
        contentValues.put(COL5, age);
        contentValues.put(COL6, num);
        contentValues.put(COL7, pos);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + nationality + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + img + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + age + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + num + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + pos + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}