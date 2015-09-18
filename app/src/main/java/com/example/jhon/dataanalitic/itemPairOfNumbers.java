package com.example.jhon.dataanalitic;

/**
 * Created by Jhon on 11/06/2015.
 */
public class itemPairOfNumbers {

    private Double estimateProxySize;


    public itemPairOfNumbers (double estimateProxySize){
        this.estimateProxySize = estimateProxySize;
    }

    public itemPairOfNumbers (){
        estimateProxySize = Double.valueOf(0);
    }


    public Double getEstimateProxySize() {
        return estimateProxySize;
    }

    public void setEstimateProxySize(Double estimateProxySize) {
        this.estimateProxySize = estimateProxySize;
    }

}