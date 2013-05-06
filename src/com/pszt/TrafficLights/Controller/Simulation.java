package com.pszt.TrafficLights.Controller;

import com.pszt.TrafficLights.model.*;
import org.w3c.dom.css.Rect;

import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 03.05.13
 * Time: 19:57
 *
 * Klasa robiąca całą logikę na modelu
 */
public class Simulation {

    private Model model;

    /**
     * określa czy nowe samochody wjeżdżają na plansze
     */
    private boolean spawnCars;



    public Simulation(Model model) {
        this.model = model;
        spawnCars = true;
    }

    public void setSpawnCars(boolean spawnCars) {
        this.spawnCars = spawnCars;
    }


    /**
     * tu sie będzie działa cala logika
     * @param deltaTime rożnica czasowa miedzy ostatnim krokiem symulacji w ms
     */
    public void update(long deltaTime){

        //update świateł
        for (Crossroad crossroad: model.getCrossroads()){
            crossroad.updateLight(deltaTime);
        }

        ArrayList< Car > carsToDelete = new ArrayList<Car>();


        // poruszanie samochodami i kolizje
        for (Car car : model.getCars()){
            //rożnica przemieszczenia
            double deltaS = deltaTime * car.getSpeed();
            car.move(deltaS);

            Rectangle carBox = car.getBounds();
            Rectangle carHitBox = car.getHitBox();
            Rectangle boardBox = model.getBounds();

            //kolizje ze skrzyżowaniami
            if (car.isOnCrossroad()){
                Rectangle crossroadBox = car.getCrossroad().getHitBox();
                if(!(crossroadBox.intersects(carBox) || crossroadBox.contains(carBox))){
                    car.setCrossroad(null);
                }
            } else{
                for(Crossroad crossroad : model.getCrossroads()){
                    Rectangle crossroadBox = crossroad.getHitBox();
                    if(crossroadBox.intersects(carBox) || crossroadBox.contains(carBox)){
                        TrafficLight light = (car.isHorizontal() ?
                                crossroad.getTrafficLightHorizontal() : crossroad.getTrafficLightVertical());
                        if(light.isGreen()){
                            car.setCrossroad(crossroad);
                            car.setSpeed(crossroad.getSpeedLimit());
                        } else{
                            car.move(-deltaS);
                            carHitBox = car.getHitBox();
                        }
                        break;
                    }
                }
            }

            //kolizje z innymi samochodami
            for(Car collisionCar : model.getCars()){
                if(car != collisionCar && carHitBox.intersects(collisionCar.getHitBox())){
                    car.move(-deltaS);
                    break;
                }
            }

            //sprawdza czy samochód nie wyjechał za plansze
            if( !boardBox.contains(carBox) && !boardBox.intersects(carBox) ){
                carsToDelete.add(car);
            }

        }

//        usuwa samochody które wyjechały za plansze
        ArrayList< Car > cars = model.getCars();
        for (Car carToDelete : carsToDelete){
             cars.remove(carToDelete);
        }

//        spawnowanie samochodów
        if (spawnCars && model.updateTimeToRespawn(deltaTime)){

//            losuje z którego spawn pointa ma sie zrespić samochód
            Random generator = new Random();
            ArrayList< SpawnPoint > spawnPoints = model.getSpawnPoints();
            int losuj = generator.nextInt(spawnPoints.size());
            SpawnPoint spawnPoint = spawnPoints.get(losuj);
            Rectangle spawnBox = spawnPoint.getHitBox();

            //sprawdza czy jest miejsce dla nowego samochodu z tego spawn pointa
            boolean clearForSpawn = true;
            for(Car car : model.getCars()){
                if(spawnBox.intersects(car.getHitBox())){
                    clearForSpawn = false;
                    break;
                }
            }

            //jeśli jest miejsce to tworzy i dodaje do kontenera nowy samochód, jeśli nie to nic nie robi
            if(clearForSpawn){
                model.getCars().add(spawnPoint.spawn());

            }
        }
    }

}

