package com.pszt.TrafficLights.model;


import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 12:55
 *
 * Klasa przedstawiająca skrzyżowanie, zawiera dwa swiatła (dla pionu i poziomu),
 * dziedziczy po ModelLimitObject, żeby skrzyzowanie wyznaczalo od razu maksymalna predkosc
 * dla odcinka drogi "za" skrzyzowaniem
 */


public class Crossroad extends ModelLimitObject implements Cloneable {
    /**
     * czas w jakim swiatla sa wstanie zolte/czerwono-zolte
     */
    static final private long intervalYellow = 3000;

    /**
     * swiatla dla ruchu pionowego
     */
    private TrafficLight trafficLightVertical;

    /**
     * swiatla dla ruchu poziomego
     */
    private TrafficLight trafficLightHorizontal;

    /**
     * okresla czy ruch w osi poziomej jest w kierunku rosnacych wspolrzednych
     * true ---->
     * false <---
     */
    private boolean ascendingHorizontal;

    /**
     * określa czy ruch w osi pionowej jest w kierunku rosnących współrzędnych
     * true |   false ^
     *      |         |
     *      V         |
     *
     */
    private boolean ascendingVertical;

    /**
     * czas w jakim skrzyżowania sa w stanie zielonym/czerwonym
     */
    private long intervalRedGreen;


    /**
     * czas za jaki maja zmienic sie swiatla
     */
    private long timeToChangeLight;

    /**
     * stan swiatel, true jesli swiatla sa zielone lub czerwone
     * false jesli zolte
     *
     * jesli jedne skrzyzowanie jest w stanie czerwonym to drugie musi byc w stanie zielonym
     * jesli jedna skrzyzowanie jest w stanie zoltym to drugi musi byc w stanie zolto-czerwonym
     */
    private boolean stateRedGreen;

    public Crossroad(double x, double y, boolean ascendingHorizontal, boolean ascendingVertical) {
        super(x, y);
        this.ascendingHorizontal = ascendingHorizontal;
        this.ascendingVertical = ascendingVertical;
        this.intervalRedGreen = 3000;
        this.trafficLightHorizontal = new TrafficLight(TrafficLight.LightColor.GREEN);
        this.trafficLightVertical = new TrafficLight(TrafficLight.LightColor.RED);
        this.timeToChangeLight = this.intervalRedGreen;
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
     * zmienia stan świateł na następny z sekwencji
     * @return czas za jaki powinna byc następna zmiana
     */
    private long nextLight(){
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

    public TrafficLight getTrafficLightHorizontal() {
        return trafficLightHorizontal;
    }

    public TrafficLight getTrafficLightVertical() {
        return trafficLightVertical;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x - Model.ROAD_WIDTH / 2),(int)( y - Model.ROAD_WIDTH / 2),
                Model.ROAD_WIDTH, Model.ROAD_WIDTH);
    }

    @Override
    public Rectangle getHitBox() {
        return getBounds();
//        return new Rectangle((int)(x - Model.ROAD_WIDTH / 2 - Car.MARGIN),
//                (int)( y - Model.ROAD_WIDTH / 2 - Car.MARGIN),
//                Model.ROAD_WIDTH + 2 * Car.MARGIN,
//                Model.ROAD_WIDTH + 2 * Car.MARGIN);
    }

    public void updateLight(long deltaTime){
        timeToChangeLight -= deltaTime;
        if(timeToChangeLight <= 0){
            timeToChangeLight += nextLight();
        }
    }


}


