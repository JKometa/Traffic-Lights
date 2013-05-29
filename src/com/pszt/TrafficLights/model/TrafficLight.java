package com.pszt.TrafficLights.model;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 12:27
 * To change this template use File | Settings | File Templates.
 */
public class TrafficLight implements Cloneable {


    public static enum LightColor {
        INVALID(-1), RED(0), RED_YELLOW(1), GREEN(2), YELLOW(3);

        private int lightColor;

        private LightColor(int lightColor) {
            this.lightColor = lightColor;
        }

        public int getColor() {
            return lightColor;
        }
    }

    private LightColor color;

    public TrafficLight(LightColor color) {
        this.color = color;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            TrafficLight clone = (TrafficLight) super.clone();
            clone.color = color;

            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public LightColor getColor() {
        return color;
    }

    public void setColor(LightColor color) {
        this.color = color;
    }

    public boolean isGreen() {
        return (color == LightColor.GREEN ? true : false);
    }

    public void nextColor() {
        switch (color) {
            case RED:
                color = LightColor.RED_YELLOW;
                break;

            case RED_YELLOW:
                color = LightColor.GREEN;
                break;

            case GREEN:
                color = LightColor.YELLOW;
                break;

            case YELLOW:
                color = LightColor.RED;
                break;
        }
    }




    /*
    public static enum Possition {
        INVALID(-1), LEFT(0), RIGHT(1), UP(2), DOWN(3);

        private int possition;

        private Possition(int possition) {
            this.possition = possition;
        }

        public int getColor() {
            return possition;
        }
    }

    private Possition possition;

    */
    /*


    /*
    private boolean turnedOn;
    private long interval;
    private long intervalRed;
    private long intervalYellow;
    private long intervalGreen;
    */
    /**
     * Konstruktor swiatla dorogwego z ustaleniem poyzycji i koloru
     *
     * @param id   id swiatla drogowaego
     * @param x    wspolrzedna x swiatla drogowego
     * @param y    wspolrzedna y swiatla drogowego
     * @param width  szerokosc swiatla
     * @param height wysokosc siwatla
     * @param possition pozycja na skrzyzowaniu po ktorej stoi swiatlo
     * @param color      kolor swiatla
     * @param interval   czas pomiedzy zmiana swiatel
     *
    public TrafficLight(int id, int x, int y, int width, int height,Possition possition, LightColor color, long interval){

    this.id = id;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.possition = possition;
    this.color = color;
    this.turnedOn = false;
    this.interval = interval;

    }

    /**
     * Konstruktor swiatla dorogowego
     *
     * @param id   id swiatla drogowaego
     * @param x    wspolrzedna x swiatla drogowego
     * @param y    wspolrzedna y swiatla drogowego
     * @param width  szerokosc swiatla
     * @param height wysokosc siwatla
     * @param interval   czas pomiedzy zmiana swiatel
     *
    public TrafficLight(int id, int x, int y, int width, int height, long interval){

    this.id = id;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.turnedOn = false;
    this.interval = interval;

    }

    public void setColor(LightColor color){
    this.color = color;
    }

    public void setPossition(Possition possition){
    this.possition = possition;
    }

    public void setWidth(int width){
    this.width = width;
    }

    public void setHeight(int height){
    this.height = height;
    }

    public void setTurnedOn(boolean turnedOn){
    this.turnedOn = turnedOn;
    }

    public void setInterval(long interval){
    this.interval = interval;
    }

    public void setIntervalRed(long interval){
    intervalRed = interval;
    }

    public void setIntervalYellow(long interval){
    intervalYellow = interval;
    }

    public void setIntervalGreen(long interval){
    intervalGreen = interval;
    }

    public void setX(int x){
    this.x = x;
    }

    public void setY(int y){
    this.y = y;
    }


    public long getInterval(){
    return interval;
    }

    public long getIntervalRed(){
    return intervalRed;
    }

    public long getIntervalYellow(){
    return intervalYellow;
    }

    public long  getIntervalGreen(){
    return  intervalGreen;
    }

    public int getId(){
    return id;
    }

    public int getX(){
    return x;
    }

    public int getY(){
    return y;
    }

    public int getWidth(){
    return width;
    }

    public int getHeight(){
    return height;
    }

    public LightColor getColor(){
    return this.color;
    }

    public Possition getPossition(){
    return this.possition;
    }

    public boolean getTurnedOn(){
    return turnedOn;
    }


     */

}
