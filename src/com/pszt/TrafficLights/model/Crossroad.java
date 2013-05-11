package com.pszt.TrafficLights.model;


import java.awt.*;

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
     * czas w jakim poziome światła sa zielone
     */
    private long intervalHorizontalGreen;

    /**
     * czas w jakim poziome światła sa czerwone
     */
    private long intervalHorizontalRed;


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
        this.intervalHorizontalGreen = 2000;
        this.intervalHorizontalRed = 6000;
        this.stateRedGreen = true;
        this.trafficLightHorizontal = new TrafficLight(TrafficLight.LightColor.GREEN);
        this.trafficLightVertical = new TrafficLight(TrafficLight.LightColor.RED);
        this.timeToChangeLight = this.intervalHorizontalGreen;
//        System.out.println("Nastepna swiatla za: " + timeToChangeLight);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            Crossroad clone = (Crossroad)super.clone();
            clone.trafficLightHorizontal = (TrafficLight)trafficLightHorizontal.clone();
            clone.trafficLightVertical = (TrafficLight)trafficLightVertical.clone();
            clone.stateRedGreen = this.stateRedGreen;
            clone.ascendingVertical = this.ascendingVertical;
            clone.ascendingHorizontal = this.ascendingHorizontal;
            clone.timeToChangeLight = this.timeToChangeLight;
            clone.intervalHorizontalGreen = this.intervalHorizontalGreen;
            clone.intervalHorizontalRed = this.intervalHorizontalRed;
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

     //   long nextTime;

        switch (trafficLightHorizontal.getColor()) {
            case RED_YELLOW:
            case YELLOW:
                return intervalYellow;
            case RED:
                return intervalHorizontalRed;
            case GREEN:
                return intervalHorizontalGreen;
        }

//        System.out.println("Nastepna swiatla za: " + intervalHorizontalGreen);
//        return (stateRedGreen ? intervalHorizontalGreen : intervalYellow);
        return -1;

    }

    public long getIntervalHorizontalGreen() {
        return intervalHorizontalGreen;
    }

    public void setIntervalHorizontalGreen(long intervalHorizontalGreen) {
        this.intervalHorizontalGreen = intervalHorizontalGreen;
    }

    public void setIntervalHorizontalRed(long intervalHorizontalRed) {
        this.intervalHorizontalRed = intervalHorizontalRed;
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
    }

    public void updateLight(long deltaTime){
        timeToChangeLight -= deltaTime;
        if(timeToChangeLight <= 0){
            timeToChangeLight += nextLight();
//            System.out.println("Nastepna swiatla za: " + timeToChangeLight);
        }
    }


}


