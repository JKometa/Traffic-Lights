package com.pszt.TrafficLights.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 02.05.13
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class DataStruct {

    private int id;
    private long interval;
    private long intervalRed;
    private long intervalYellow;
    private long intervalGreen;
    private long averageCarsPassed;


    public DataStruct() {

    }

    /**
     * KOnstruktor struktury danych
     *
     * @param id                id skrzyzowania
     * @param interval          glowny interwal
     * @param intervalRed       interwal na swiatlo czerwone
     * @param intervalYellow    interwal na swiatlo zolte
     * @param intervalGreen     interwal na swiatlo zielone
     * @param averageCarsPassed srednia przepustowosc (srednia bo policzona w dwie strony)
     */
    public DataStruct(int id, long interval, long intervalRed, long intervalYellow, long intervalGreen, long averageCarsPassed) {
        this.id = id;
        this.interval = interval;
        this.intervalGreen = intervalGreen;
        this.intervalRed = intervalRed;
        this.intervalYellow = intervalYellow;
        this.averageCarsPassed = averageCarsPassed;
    }

    public void updateData(int id, long interval, long intervalRed, long intervalYellow, long intervalGreen, long averageCarsPassed) {
        this.id = id;
        this.interval = interval;
        this.intervalGreen = intervalGreen;
        this.intervalRed = intervalRed;
        this.intervalYellow = intervalYellow;
        this.averageCarsPassed = averageCarsPassed;
    }

    public void updateIntervals(long interval, long intervalRed, long intervalYellow, long intervalGreen) {
        this.interval = interval;
        this.intervalRed = intervalRed;
        this.intervalYellow = intervalYellow;
        this.intervalGreen = intervalGreen;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setIntervalRed(long intervalRed) {
        this.intervalRed = intervalRed;
    }

    public void setIntervalYellow(long intervalYellow) {
        this.intervalYellow = intervalYellow;
    }

    public void setIntervalGreen(long intervalGreen) {
        this.intervalGreen = intervalGreen;
    }

    public void setAverageCarsPassed(long averageCarsPassed) {
        this.averageCarsPassed = averageCarsPassed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public long getInterval() {
        return interval;
    }

    public long getIntervalRed() {
        return intervalRed;
    }

    public long getIntervalYellow() {
        return intervalYellow;
    }

    public long getIntervalGreen() {
        return intervalGreen;
    }

    public long getAverageCarsPassed() {
        return averageCarsPassed;
    }
}
