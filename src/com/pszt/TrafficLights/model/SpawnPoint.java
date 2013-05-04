package com.pszt.TrafficLights.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 *
 * Obiekt ktory bedzie spawnowal nowe samochodziki na mapie,
 * jednoczesnie jest poczatkiem drogi
 */

public class SpawnPoint extends ModelLimitObject {


    /**
     * spawnowany samochod bedzie kopia prototypu
     */
    private Car prototype;

    public SpawnPoint(float x, float y) {
        super(x, y);
        boolean horizontal;
        if ( x > 0 && x < Model.BOARD_WIDTH){
            horizontal = false;
            if ( y == 0){
                prototype = new Car(x, y - Car.WIDTH_HORIZONTAL / 2, horizontal, true);
            }
            else{
                prototype = new Car(x, y + Car.WIDTH_HORIZONTAL / 2, horizontal, false);
            }
        } else{
            horizontal = true;
            if ( x == 0){
                prototype = new Car(x - Car.WIDTH_HORIZONTAL / 2, y, horizontal, true);
            } else {
                prototype = new Car(x + Car.WIDTH_HORIZONTAL / 2, y, horizontal, false);
            }
        }

        prototype.setSpeed(this.getSpeedLimit());
    }

    /**
     * spawnuje nowy samochodzik
     * @return nowo stworzony samochodzik na bazie prototypu
     */
    public Car spawn(){
        Car spawn = null;
        try {
            spawn = (Car)prototype.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return spawn;
    }

    /**
     * sprawdza czy samochody poruszajace sie po drodze jada w kierunku rosnacych wspolrzednych
     * @return true jesli samochody jada w kierunku rosnacych wspolrzednych
     */
    public boolean isAscending(){
        return prototype.isAscending();
    }

    @Override
    public Rectangle getBounds() {
        return prototype.getBounds();
    }


}
