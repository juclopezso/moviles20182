package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.Company;

/**
 * Created by vr on 11/11/17.
 */

public class CompanyOperations {
    public static final String LOGTAG = "COM_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            CompanyDBHandler.COLUMN_ID,
            CompanyDBHandler.COLUMN_NAME,
            CompanyDBHandler.COLUMN_URL,
            CompanyDBHandler.COLUMN_PHONE,
            CompanyDBHandler.COLUMN_EMAIL,
            CompanyDBHandler.COLUMN_PRODUCTS,
            CompanyDBHandler.COLUMN_TYPE
    };

    public CompanyOperations(Context context){
        dbhandler = new CompanyDBHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();
    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public Company createCompany(Company Company){
        ContentValues values  = new ContentValues();
        values.put(CompanyDBHandler.COLUMN_NAME, Company.getName());
        values.put(CompanyDBHandler.COLUMN_URL, Company.getUrl());
        values.put(CompanyDBHandler.COLUMN_PHONE, Company.getPhone());
        values.put(CompanyDBHandler.COLUMN_EMAIL, Company.getEmail());
        values.put(CompanyDBHandler.COLUMN_PRODUCTS, Company.getProducts());
        values.put(CompanyDBHandler.COLUMN_TYPE, Company.getType());
        long insertid = database.insert(CompanyDBHandler.TABLE_COMPANIES,null,values);
        Company.setCompanyId(insertid);
        return Company;
    }

    // Getting single Company
    public Company getCompany(long id) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANIES,allColumns,CompanyDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Company c = new Company(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        return c;
    }

    public List<Company> getCompaniesByName( String name ){
        Cursor cursor = database.query( CompanyDBHandler.TABLE_COMPANIES,allColumns,CompanyDBHandler.COLUMN_NAME + " LIKE '%" + name + "%'", null, null, null, null, null );
        List<Company> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Company company = new Company();
                company.setCompanyId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                company.setName(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                company.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_URL)));
                company.setPhone(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                company.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                company.setProducts(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                company.setType(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                companies.add(company);
            }
        }
        return companies;
    }

    public List<Company> getCompaniesByType( String type ){
        Cursor cursor = database.query( CompanyDBHandler.TABLE_COMPANIES,allColumns,CompanyDBHandler.COLUMN_TYPE + " LIKE '%" + type + "%'", null, null, null, null, null );
        List<Company> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Company company = new Company();
                company.setCompanyId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                company.setName(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                company.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_URL)));
                company.setPhone(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                company.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                company.setProducts(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                company.setType(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                companies.add(company);
            }
        }
        return companies;
    }

    public List<Company> getAllCompanies() {

        Cursor dbCursor = database.query(CompanyDBHandler.TABLE_COMPANIES, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANIES,allColumns,null,null,null, null, null);

        List<Company> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Company company = new Company();
                company.setCompanyId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                company.setName(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                company.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_URL)));
                company.setPhone(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                company.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                company.setProducts(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                company.setType(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                companies.add(company);
            }
        }
        // return All Companies
        return companies;
    }




    // Updating Company
    public int updateCompany(Company company) {

        ContentValues values = new ContentValues();;
        values.put(CompanyDBHandler.COLUMN_NAME, company.getName());
        values.put(CompanyDBHandler.COLUMN_URL, company.getUrl());
        values.put(CompanyDBHandler.COLUMN_PHONE, company.getPhone());
        values.put(CompanyDBHandler.COLUMN_EMAIL, company.getEmail());
        values.put(CompanyDBHandler.COLUMN_PRODUCTS, company.getProducts());
        values.put(CompanyDBHandler.COLUMN_TYPE, company.getType());

        // updating row
        return database.update(CompanyDBHandler.TABLE_COMPANIES, values,
                CompanyDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(company.getCompanyId())});
    }

    // Deleting Company
    public void deleteCompany(long companyId) {
        database.delete(CompanyDBHandler.TABLE_COMPANIES, CompanyDBHandler.COLUMN_ID + "=" + companyId, null);
    }
}
