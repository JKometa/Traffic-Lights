package com.pszt.TrafficLights.model;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 16:36
 *
 * Abstrakcyjna klasa wyznaczajaca ograniczenie predkosci na poczatku drogi ktorej sie znajduje
 */
abstract class ModelLimitObject extends ModelObject implements Cloneable {
    /**
     * Ograniczenie predkosci na odcinku ktory zaczyna sie ModelLimitObject
     */
    private int speedLimit;

    /**
     * Konstruktor przyjmujacy wspolrzedne na ktorej bedzie sie znajdowac obiekt,
     * predkosc jest generowana losowo
     * @param x wspolrzedne x
     * @param y wspolrzedne y
     */
    protected ModelLimitObject(float x, float y) {
        super(x, y);
        Random rand = new Random();
        int randInt = rand.nextInt(5);
        this.speedLimit = 40 + randInt * 10;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ModelLimitObject clone = (ModelLimitObject)super.clone();
            clone.speedLimit = speedLimit;
            return clone;

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


    int getSpeedLimit() {
        return speedLimit;
    }

    void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }
}
