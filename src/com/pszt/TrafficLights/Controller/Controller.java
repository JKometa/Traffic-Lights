package com.pszt.TrafficLights.Controller;

import com.pszt.TrafficLights.model.Car;
import com.pszt.TrafficLights.model.Crossroad;
import com.pszt.TrafficLights.model.Model;
import com.pszt.TrafficLights.model.SpawnPoint;
import com.pszt.TrafficLights.view.Widok;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements  Runnable{
    private Model model;
    private Widok view;

    private Simulation simulation;
    private Thread thread;
    final private int DELAY_FRAME = 100;

    public Controller(Widok w) {
        this.view = w;

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

            simulation.update((sleep > 0 ? DELAY_FRAME : timeDiff ));
            ArrayList<Car> carsTmp = new ArrayList<Car>();
            ArrayList<Crossroad> crossroadsTmp = new ArrayList<Crossroad>();

              for(Car tmp : model.getCars()){
                  try {
                      carsTmp.add((Car)tmp.clone());
                  } catch (CloneNotSupportedException e) {
                      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                  }
              }
            for(Crossroad tmp : model.getCrossroads()){

                try {
                    crossroadsTmp.add((Crossroad)tmp.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }


            view.maziaj(carsTmp, crossroadsTmp);

            beforeTime = System.currentTimeMillis();
        }
    }

    /**
     * startuje symulacje
     */
    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void setModel(Model model) {
        this.model = model;
        view.setHorizonalLines(model.getHorizontalLines());
        view.setVerticalLines(model.getVerticalLines());
        this.simulation = new Simulation(model);
    }


}
