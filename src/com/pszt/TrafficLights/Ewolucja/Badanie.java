package com.pszt.TrafficLights.Ewolucja;

import com.pszt.TrafficLights.model.Model;
import com.pszt.TrafficLights.Controller.Simulation;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 09.05.13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */

/**
 * Klasa do badania osobnikow
 */
public class Badanie implements Runnable{
    /**
     * kopia modelu na której pracuje osobnik
     */
    private Model model;

    /**
     * symulacja którą przeprowadza badanie
     */
    private Simulation simulation;

    /**
     * watek na badanie
     */
    private Thread thread;

    /**
     * krok czasowy symulacji
     */
    final private int DELAY_FRAME = 100;

    /**
     * mówi, czy wątek zakończył badanie i zmienna wynik jest prawdziwa
     */
    private boolean badanieZakonczone;

    /**
     * wynik symulacji przeprowadzone na modelu do momentu az wszystkie samochody wyjada z planszy
     */
    private double wynik;

    public Badanie(Model model) {
        this.model = model;
        simulation = new Simulation(model);
        simulation.setSpawnCars(false);
    }

    @Override
    public void run() {
        while(model.getNumberOfCars() != 0){
            simulation.update(DELAY_FRAME);
        }
        wynik = model.getAverageTimeInTraffic();
        badanieZakonczone = true;

    }

    /**
     * startuje badanie
     */
    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public double getWynik() {
        return wynik;
    }

    public boolean isBadanieZakonczone() {
        return badanieZakonczone;
    }
}
