package co.edu.unal.empresassqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.CompanyOperations;
import Model.Company;

/**
 * Created by vr on 11/11/17.
 */

public class CreateUpdateCompany extends AppCompatActivity {
    private static final String EXTRA_COM_ID = "co.edu.unal.empresassqlite.companyId";
    private static final String EXTRA_CREATE_UPDATE = "co.edu.unal.empresassqlite.create_update";
    private EditText mNameEditText;
    private EditText mUrlEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mProductsEditText;
    private EditText mTypeEditText;
    private Button mCreateUpdateButton;
    private Company newCompany;
    private Company oldCompany;
    private String mode;
    private long companyId;
    private CompanyOperations companyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_update_company);
        newCompany = new Company();
        oldCompany = new Company();
        mNameEditText = (EditText)findViewById(R.id.edit_text_name);
        mUrlEditText = (EditText)findViewById(R.id.edit_text_url);
        mPhoneEditText = (EditText) findViewById(R.id.edit_text_phone);
        mEmailEditText = (EditText)findViewById(R.id.edit_text_email);
        mProductsEditText = (EditText)findViewById(R.id.edit_text_product);
        mTypeEditText = (EditText) findViewById(R.id.edit_text_type);
        mCreateUpdateButton = (Button)findViewById(R.id.button_create_update);
        companyData = new CompanyOperations(this);
        companyData.open();


        mode = getIntent().getStringExtra(EXTRA_CREATE_UPDATE);
        if(mode.equals("Update")){
            mCreateUpdateButton.setText("Update Company");
            companyId = getIntent().getLongExtra(EXTRA_COM_ID,0);
            initializeCompany(companyId);
        }

        mCreateUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mode.equals("Create")) {
                    newCompany.setName(mNameEditText.getText().toString());
                    newCompany.setUrl(mUrlEditText.getText().toString());
                    newCompany.setPhone(mPhoneEditText.getText().toString());
                    newCompany.setEmail(mEmailEditText.getText().toString());
                    newCompany.setProducts(mProductsEditText.getText().toString());
                    newCompany.setType(mTypeEditText.getText().toString());
                    companyData.createCompany(newCompany);
                    Toast t = Toast.makeText(CreateUpdateCompany.this, "Company "+ newCompany.getName() + " has been created", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(CreateUpdateCompany.this,MainActivity.class);
                    startActivity(i);
                }else {
                    oldCompany.setName(mNameEditText.getText().toString());
                    oldCompany.setUrl(mUrlEditText.getText().toString());
                    oldCompany.setPhone(mPhoneEditText.getText().toString());
                    oldCompany.setEmail(mEmailEditText.getText().toString());
                    oldCompany.setProducts(mProductsEditText.getText().toString());
                    oldCompany.setType(mTypeEditText.getText().toString());
                    companyData.updateCompany(oldCompany);
                    Toast t = Toast.makeText(CreateUpdateCompany.this, "Company "+ oldCompany.getName() + " has been updated", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(CreateUpdateCompany.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });


    }

    private void initializeCompany(long companyId) {
        oldCompany = companyData.getCompany(companyId);
        mNameEditText.setText(oldCompany.getName());
        mUrlEditText.setText(oldCompany.getUrl());
        mPhoneEditText.setText(oldCompany.getPhone());
        mEmailEditText.setText(oldCompany.getEmail());
        mProductsEditText.setText(oldCompany.getProducts());
        mTypeEditText.setText(oldCompany.getType());
    }

}
