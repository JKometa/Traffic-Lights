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
     * szerokość drogi
     */
    static public final int ROAD_WIDTH = 40;

    /**
     * szerokość planszy na której sa skrzyżowania
     */
    static public final int BOARD_WIDTH = 1000;

    /**
     * wysokość planszy na której sa skrzyżowania
     */
    static public final int BOARD_HEIGHT = 1000;

    /**
     * minimalna odległość miedzy dwoma drogami
     */
    static private final int BOARD_MARGIN = 500;

    /**
     * kontener przechowujący wszystkie samochody na planszy
     */
    private ArrayList< Car > cars;

    /**
     * kontener przechowujący wszystkie skrzyżowania na planszy
     */
    private ArrayList< Crossroad > crossroads;

    /**
     * kontener przechowujący wszystkie początki dróg na planszy
     */
    private ArrayList< SpawnPoint> spawnPoints;

    /**
     * czas za jaki ma sie zrespić nowy samochód
     */
    private long timeToNextCarSpawn;

    /**
     * górny przedział czasu na respawn samochodu
     */
    final static private int MAX_SPAWN_TIME = 6000;

    /**
     * dolny przedział czasu na respawn samochodu
     */
    final static private int MIN_SPAWN_TIME = 4000;

    /**
     * współrzędne y linie poziomych
     */
    private int[] horizontalLines;

    /**
     * współrzędne x linii pionowych
     */
    private int[] verticalLines;

    /**
     * liczba samochodów który opuściły plansze
     */
    private int carLeft;

    /**
     * średni czas przebywania w korku przez samochody
     */
    private double averageTimeInTraffic= 1000;

    /**
     * ilosc skrzyzowan na planszy
     */
    private int numberOfCrossroads;


    /**
     * generuje model z odpowiednia ilością dróg
     * @param numberOfHorizontalRoads ilość dróg poziomych
     * @param numberOfVerticalRoads ilość dróg pionowych
     *
     *  Na razie nie działa i tworzy statycznie :P
     */

    public Model(int numberOfHorizontalRoads, int numberOfVerticalRoads) {
        cars = new ArrayList<Car>();
        crossroads = new ArrayList<Crossroad>();
        spawnPoints = new ArrayList<SpawnPoint>();


        numberOfHorizontalRoads = 2;
        numberOfVerticalRoads = 3;

        int[] horizontal = {250, 750};
        int[] vertical = {100, 366, 800};

        horizontalLines = horizontal;
        verticalLines = vertical;

        Random generator = new Random();

        timeToNextCarSpawn = generator.nextInt(MAX_SPAWN_TIME + MIN_SPAWN_TIME) - MIN_SPAWN_TIME;

        ArrayList < SpawnPoint > horizontalSpawnPoints = new ArrayList<SpawnPoint>();
        for (int y : horizontal){
             boolean ascending =  generator.nextBoolean();
             horizontalSpawnPoints.add(new SpawnPoint((ascending ? 0 : BOARD_WIDTH), y, true, ascending));
        }

        ArrayList < SpawnPoint > verticalSpawnPoints = new ArrayList<SpawnPoint>();
        for (int x : vertical){
            boolean ascending = generator.nextBoolean();
            verticalSpawnPoints.add(new SpawnPoint(x, (ascending ? 0 : BOARD_HEIGHT), false, ascending));
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

        numberOfCrossroads = crossroads.size();
//        cars.add(spawnPoints.get(2).spawn() );
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            Model clone = (Model)super.clone();

            clone.carLeft = 0; //this.carLeft;
            clone.timeToNextCarSpawn = this.timeToNextCarSpawn;
            clone.averageTimeInTraffic = 0; //this.averageTimeInTraffic;
            clone.verticalLines = this.verticalLines.clone();
            clone.horizontalLines = this.horizontalLines.clone();
            clone.averageTimeInTraffic = this.averageTimeInTraffic;

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

            for(Car car : clone.cars){
                for(Crossroad crossroad : clone.crossroads){
                    Rectangle carBox = car.getBounds();
                    Rectangle crossroadBox = crossroad.getBounds();
                    if(crossroadBox.intersects(carBox) || crossroadBox.contains(carBox)){
                        car.setCrossroad(crossroad);
                    }
                }
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

    public int[] getHorizontalLines() {
        return horizontalLines;
    }

    public int[] getVerticalLines() {
        return verticalLines;
    }

    public double getAverageTimeInTraffic() {
        return averageTimeInTraffic;
    }

    public int getCarLeft() {
        return carLeft;
    }

    public int getNumberOfCrossroads() {
        return numberOfCrossroads;
    }

    public int getNumberOfCars(){
        return cars.size();
    }

    public void setIntervalsOnCrossroads(long[] intervals){
        for (int i = 0; i < numberOfCrossroads; ++i ){
            crossroads.get(i).setIntervalHorizontalGreen(intervals[i * 2]);
            crossroads.get(i).setIntervalHorizontalRed(intervals[i * 2 + 1]);
        }
    }

    public void updateAverageTimeInTraffic(long timeInTraffic){
        averageTimeInTraffic = ((averageTimeInTraffic * carLeft) + timeInTraffic) / (carLeft + 1);
        ++carLeft;
    }

    /**
     *  uaktualnia czas do respawnu
     * @param deltaTime czas jaki upłynął od ostatnie aktualizacji
     * @return true jeśli wymagany jest respawn nowego samochodu
     */
    public boolean updateTimeToRespawn(long deltaTime){
        timeToNextCarSpawn -= deltaTime;
        if(timeToNextCarSpawn <= 0){
            Random generator = new Random();
            timeToNextCarSpawn = generator.nextInt(MAX_SPAWN_TIME + MIN_SPAWN_TIME) - MIN_SPAWN_TIME;
            return true;
        }
        return false;
    }
}
