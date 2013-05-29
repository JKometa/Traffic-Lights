package com.pszt.TrafficLights.model;

import java.awt.Rectangle;
import java.awt.Point;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 16:08
 * <p/>
 * Abstrakcyjna klasa przedstawiajaca wszystko co ma swoje polozenie na mapie
 */
abstract class ModelObject implements Cloneable {
    protected double x;
    protected double y;

    protected ModelObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ModelObject clone = (ModelObject) super.clone();
            clone.x = this.x;
            clone.y = this.y;
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setPosition(Point position) {
        this.x = position.x;
        this.y = position.y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public Point getPosition() {
        return new Point((int) x, (int) y);
    }

    public double getPositionX() {
        return x;
    }

    public void setPositionX(double x) {
        this.x = x;
    }

    public double getPositionY() {
        return y;
    }

    public void setPositionY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "ModelObject{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * getBounds
     *
     * @return kwadrat jaki zajmuje dany obiekt na mapie
     */
    abstract public Rectangle getBounds();

    /**
     * HitBox - kwadrat używany do detekcji kolizji
     *
     * @return zwraca kwadrat robiący za hitboxa, jeśli dany samochodzik ma wielkość
     *         szerokość x wysokość, to hit box danego samochodzika ma
     *         szerokość + margines x wysokość + margines,
     *         aby samochody nie jechały zderzak w zderzak
     */
    abstract public Rectangle getHitBox();
}
