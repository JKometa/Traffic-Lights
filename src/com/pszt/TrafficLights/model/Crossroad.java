package com.pszt.TrafficLights.model;


import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Modzel
 * Date: 01.05.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */


public class Crossroad {

    private TrafficLight trafficLightLeft;
    private TrafficLight trafficLightRight;
    private TrafficLight trafficLightUp;
    private TrafficLight trafficLightDown;
    private ArrayList<Car> carsWaitingLeft;
    private ArrayList<Car> carsWaitingRight;
    private ArrayList<Car> carsWaitingUp;
    private ArrayList<Car> carsWaitingDown;

    private long carsPassed;
    private int id;
    private int x;
    private int y;
    private int rightCapacity;
    private int leftCapacity;
    private int upCapacity;
    private int downCapacity;

    /**
     * Konstruktor ktory tworzy skrzyzowanie i przypisuje mu swiatla.
     *
     * @param id id skrzyzowania
     * @param x  wpolrzedna x skrzyzowania
     * @param y  wspolrzedna y skrzyzowania
     * @param rightCapacity  pojemnosc skrzyzowania z prawej strony
     * @param leftCapacity   pojemnosc skrzyzowania z lewej strony
     * @param upCapacity     pojemnosc skrzyzowania z gory
     * @param downCapacity   pojemnosc skrzyzowania z dolu
     * @param lightsIdStart  id od ktorego bedzie zaczynane nadawanie id swiatlom
     * @param lightsRadius   promien wokol ktorego stworza sie swiatla
     * @param interval       czas pomiedzy zmiana swiatel
     */
    public Crossroad(int id, int x, int y, int rightCapacity, int leftCapacity, int upCapacity, int downCapacity, int lightsIdStart, int lightsRadius, long interval){
        this.id = id;
        this.x = x;
        this.y = y;
        this.leftCapacity = leftCapacity;
        this.rightCapacity = rightCapacity;
        this.upCapacity = upCapacity;
        this.downCapacity = downCapacity;
        trafficLightLeft = new TrafficLight(lightsIdStart, x - lightsRadius, y, 50, 100, TrafficLight.Possition.LEFT, TrafficLight.LightColor.RED, interval);
        trafficLightRight = new TrafficLight(lightsIdStart + 1, x + lightsRadius, y, 50, 100, TrafficLight.Possition.RIGHT, TrafficLight.LightColor.RED,  interval);
        trafficLightUp = new TrafficLight(lightsIdStart + 2, x, y + lightsRadius, 50, 100, TrafficLight.Possition.UP, TrafficLight.LightColor.RED,  interval);
        trafficLightDown = new TrafficLight(lightsIdStart + 3, x, y - lightsRadius, 50, 100, TrafficLight.Possition.DOWN, TrafficLight.LightColor.RED,  interval);
    }

    /**
     *   Konstruktor ktory tworzy skrzyzowanie i przypisuje mu swiatla
     *
     * @param id id skrzyzowania
     * @param x  wpolrzedna x skrzyzowania
     * @param y  wspolrzedna y skrzyzowania
     * @param generalCapacity  parametr okreslajacy poejmnosc skrzyzowan po wszystich stronach
     * @param lightsIdStart  id od ktorego bedzie zaczynane nadawanie id swiatlom
     * @param lightsRadius   promien wokol ktorego stworza sie swiatla
     * @param interval       czas pomiedzy zmiana swiatel
     */
    public Crossroad(int id, int x, int y, int generalCapacity,int lightsIdStart, int lightsRadius, long interval){
        this.id = id;
        this.x = x;
        this.y = y;
        this.leftCapacity = generalCapacity;
        this.rightCapacity = generalCapacity;
        this.upCapacity = generalCapacity;
        this.downCapacity = generalCapacity;
        trafficLightLeft = new TrafficLight(lightsIdStart, x - lightsRadius, y, 50, 100, TrafficLight.Possition.LEFT, TrafficLight.LightColor.RED,  interval);
        trafficLightRight = new TrafficLight(lightsIdStart + 1, x + lightsRadius, y, 50, 100, TrafficLight.Possition.RIGHT, TrafficLight.LightColor.RED,  interval);
        trafficLightUp = new TrafficLight(lightsIdStart + 2, x, y + lightsRadius, 50, 100, TrafficLight.Possition.UP, TrafficLight.LightColor.RED,  interval);
        trafficLightDown = new TrafficLight(lightsIdStart + 3, x, y - lightsRadius, 50, 100, TrafficLight.Possition.DOWN, TrafficLight.LightColor.RED,  interval);
    }

    /**
     * Konstruktor ktory tworzy skrzyzowanie
     *
     * @param id id skrzyzowania
     * @param x  wpolrzedna x skrzyzowania
     * @param y  wspolrzedna y skrzyzowania
     * @param rightCapacity  pojemnosc skrzyzowania z prawej strony
     * @param leftCapacity   pojemnosc skrzyzowania z lewej strony
     * @param upCapacity     pojemnosc skrzyzowania z gory
     * @param downCapacity   pojemnosc skrzyzowania z dolu
     */
    public Crossroad(int id, int x, int y, int rightCapacity, int leftCapacity, int upCapacity, int downCapacity){
        this.id = id;
        this.x = x;
        this.y = y;
        this.leftCapacity = leftCapacity;
        this.rightCapacity = rightCapacity;
        this.upCapacity = upCapacity;
        this.downCapacity = downCapacity;

    }

