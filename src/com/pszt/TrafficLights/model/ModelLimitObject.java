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
    private double speedLimit;

    /**
     * Numer prędkości w przypadku posortowania rosnaco mozliwych predkosci
     */
    private int idSpeed;

    /**
     * Konstruktor przyjmujacy wspolrzedne na ktorej bedzie sie znajdowac obiekt,
     * predkosc jest generowana losowo
     * @param x wspolrzedne x
     * @param y wspolrzedne y
     */
    protected ModelLimitObject(double x, double y) {
        super(x, y);
        Random rand = new Random();
        int randInt = rand.nextInt(5);
        this.speedLimit = 0.02f + randInt * 0.005f;
        idSpeed = randInt;
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


    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getIdSpeed() {
        return idSpeed;
    }
}
