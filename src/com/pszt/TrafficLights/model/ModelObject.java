package com.pszt.TrafficLights.model;

import java.awt.Rectangle;
import java.awt.Point;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 16:08
 *
 *  Abstrakcyjna klasa przedstawiajaca wszystko co ma swoje polozenie na mapie
 */
abstract class ModelObject implements Cloneable {
    protected float x;
    protected float y;

    protected ModelObject(float x, float y) {
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

    public void setPosition(Point position){
        this.x = position.x;
        this.y = position.y;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }


   public Point getPosition(){
       return new Point((int)x, (int)y);
   }

    public float getPositionX() {
        return x;
    }

    public void setPositionX(float x) {
        this.x = x;
    }

    public float getPositionY() {
        return y;
    }

    public void setPositionY(float y) {
        this.y = y;
    }

    /**
     *  getBounds
     * @return kwadrat jaki zajmuje dany obiekt na mapie
     */
    abstract public Rectangle getBounds();
}
