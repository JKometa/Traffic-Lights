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
    /**
     * szerokość samochodu w pozycji poziomej,
     *  w pozycji pionowej jest to jego wysokość
     */
    static final int WIDTH_HORIZONTAL = 30;

    /**
     * wysokość samochodu w pozycji poziomej,
     * w pozycji pionowej jest to jego szerokość
     */
    static final int HEIGHT_HORIZONTAL = 10;

    /**
     * minimalny odstęp jakie muszą zachować samochody miedzy sobą,
     *  a także samochód od "zamkniętego" skrzyżowania
     */
    static final int MARGIN = 10;

    /**
     * Okresla czy pojazd porusza sie pionowo czy poziomo
     */
    private boolean horizontal;

    /**
     * Określa czy pojazd porusza sie po osi w kierunku rosnących zmiennych
     */
    private boolean ascending;

    /**
     * prędkość z jaka porusza sie samochód
     */
    private double speed;

    /**
     * skrzyżowanie na którym sie znajduje samochód,
     *  null oznacza, że samochód nie jest na skrzyżowaniu
     */
    private Crossroad crossroad;



    public Car(double x, double y, boolean horizontal, boolean ascending){
        super(x,y);
        this.horizontal = horizontal;
//        System.out.println(horizontal);
//        System.out.println(this.horizontal);
        this.ascending = ascending;
        this.crossroad = null;
    }



    @Override
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle();
        bounds.setSize((horizontal ? WIDTH_HORIZONTAL : HEIGHT_HORIZONTAL),
                        (horizontal ? HEIGHT_HORIZONTAL : WIDTH_HORIZONTAL));
        bounds.setLocation((int)(x - bounds.width / 2 ),
                (int)(y - bounds.height / 2));
        return bounds;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Rectangle getHitBox() {
        Rectangle hitBox = getBounds();
        if (horizontal){
            hitBox.x -= MARGIN;
            hitBox.width += 2 * MARGIN;
        } else{
            hitBox.y -= MARGIN;
            hitBox.height += 2 * MARGIN;
        }
        return hitBox;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            Car clone = (Car)super.clone();
            clone.ascending = this.ascending;
            clone.horizontal = this.horizontal;
            clone.speed = this.speed;
            clone.crossroad = this.crossroad;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * sprawdza czy samochód jest na skrzyżowaniu
     * @return true jeśli jest
     */
    public boolean isOnCrossroad() {
        return (crossroad != null);
    }

    public Crossroad getCrossroad() {
        return crossroad;
    }

    public void setCrossroad(Crossroad crossroad) {
        this.crossroad = crossroad;
    }


    public void move(double deltaS){
        if(!ascending)
            deltaS = - deltaS;

        if(horizontal)
            x += deltaS;
        else
            y += deltaS;


    }



    /**
     * ustawia samochód za innym samochodem zachowując wymagany odstęp
     * @param car samochód który znajduje sie przed naszym obiektem
     */
    public void setPositionBefore(Car car){
        int delta = WIDTH_HORIZONTAL + MARGIN;
        if (horizontal){
            setPositionX(car.getPositionX() + (ascending ? (- delta) : delta));
        } else {
            setPositionY(car.getPositionY() + (ascending ? (- delta) : delta));
        }

    }

    /**
     * ustawia samochód przed skrzyżowaniem zachowując wymagany odstęp
     * @param crossroad skrzyżowanie które blokuje wjazd samochodowi
     */
    public void setPositionBefore(Crossroad crossroad){
        int delta = Model.ROAD_WIDTH / 2 + WIDTH_HORIZONTAL /2 + MARGIN;
        if (horizontal){
            setPositionX(crossroad.getPositionX() + (ascending ? (- delta) : delta));
        } else {
            setPositionY(crossroad.getPositionY() + (ascending ? (- delta) : delta));
        }
    }
}
