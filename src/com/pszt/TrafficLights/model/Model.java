package com.pszt.TrafficLights.model;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public class Model implements Cloneable {
    /**
     * szerokosc drogi
     */
    static final int ROAD_WIDTH = 40;

    /**
     * szerokosc planszy na ktorej sa skrzyzowania
     */
    static final int BOARD_WIDTH = 1000;

    /**
     * wysokosc planszy na ktorej sa skrzyzowania
     */
    static final int BOARD_HEIGHT = 1000;

    /**
     * minimalna odleglosc miedzy dwoma drogami
     */
    static private final int BOARD_MARGIN = 100;

    /**
     * kontener przechowujacy wszystkie samochody na planszy
     */
    private ArrayList< Car > cars;

    /**
     * kontener przechowujacy wszystkie skrzyzowania na planszy
     */
    private ArrayList< Crossroad > crossroads;

    /**
     * kontener przechowyjacy wszystkie poczatki drog na planszy
     */
    private ArrayList< SpawnPoint> spawnPoints;

    /**
     * generuje model z odpowiednia iloscia drog
     * @param numberOfHorizontalRoads ilosc drog poziomych
     * @param numberOfVerticalRoads ilosc drog pionowych
     *
     *  Narazie nie dziala i tworzy statycznie :P
     */

    public Model(int numberOfHorizontalRoads, int numberOfVerticalRoads) {
        cars = new ArrayList<Car>();
        crossroads = new ArrayList<Crossroad>();
        spawnPoints = new ArrayList<SpawnPoint>();

        numberOfHorizontalRoads = 2;
        numberOfVerticalRoads = 2;

        int[] horizontal = {320, 777};
        int[] vertical = {200, 555};

        Random generator = new Random();

        ArrayList < SpawnPoint > horizontalSpawnPoints = new ArrayList<SpawnPoint>();
        for (int y : horizontal){
             boolean ascending = generator.nextBoolean();
             horizontalSpawnPoints.add(new SpawnPoint((ascending ? 0 : BOARD_WIDTH), y));
        }

        ArrayList < SpawnPoint > verticalSpawnPoints = new ArrayList<SpawnPoint>();
        for (int x : vertical){
            boolean ascending = generator.nextBoolean();
            verticalSpawnPoints.add(new SpawnPoint(x, (ascending ? 0 : BOARD_HEIGHT)));
        }

        for (SpawnPoint y : horizontalSpawnPoints){
            for (SpawnPoint x : verticalSpawnPoints){
                crossroads.add(new Crossroad(x.getPositionX(), y.getPositionY(),y.isAscending(), x.isAscending()));
            }
        }

        for (SpawnPoint tmp : horizontalSpawnPoints){
            spawnPoints.add(tmp);
        }

        for (SpawnPoint tmp : verticalSpawnPoints){
            spawnPoints.add(tmp);
        }

        for (SpawnPoint spawn : spawnPoints){
            cars.add(spawn.spawn());
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            Model clone = (Model)super.clone();

            clone.crossroads = new ArrayList<Crossroad>();
            for(Crossroad cross : this.crossroads ){
                clone.crossroads.add((Crossroad)cross.clone());
            }

            clone.cars = new ArrayList<Car>();
            for(Car car : this.cars){
                clone.cars.add((Car)car.clone());
            }

            clone.spawnPoints = new ArrayList<SpawnPoint>();
            for(SpawnPoint spawn : this.spawnPoints){
                clone.spawnPoints.add((SpawnPoint)spawn.clone());
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * get bounds
     * @return prostokąt jaki zajmuje plansza modelu
     */
    public Rectangle getBounds(){
        return new Rectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<Crossroad> getCrossroads() {
        return crossroads;
    }

    public ArrayList<SpawnPoint> getSpawnPoints() {
        return spawnPoints;
    }
}
