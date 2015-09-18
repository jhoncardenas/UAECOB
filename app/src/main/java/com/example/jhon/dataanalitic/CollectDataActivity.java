package com.example.jhon.dataanalitic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class CollectDataActivity extends ActionBarActivity {

    private RecyclerView recyclerViewItems;
    private adapterItemPairOfNumbers mAdapter;
    private ArrayList<itemPairOfNumbers> listItemPairsOfNumbers;
    private EditText proxySize;
    private ImageButton addAction;
    private Double mean;
    private Double standartDesviation;
    private Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colect_data_activity);


        if (listItemPairsOfNumbers == null) {
            listItemPairsOfNumbers = new ArrayList<>();
        }

        setProxySize((EditText) findViewById(R.id.proxySize));
        setAddAction((ImageButton) findViewById(R.id.addAction));
        recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        setRecyclerAdapter();

        addAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proxySize.getText().toString().trim().length() == 0) {
                    new AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage("Enter the Proxy Size")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();

                } else {
                        itemPairOfNumbers item = new itemPairOfNumbers();
                        item.setEstimateProxySize(Double.valueOf(proxySize.getText().toString()));
                        listItemPairsOfNumbers.add(item);
                        mAdapter.notifyDataSetChanged();
                        ClearInfoAction();
                    }
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collect_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calculate) {

            Log.i("DEBUG PRINCIPAL", "APRETE EL BOTON");



            Intent intent = new Intent(this, ShowResultsActivity.class);

            doTheMaths();
            intent.putExtra("mean", mean);
            intent.putExtra("standartDesviation", standartDesviation);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setRecyclerAdapter() {
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new adapterItemPairOfNumbers(listItemPairsOfNumbers);
        recyclerViewItems.setAdapter(mAdapter);
        recyclerViewItems.setItemAnimator(new DefaultItemAnimator());
        final RecyclerView.ItemDecoration itemDecoration = new SampleDivider(this);
        recyclerViewItems.addItemDecoration(itemDecoration);
    }

    private void ClearInfoAction() {
        proxySize.setText("");
    }


    public EditText getProxySize() {
        return proxySize;
    }

    public void setProxySize(EditText proxySize) {
        this.proxySize = proxySize;
    }


    public ImageButton getAddAction() {
        return addAction;
    }

    public void setAddAction(ImageButton addAction) {
        this.addAction = addAction;
    }

    public void doTheMaths(){
        mean = Double.valueOf(0);
        standartDesviation = Double.valueOf(0);
        if (listItemPairsOfNumbers.size() != 0) {
            for (int i = 0; i < listItemPairsOfNumbers.size(); i++) {
                mean = mean + listItemPairsOfNumbers.get(i).getEstimateProxySize();
            }
            mean = mean / listItemPairsOfNumbers.size();
            for (int i=0; i<listItemPairsOfNumbers.size();i++)
            {
                standartDesviation = standartDesviation + Math.pow(listItemPairsOfNumbers.get(i).getEstimateProxySize() - mean, 2);
            }
            standartDesviation = Math.sqrt(standartDesviation / (listItemPairsOfNumbers.size() - 1));
            standartDesviation = new BigDecimal(standartDesviation).setScale(2, RoundingMode.HALF_UP).doubleValue();


        }




    }
}
