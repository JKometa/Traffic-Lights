package com.pszt.TrafficLights.model;


import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class Car extends ModelLimitObject implements Cloneable {
    static final int WIDTH_HORIZONTAL = 30;
    static final int HEIGHT_HORIZONTAL = 10;
    static final private int MARGIN = 10;

    /**
     * Okresla czy pojazd porusza sie pionowo czy poziomo
     */
    private boolean horizontal;

    /**
     * Okresla czy pojazd porusza sie po osi w kierunku rosnacych zmiennych
     */
    private boolean ascending;

    /**
     * predkosc z jaka porusza sie samochod
     */
    private int speed;

    /**
     * okresla czy samochod znajduje sie na skrzyzowaniu
     */
    private boolean onCrossroad;



    public Car(int x, int y, boolean horizontal, boolean ascending){
        super(x,y);
        this.horizontal = horizontal;
        this.ascending = ascending;
        this.onCrossroad = false;
    }



    @Override
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle();
        bounds.setSize((horizontal ? WIDTH_HORIZONTAL : HEIGHT_HORIZONTAL),
                        (horizontal ? HEIGHT_HORIZONTAL : WIDTH_HORIZONTAL));
        bounds.setLocation(x - bounds.width / 2 , y - bounds.height / 2);
        return bounds;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Car clone = (Car)super.clone();
            clone.ascending = this.ascending;
            clone.horizontal = this.ascending;
            clone.speed = this.speed;
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean isAscending() {
        return ascending;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOnCrossroad() {
        return onCrossroad;
    }

    public void setOnCrossroad(boolean onCrossroad) {
        this.onCrossroad = onCrossroad;
    }

    public void setPositionBefore(Car car){
        int delta = WIDTH_HORIZONTAL + MARGIN;
        if (horizontal){
            setPositionX(car.getPositionX() + (ascending ? (- delta) : delta));
        } else {
            setPositionY(car.getPositionY() + (ascending ? (- delta) : delta));
        }

    }

    public void setPositionBefore(Crossroad crossroad){
        int delta = Model.ROAD_WIDTH / 2 + WIDTH_HORIZONTAL /2 + MARGIN;
        if (horizontal){
            setPositionX(crossroad.getPositionX() + (ascending ? (- delta) : delta));
        } else {
            setPositionY(crossroad.getPositionY() + (ascending ? (- delta) : delta));
        }
    }
}
