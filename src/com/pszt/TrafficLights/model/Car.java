package com.pszt.TrafficLights.model;


/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class Car {

    private long id;
    private int x;
    private int y;
    private int destX;
    private int destY;
    private int width;
    private int height;
    private boolean onStreet;


    /**
     * Konstruktor samochodu z przypisaniem szerokosci i wysokosci
     *
     * @param id  id samochodu
     * @param x   wsporzedna x samochodu
     * @param y   wspolrzedna y samochodu
     * @param destX  wspolrzedna x celu do ktorego dazy samochod
     * @param destY   wspolrzedna y celu do ktorego dazy samochod
     * @param width   szerokosc samochodu
     * @param height  wysokosc samochodu
     */
    public Car(int id, int x, int y, int destX, int destY, int width, int height){
        this.id = id;
        this.x = x;
        this.y = y;
        this.destX = destX;
        this.destY = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Konstruktor samochodu
     *
     * @param id  id samochodu
     * @param x   wsporzedna x samochodu
     * @param y   wspolrzedna y samochodu
     * @param destX  wspolrzedna x celu do ktorego dazy samochod
     * @param destY   wspolrzedna y celu do ktorego dazy samochod
     */
    public Car(int id, int x, int y, int destX, int destY){
        this.id = id;
        this.x = x;
        this.y = y;
        this.destX = destX;
        this.destY = y;
    }

    public void carPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Ustawienie zmiennej mowiacej o tym czy samochod jest na drodze czy nie
     *
     * @param onStreet  parametr true/false
     */
    public void setOnStreet(boolean onStreet){
        this.onStreet = onStreet;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public long getId(){
        return id;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDestX(){
        return destX;
    }

    public int getDestY(){
        return destY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean getOnStreet(){
        return this.onStreet;
    }











}
