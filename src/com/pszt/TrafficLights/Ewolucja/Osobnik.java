package com.pszt.TrafficLights.Ewolucja;

import java.util.Random;
import java.lang.Math;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 02.05.13
 * Time: 09:24
 * To change this template use File | Settings | File Templates.
 */
public class Osobnik {


    private long[] cechy;
    private double[] rozklady;
    private int iloscCech;
    private double wynik;

    public Osobnik(int iloscCech) {
        this.iloscCech = iloscCech;
        this.cechy = new long[this.iloscCech];
        this.rozklady  = new double[this.iloscCech];
    }

    /**
     * Konstruktor tworzący osobnika w zarodku, na podstawie rodzicow
     * krzyżowanie
     * @param matka rodzic
     * @param ojciec rodzic
     */
    public Osobnik(Osobnik matka, Osobnik ojciec){
        this.iloscCech = matka.iloscCech;
        this.cechy = new long[this.iloscCech];
        this.rozklady  = new double[this.iloscCech];

        for(int i = 0 ; i < iloscCech; ++i){
            cechy[i] = (matka.cechy[i] + ojciec.cechy[i]) / 2;
            rozklady[i] = (matka.cechy[i] + ojciec.cechy[i]) / 2;
        }
    }

    /**
     * mutuje osobnika
      * @param tau wyliczone na podstawie wielkości populacji
     * @param tau2 wyliczone na podstawie wielkości populacji
     * @param generator generator losujący cechy dla danej populacji
     */
    public void mutuj(double tau, double tau2, Random generator){
        double xi = generator.nextGaussian();
        for(double rozklad : rozklady){
            rozklad *= Math.exp(xi * tau2 + tau * generator.nextGaussian());

        }

        for(int i = 0; i < iloscCech; ++i){
            cechy[i] += generator.nextGaussian() * rozklady[i];
            if(cechy[i] < 300)
                cechy[i] = 300;

            if(cechy[i] > 5000)
                cechy[i] = 5000;

//            System.out.println("Cecha " + cechy[i]);
        }



    }

    public double[] getRozklady() {
        return rozklady;
    }

    public void setRozklady(double[] rozklady) {
        this.rozklady = rozklady;
    }

    public long[] getCechy() {
        return cechy;
    }

    public void setCechy(long[] cechy) {
        this.cechy = cechy;
    }

    public double getWynik() {
        return wynik;
    }

    public void setWynik(double wynik) {
        this.wynik = wynik;
    }


}
