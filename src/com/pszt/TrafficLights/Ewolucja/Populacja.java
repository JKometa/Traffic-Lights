package com.pszt.TrafficLights.Ewolucja;

import java.util.*;
import java.lang.Math.*;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 02.05.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class Populacja {

    private final double MIN_LOSUJ = 0.1;
    private final double MAX_LOSUJ = 5.0;
    private Random generator;

    private ArrayList <Osobnik> osobnicy;
    private ArrayList <Osobnik> potomkowie;
    private int iloscOsobnikow;
    private int iloscCech;

    private double tau;
    private double tau2;

    public Populacja(int iloscCech) {
        this.iloscCech = iloscCech;
        iloscOsobnikow = 10;
        tau = 1 / (Math.sqrt(2 * Math.sqrt(iloscOsobnikow)));
        tau2 = 1 / (Math.sqrt(2 * iloscOsobnikow));
        generator = new Random();

        osobnicy = new ArrayList<Osobnik>();
        potomkowie = new ArrayList<Osobnik>();

    }

    private void generujRodzicow(){
        for(int i = 0; i < iloscOsobnikow; ++i){
            Osobnik nowyRodzic = new Osobnik(iloscCech);
//            osobnicy.

            double[] cechyNowegoRodzica = nowyRodzic.getIntervals();
            double[] rozkladyNowegoOsobnika = nowyRodzic.getRozklady();
            for (int j = 0; j < iloscCech; ++j){
                cechyNowegoRodzica[j] = losuj();
                rozkladyNowegoOsobnika[j] = generator.nextGaussian();

            }
        }

        ocenOsobnikow(osobnicy);

    }

    private void ocenOsobnikow(ArrayList <Osobnik> celOceny){

    }

    private double losuj(){
        double los =  MIN_LOSUJ + (MAX_LOSUJ - MIN_LOSUJ) * generator.nextDouble();
        return los;
    }
}
