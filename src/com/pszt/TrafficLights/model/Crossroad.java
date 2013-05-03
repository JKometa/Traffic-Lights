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
    static private long intervalYellow = 3;

    private TrafficLight trafficLightVertical;
    private TrafficLight trafficLightHorizontal;

    private long intervalRedGreen;

    /**
     * stan swiatel, true jesli swiatla sa zielone lub czerwone
     * false jesli zolte
     */
    private boolean stateRedGreen;

    public Crossroad(int x, int y) {
        super(x, y);
        intervalRedGreen = 3;
        trafficLightHorizontal = new TrafficLight(TrafficLight.LightColor.GREEN);
        trafficLightVertical = new TrafficLight(TrafficLight.LightColor.RED);
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

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - Model.ROAD_WIDTH / 2, y - Model.ROAD_WIDTH / 2, Model.ROAD_WIDTH, Model.ROAD_WIDTH);
    }
}
