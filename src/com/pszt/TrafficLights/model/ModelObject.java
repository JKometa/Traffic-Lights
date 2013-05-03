package com.pszt.TrafficLights.model;

import java.awt.Rectangle;
import java.awt.Point;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
abstract class ModelObject implements Cloneable {
    protected int x;
    protected int y;

    protected ModelObject(int x, int y) {
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

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }


   public Point getPosition(){
       return new Point(x, y);
   }

    int getPositionX() {
        return x;
    }

    void setPositionX(int x) {
        this.x = x;
    }

    int getPositionY() {
        return y;
    }

    void setPositionY(int y) {
        this.y = y;
    }

    abstract public Rectangle getBounds();
}
