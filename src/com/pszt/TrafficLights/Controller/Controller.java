package com.pszt.TrafficLights.Controller;

import com.pszt.TrafficLights.model.Car;
import com.pszt.TrafficLights.model.Crossroad;
import com.pszt.TrafficLights.model.Model;
import com.pszt.TrafficLights.model.SpawnPoint;
import com.pszt.TrafficLights.view.Widok;

import java.awt.*;
import java.util.ArrayList;
import com.pszt.TrafficLights.Ewolucja.Populacja;
import com.pszt.TrafficLights.view.WidokMain;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements  Runnable{
    private Model model;
    private Model copyModel;
   // private Widok view;
    private WidokMain widokMain;

    private Simulation simulation;
    private Thread thread;
    final private int DELAY_FRAME = 300;

    private Populacja populacja;

    /**
     * ostatnio odczytane pokolenie z populacji
     */
    private int pokolenie;

    public Controller(WidokMain vidokMain) {

        this.widokMain = vidokMain;


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
            try {
                copyModel = (Model)model.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            if(pokolenie < populacja.getIloscPokolen()){
                model.setIntervalsOnCrossroads(populacja.getNajlepszeOkresy());
                pokolenie = populacja.getIloscPokolen();
//                System.out.println("ustawiam swiatla wg nowego pokolenia! " + pokolenie);
            }


            ArrayList<Car> carsTmp = new ArrayList<Car>();
            ArrayList<Crossroad> crossroadsTmp = new ArrayList<Crossroad>();
            ArrayList<SpawnPoint> spawnTmp = new ArrayList<SpawnPoint>();

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
//            for(SpawnPoint tmp : model.getSpawnPoints()){
//
//                try {
//                    spawnTmp.add((SpawnPoint)tmp.clone());
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//            }


            widokMain.getWidok().maziaj(carsTmp, crossroadsTmp);

            beforeTime = System.currentTimeMillis();
        }
    }

    /**
     * startuje symulacje
     */
    public void start(){
        thread = new Thread(this);
        thread.start();
        populacja.start();

    }

    /** Wyswietla okno symulacji i inicjuje ja
     *
     */
    public void display(int h, int v){
        generateModel(h,v);
        widokMain.showSimulation();
        this.start();
    }

    public void stopSimulation(){
           thread.stop();

    }


    public Model getCopyModel() {
        return copyModel;
    }

    public void setModel(Model model) {
        this.model = model;
        widokMain.setHorizonalLines(model.getHorizontalLines());
        widokMain.setVerticalLines(model.getVerticalLines());

        try {
            copyModel = (Model)model.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        this.populacja = new Populacja(this);
        this.simulation = new Simulation(model);
    }
    public void generateModel(int h, int v){
         Model m = new Model(h,v);
        this.setModel(m);

    }

}