    /**
     * Konstruktor ktory tworzy skrzyzowanie
     *
     * @param id id skrzyzowania
     * @param x  wpolrzedna x skrzyzowania
     * @param y  wspolrzedna y skrzyzowania
     * @param generalCapacity  parametr okreslajacy poejmnosc skrzyzowan po wszystich stronach
     */
    public Crossroad(int id, int x, int y, int generalCapacity){
        this.id = id;
        this.x = x;
        this.y = y;
        this.leftCapacity = generalCapacity;
        this.rightCapacity = generalCapacity;
        this.upCapacity = generalCapacity;
        this.downCapacity = generalCapacity;
    }


    /**
     * Przypisanie swiatla ulicznego do skrzyzowania od strony podanej jako parametr
     *
     * @param trafficLight  swiatlo uliczne ktore bedzie przypisane do skrzyzowania
     * @param possition     okreslenie z ktorej strony skrzyzowania bedzie swiatlo
     */
    public void attachTrafficLight(TrafficLight trafficLight, TrafficLight.Possition possition){
        switch (possition){
            case INVALID:
                break;
            case LEFT:
                trafficLightLeft = trafficLight;
                break;
            case RIGHT:
                trafficLightRight = trafficLight;
                break;
            case UP:
                trafficLightUp = trafficLight;
                break;
            case DOWN:
                trafficLightDown = trafficLight;
                break;
        }
    }

    /**
     * Dodanie samochodu do listy oczekujacych na przejazd
     *
     * @param car    samochod ktory ma byc dodany
     * @param possition   okreslenie z ktorej strony samochod bedzie czekal na przejazd
     */
    public void addCar(Car car, TrafficLight.Possition possition){
        switch (possition){
            case INVALID:
                break;
            case LEFT:
                carsWaitingLeft.add(car);
                break;
            case RIGHT:
                carsWaitingRight.add(car);
                break;
            case UP:
                carsWaitingUp.add(car);
                break;
            case DOWN:
                carsWaitingDown.add(car);
                break;
        }

    }

    /**
     * Usuniecie danego samochodu z listy oczekujacych na przejazd po danej stronie
     *
     * @param car  samochod ktory bedzie usuniety
     * @param possition   strona z ktorej usuwamy samochod
     */
    public void deleteCar(Car car, TrafficLight.Possition possition){

        switch (possition){
            case INVALID:
                break;
            case LEFT:
                carsWaitingLeft.remove(car);
                break;
            case RIGHT:
                carsWaitingRight.remove(car);
                break;
            case UP:
                carsWaitingUp.remove(car);
                break;
            case DOWN:
                carsWaitingDown.remove(car);
                break;
        }

    }

    /**
     *
     * Usuwa samochod po zadanym id i stronie na ktorej czeka
     *
     * @param id   id samochodu
     * @param possition   strona z ktorej usuwamy samochod
     */
    public void deleteCarById(int id, TrafficLight.Possition possition){
       Car car;

        switch (possition){
            case INVALID:
                break;
            case LEFT:
                for(Car tmp : carsWaitingLeft){
                    if(tmp.getId() == id){
                        car = tmp;
                        carsWaitingLeft.remove(car);
                        break;
                    }
                }

                break;
            case RIGHT:
                for(Car tmp : carsWaitingRight){
                    if(tmp.getId() == id){
                        car = tmp;
                        carsWaitingRight.remove(car);
                        break;
                    }
                }
                break;
            case UP:
                for(Car tmp : carsWaitingUp){
                    if(tmp.getId() == id){
                        car = tmp;
                        carsWaitingUp.remove(car);

                        break;
                    }
                }
                break;
            case DOWN:
                for(Car tmp : carsWaitingDown){
                    if(tmp.getId() == id) {
                        car = tmp;
                        carsWaitingDown.remove(car);
                        break;
                    }
                }
                break;
        }
    }

    /**
     * Usuniecie pierwszego samochodu z listy po odpowieniej stronie
     *
     * @param possition  strona z ktorej chcemy usunac
     */
    public void deleteFirstCar(TrafficLight.Possition possition){

        switch (possition){
            case INVALID:
                break;
            case LEFT:
                carsWaitingLeft.remove(0);
                break;
            case RIGHT:
                carsWaitingRight.remove(0);
                break;
            case UP:
                carsWaitingUp.remove(0);
                break;
            case DOWN:
                carsWaitingDown.remove(0);
                break;
        }
    }

    public void setCarsPassed(long carsPassed){
        this.carsPassed = carsPassed;
    }

    public void incrementCarsPassed(){
        carsPassed++;
    }

    public void addToCarsPassed(int number){
       carsPassed += number;
    }

    public TrafficLight getTrafficLightLeft(){
        return  trafficLightLeft;
    }

    public TrafficLight getTrafficLightRight(){
        return trafficLightRight;
    }

    public TrafficLight getTrafficLightUp(){
        return  trafficLightUp;
    }

    public TrafficLight getTrafficLightDown(){
        return trafficLightDown;
    }

    public TrafficLight getTrafficLightById(int id){

        if(trafficLightLeft.getId() == id)
            return trafficLightLeft;

        if(trafficLightDown.getId() == id)
            return trafficLightDown;

        if(trafficLightRight.getId() == id)
            return trafficLightRight;

        if(trafficLightUp.getId() == id)
            return trafficLightUp;

        return null;
    }

    public ArrayList<Car> getCarsWaitingLeft(){
        return carsWaitingLeft;
    }

    public ArrayList<Car> getCarsWaitingRight(){
        return carsWaitingRight;
    }

    public ArrayList<Car> getCarsWaitingUp(){
        return carsWaitingUp;
    }

    public ArrayList<Car> getCarsWaitingDown(){
        return carsWaitingDown;
    }

    public long getCarsPassed(){
        return carsPassed;
    }


}
