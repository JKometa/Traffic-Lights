package com.pszt.TrafficLights.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 02.05.13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class DataHandler {

    private ArrayList<DataStruct> crossroadStruct;


    public DataHandler(){
        crossroadStruct = new ArrayList<DataStruct>();
    }

    public void addCrossroadData(DataStruct data){
        crossroadStruct.add(data);
    }

    public void removeData(DataStruct data){
        crossroadStruct.remove(data);
    }

    public ArrayList<DataStruct> getCrossroadStruct(){
        return crossroadStruct;
    }


}
