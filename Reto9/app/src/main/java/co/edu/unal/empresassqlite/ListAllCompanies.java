package co.edu.unal.empresassqlite;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import DB.CompanyOperations;
import Model.Company;

/**
 * Created by vr on 11/11/17.
 */

public class ListAllCompanies extends AppCompatActivity {

    private CompanyOperations companyOps;
    private ListView listView;
    private List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_companies);
        listView = (ListView) findViewById( R.id.list );
        companyOps = new CompanyOperations(this);
        companyOps.open();
        companies = companyOps.getAllCompanies();
        companyOps.close();
        ArrayAdapter<Company> adapter = new ArrayAdapter<Company>(this,
                android.R.layout.simple_list_item_1, companies);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_filter, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LayoutInflater li = LayoutInflater.from( this );
        View dialogView = li.inflate( R.layout.dialog_get_string, null );
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView( dialogView );

        TextView textView = (TextView) dialogView.findViewById( R.id.tv_dialog_string );
        final EditText editText = (EditText) dialogView.findViewById( R.id.et_dialog_string );

        switch( item.getItemId() ){
            case R.id.menu_filter_name:
                textView.setText( "Insert the name" );
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CompanyOperations ops = new CompanyOperations(ListAllCompanies.this);
                        ops.open();
                        companies = ops.getCompaniesByName( editText.getText().toString() );
                        ops.close();
                        updateListView();
                    }
                }).create().show();
                return true;
            case R.id.menu_filter_type:
                textView.setText( "Insert the type" );
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CompanyOperations ops = new CompanyOperations(ListAllCompanies.this);
                        ops.open();
                        companies = ops.getCompaniesByName( editText.getText().toString() );
                        ops.close();
                        updateListView();
                    }
                }).create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateListView(){
        ArrayAdapter<Company> adapter = new ArrayAdapter<Company>( ListAllCompanies.this, android.R.layout.simple_list_item_1, companies );
        listView.setAdapter( adapter );
        if( companies.size() == 0 ){
            Toast.makeText( ListAllCompanies.this, "No Company found", Toast.LENGTH_SHORT ).show();
        }
    }
}