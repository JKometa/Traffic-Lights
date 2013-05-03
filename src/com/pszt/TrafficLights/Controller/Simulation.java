package com.pszt.TrafficLights.Controller;

import com.pszt.TrafficLights.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
public class Simulation {
    private Model model;

    /**
     * z jaka szybkoscia ma sie odbywac symulacja
     */
    private int scaleTime;
    private boolean spawnCars;

    public Simulation(Model model) {
        this.model = model;
        scaleTime = 1;
        spawnCars = true;
    }

    public void setSpawnCars(boolean spawnCars) {
        this.spawnCars = spawnCars;
    }

    public void setScaleTime(int scaleTime) {
        this.scaleTime = scaleTime;
    }

    /**
     * tu sie bedzie dziala cala logika
     * @param deltaTime
     */
    public void update(long deltaTime){

        deltaTime *= scaleTime;

    }
}
