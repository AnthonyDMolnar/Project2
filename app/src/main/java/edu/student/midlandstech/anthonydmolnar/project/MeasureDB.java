package edu.student.midlandstech.anthonydmolnar.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MeasureDB extends SQLiteOpenHelper {
    public static final String QUERY1 = "create table measure ( id integer primary key autoincrement, name text, measure real)";
    public static final String QUERY2 = "insert into measure (name, measure) values ('teaspoon', 1), ('tablespoon', 3), ('cup', 48), ('Fl.Ounce', 6);";
    public MeasureDB(Context context) {super(context, "MeasureDB", null, 1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY1);
        sqLiteDatabase.execSQL(QUERY2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {
        sqLiteDatabase.execSQL("drop table if exists measure;");
        onCreate(sqLiteDatabase);
    }

    public void insertMeasure(String name, double measure) {
        String query = "insert into measure (name, measure) values ('"
            + name + "' ," + measure + ");";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        Log.w("Mainactivity", query);
    }

    public ArrayList<Measure> selectAll() {
        String sqlQuery = "select * from measure";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<Measure> measures = new ArrayList<>();
        while(cursor.moveToNext()) {
            Measure currentMeasure
                    = new Measure( Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2));
            measures.add(currentMeasure);
        }
        db.close();
        return measures;
    }
}
