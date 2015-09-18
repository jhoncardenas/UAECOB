package com.example.jhon.dataanalitic;

/**
 * Created by Jhon on 11/06/2015.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class adapterItemPairOfNumbers extends RecyclerView.Adapter<adapterItemPairOfNumbers.ViewHolder> {
    private ArrayList<itemPairOfNumbers> itemsData;

    public adapterItemPairOfNumbers(ArrayList<itemPairOfNumbers> itemsData) {
        this.itemsData = itemsData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public adapterItemPairOfNumbers.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // create a new view

        //El Item hay que crearlo con la estructura  R.layout.el_layout_del_item, parent, false
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pair_of_numbers, parent, false);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView proxySize;


        // public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            proxySize = (TextView) itemLayoutView.findViewById(R.id.proxySize);


        }
    }
    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (itemsData != null)
            return itemsData.size();
        else
            return 0;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.proxySize.setText(itemsData.get(position).getEstimateProxySize().toString());
    }
}
