package com.pszt.TrafficLights.Controller;

import com.pszt.TrafficLights.model.Model;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements  Runnable{
    private Model model;

    private Simulation simulation;
    private Thread thread;
    final private int DELAY_FRAME = 50;

    public Controller() {
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY_FRAME - timeDiff;

            if (sleep > 0){
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
            }

            simulation.update(DELAY_FRAME);

            /*
             * tu bedzie funkcja z view rysujaca i przekazujaca kopie samochodow i skrzyzowan
             */
        }
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void setModel(Model model) {
        this.model = model;
        this.simulation = new Simulation(model);
    }


}
