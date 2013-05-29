package com.pszt.TrafficLights.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class DataLog {

    private ArrayList<DataHandler> crossroadsData;
    private DataHandler dataHandler;
    private int numberOfCrossroads;

    /**
     * Konstruktor z podana iloscia skrzyzowan.
     *
     * @param numberOfCrossroads
     */
    public DataLog(int numberOfCrossroads) {
        this.numberOfCrossroads = numberOfCrossroads;
        crossroadsData = new ArrayList<DataHandler>();
    }

    /**
     * Dodanie struktury do listy
     *
     * @param data struktura danych
     */
    public void addData(DataStruct data) {
        if (dataHandler.getCrossroadStruct().size() <= numberOfCrossroads)
            dataHandler.addCrossroadData(data);
        else {
            crossroadsData.add(dataHandler);
            dataHandler.getCrossroadStruct().clear();
            dataHandler.addCrossroadData(data);
        }
    }

    /**
     * Dodanie danych do listy
     *
     * @param id                id skrzyzowania
     * @param interval          glowny interwal
     * @param intervalRed       interwal na swiatlo czerwone
     * @param intervalYellow    interwal na swiatlo zolte
     * @param intervalGreen     interwal na swiatlo zielone
     * @param averageCarsPassed srednia przepustowosc (srednia bo policzona w dwie strony)
     */
    public void addData(int id, long interval, long intervalRed, long intervalYellow, long intervalGreen, long averageCarsPassed) {

        DataStruct data = new DataStruct(id, interval, intervalRed, intervalYellow, intervalGreen, averageCarsPassed);
        if (dataHandler.getCrossroadStruct().size() <= numberOfCrossroads)
            dataHandler.addCrossroadData(data);
        else {
            crossroadsData.add(dataHandler);
            dataHandler.getCrossroadStruct().clear();
            dataHandler.addCrossroadData(data);
        }
    }

    public ArrayList<DataHandler> getCrossroadsData() {
        return crossroadsData;
    }

    public int getNumberOfCrossroads() {
        return numberOfCrossroads;
    }
}
