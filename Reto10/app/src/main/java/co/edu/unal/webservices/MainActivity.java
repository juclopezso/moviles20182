package co.edu.unal.webservices;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;
    private ListView mListView;
    private ArrayList<MyItem> mResultList;

    //INHERITED METHODS------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        mListView = (ListView) findViewById(R.id.list_view);
        mResultList = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId() ){
            case R.id.action_search:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                makeGithubSearchQuery();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //CLASS METHODS---------------------------------------------------------------------------------
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        new GithubQueryTask().execute( githubSearchUrl );
    }

    //CLASSES---------------------------------------------------------------------------------------
    public class GithubQueryTask extends AsyncTask< URL, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(URL... urls) {
            JSONArray result = null;
            try {
                result = new JSONArray( NetworkUtils.getResponseFromHttpUrl( urls[ 0 ] ) );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            mResultList.clear();
            try {
                for (int i = 0; i < result.length(); ++i) {
                    JSONObject obj = result.getJSONObject(i);
                    mResultList.add(new MyItem(
                            obj.getString("departamento"),
                            obj.getString("municipio"),
                            obj.getString("direccion"),
                            obj.getDouble("latitud"),
                            obj.getDouble("longitud"),
                            obj.getString("zona_inagurada")
                    ));

                }
            }catch ( JSONException e ){
                //Log.e( "ERROR JSON", "exception", e );
                //Toast.makeText( MainActivity.this, "Error procesando JSONArray", Toast.LENGTH_SHORT ).show();
            }
            if( result != null && result.length() > 0 ){
                ArrayAdapter<MyItem> arrayAdapter = new ArrayAdapter<MyItem>( MainActivity.this, android.R.layout.simple_list_item_1, mResultList );
                mListView.setAdapter( arrayAdapter );
            }
            else{
                mListView.setAdapter( null );
                Toast.makeText( MainActivity.this, "No se encontraron resultados", Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
