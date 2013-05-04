package com.pszt.TrafficLights.model;


import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */


public class Crossroad extends ModelLimitObject implements Cloneable {
    static final private long intervalYellow = 3000;

    private TrafficLight trafficLightVertical;
    private TrafficLight trafficLightHorizontal;

    private boolean ascendingHorizontal;
    private boolean ascendingVertical;

    private long intervalRedGreen;

    /**
     * stan swiatel, true jesli swiatla sa zielone lub czerwone
     * false jesli zolte
     */
    private boolean stateRedGreen;

    public Crossroad(int x, int y, boolean ascendingHorizontal, boolean ascendingVertical) {
        super(x, y);
        this.ascendingHorizontal = ascendingHorizontal;
        this.ascendingVertical = ascendingVertical;
        this.intervalRedGreen = 3000;
        this.trafficLightHorizontal = new TrafficLight(TrafficLight.LightColor.GREEN);
        this.trafficLightVertical = new TrafficLight(TrafficLight.LightColor.RED);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            Crossroad clone = (Crossroad)super.clone();
            clone.trafficLightHorizontal = (TrafficLight)trafficLightHorizontal.clone();
            clone.trafficLightVertical = (TrafficLight)trafficLightVertical.clone();
            clone.stateRedGreen = this.stateRedGreen;
             return clone;

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * zmienia stan swiatel na nastepna sekwencje
     * @return czas za jaki powinna byc nastepna zmiana
     */
    public long nextLight(){
        trafficLightHorizontal.nextColor();
        trafficLightVertical.nextColor();
        stateRedGreen = !stateRedGreen;
        return (stateRedGreen ? intervalRedGreen : intervalYellow);

    }

    public long getIntervalRedGreen() {
        return intervalRedGreen;
    }

    public void setIntervalRedGreen(long intervalRedGreen) {
        this.intervalRedGreen = intervalRedGreen;
    }

    public boolean isAscendingVertical() {
        return ascendingVertical;
    }

    public boolean isAscendingHorizontal() {
        return ascendingHorizontal;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - Model.ROAD_WIDTH / 2, y - Model.ROAD_WIDTH / 2, Model.ROAD_WIDTH, Model.ROAD_WIDTH);
    }
}
