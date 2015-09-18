package com.example.jhon.dataanalitic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Jhon on 12/06/2015.
 */
public class ShowResultsActivity extends ActionBarActivity {
    private TextView mean;
    private TextView standartDesviation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_results_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        setMean((TextView) findViewById(R.id.mean));
        setStandartDesviation((TextView) findViewById(R.id.standartDesviation));


        getMean().setText(String.valueOf(extras.getDouble("mean")));
        getStandartDesviation().setText(String.valueOf(extras.getDouble("standartDesviation")));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {

            Log.i("DEBUG SHOW", "APRETE EL BOTON");
            Intent intent = new Intent(this, CollectDataActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public TextView getMean() {
        return mean;
    }

    public void setMean(TextView mean) {
        this.mean = mean;
    }

    public TextView getStandartDesviation() {
        return standartDesviation;
    }

    public void setStandartDesviation(TextView standartDesviation) {
        this.standartDesviation = standartDesviation;
    }
}
