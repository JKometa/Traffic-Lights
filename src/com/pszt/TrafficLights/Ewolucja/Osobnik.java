package com.pszt.TrafficLights.Ewolucja;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 02.05.13
 * Time: 09:24
 * To change this template use File | Settings | File Templates.
 */
public class Osobnik {


    private double[] intervals;
    private double[] rozklady;
    private double wynik;

    public Osobnik(int iloscCech) {
        intervals = new double[iloscCech];
        rozklady  = new double[iloscCech];
    }

    public double[] getRozklady() {
        return rozklady;
    }

    public void setRozklady(double[] rozklady) {
        this.rozklady = rozklady;
    }

    public double[] getIntervals() {
        return intervals;
    }

    public void setIntervals(double[] intervals) {
        this.intervals = intervals;
    }

    public double getWynik() {
        return wynik;
    }

    public void setWynik(double wynik) {
        this.wynik = wynik;
    }


}
