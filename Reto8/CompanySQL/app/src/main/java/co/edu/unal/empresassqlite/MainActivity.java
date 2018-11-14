package co.edu.unal.empresassqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.CompanyOperations;
import Model.Company;

public class MainActivity extends AppCompatActivity {

    private Button mCreateButton;
    private Button mUpdateButton;
    private Button mDeleteButton;
    private Button mListButton;
    private CompanyOperations companyOps;
    private static final String EXTRA_COM_ID = "co.edu.unal.empresassqlite.companyId";
    private static final String EXTRA_CREATE_UPDATE = "co.edu.unal.empresassqlite.create_update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mCreateButton = (Button) findViewById(R.id.button_create);
        mUpdateButton = (Button) findViewById(R.id.button_update);
        mDeleteButton = (Button) findViewById(R.id.button_delete);
        mListButton = (Button)findViewById(R.id.button_list);



        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CreateUpdateCompany.class);
                i.putExtra(EXTRA_CREATE_UPDATE, "Create");
                startActivity(i);
            }
        });
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCompanyIdAndUpdate();
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCompanyIdAndDelete();
            }
        });
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListAllCompanies.class);
                startActivity(i);
            }
        });
    }


    public void getCompanyIdAndUpdate(){

        LayoutInflater li = LayoutInflater.from(this);
        View getCompanyIdView = li.inflate(R.layout.dialog_get_company_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(getCompanyIdView);

        final EditText userInput = (EditText) getCompanyIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        companyOps = new CompanyOperations(MainActivity.this);
                        companyOps.open();
                        try {
                            companyOps.getCompany( Long.parseLong(userInput.getText().toString()) );
                            Intent i = new Intent(MainActivity.this, CreateUpdateCompany.class);
                            i.putExtra(EXTRA_CREATE_UPDATE, "Update");
                            i.putExtra(EXTRA_COM_ID, Long.parseLong(userInput.getText().toString()));
                            startActivity(i);
                        }catch( Exception e ){
                            Toast.makeText( MainActivity.this, "No company found with the given id.", Toast.LENGTH_SHORT ).show();
                        }
                        companyOps.close();
                    }
                }).create()
                .show();

    }


    public void getCompanyIdAndDelete(){

        LayoutInflater li = LayoutInflater.from(this);
        View getCompanyIdView = li.inflate(R.layout.dialog_get_company_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_emp_id.xml to alertdialog builder
        alertDialogBuilder.setView(getCompanyIdView);

        final EditText userInput = (EditText) getCompanyIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        companyOps = new CompanyOperations(MainActivity.this);
                        companyOps.open();
                        try {
                            companyOps.deleteCompany( Long.parseLong(userInput.getText().toString()) );
                            Toast t = Toast.makeText(MainActivity.this,"Company removed",Toast.LENGTH_SHORT);
                            t.show();
                        }catch( Exception e ){
                            Toast.makeText( MainActivity.this, "No company found with the given id.", Toast.LENGTH_SHORT ).show();
                        }
                        companyOps.close();
                    }
                }).create()
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if( companyOps != null ) companyOps.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if( companyOps != null ) companyOps.close();
    }
}
