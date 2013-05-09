package com.pszt.TrafficLights.Ewolucja;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 10.05.13
 * Time: 00:18
 * To change this template use File | Settings | File Templates.
 */

/**
 * Klasa do porównywania osobnikow
 */
public class KomparatorOsobnikow implements  Comparator <Osobnik>{

    @Override
    public int compare(Osobnik osobnik, Osobnik osobnik2) {
        if(osobnik2 == null) return -1;
        if(osobnik.getWynik() > osobnik2.getWynik()) return 1;
        else if (osobnik.getWynik() < osobnik2.getWynik()) return -1;
        else return 0;
    }
}
