package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vr on 11/11/17.
 */

public class CompanyDBHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "companies.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMPANIES = "companies";
    public static final String COLUMN_ID = "companyId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PRODUCTS = "products";
    public static final String COLUMN_TYPE = "type";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_COMPANIES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_URL + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PRODUCTS + " TEXT, " +
                    COLUMN_TYPE + " TEXT " +
                    ")";


    public CompanyDBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIES);
        db.execSQL(TABLE_CREATE);
    }
}