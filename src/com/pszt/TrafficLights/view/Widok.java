package com.pszt.TrafficLights.view;

import com.pszt.TrafficLights.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kometa
 * Date: 04.05.2013
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class Widok extends JPanel {


    public ArrayList<Car> cars;

    public ArrayList<Crossroad> crossroads;

    public ArrayList<SpawnPoint> spawnPoints;

    private int[] horizontal;
    private int[] vertical;

    boolean modelPoprawiony = false;


    public Widok(Model m) {
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        crossroads = m.getCrossroads();
        spawnPoints = m.getSpawnPoints();
        cars = m.getCars();

    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);

        for (int horizontalLine : horizontal) {
            g2d.fillRect(0, horizontalLine - Model.ROAD_WIDTH/2, Model.BOARD_WIDTH, Model.ROAD_WIDTH);
         //   g2d.drawLine(0, horizontalLine, 1000, horizontalLine);
        }
        for (int verticalLine : vertical) {
            g2d.fillRect(verticalLine - Model.ROAD_WIDTH/2, 0 , Model.ROAD_WIDTH, Model.BOARD_HEIGHT);
           // g2d.drawLine(verticalLine, 0, verticalLine, 500);
        }





        for (Crossroad tmp : crossroads) {

            Point cords = tmp.getPosition();
            g2d.setColor(Color.blue);

            g2d.drawRect(cords.x -20, cords.y-20, 40, 40);
            Point hPoint, vPoint;
            hPoint = (Point)cords.clone();
            vPoint =(Point)cords.clone();
            TrafficLight.LightColor light = tmp.getTrafficLightHorizontal().getColor();
            if(tmp.isAscendingHorizontal()){
               hPoint.x -= 25;
               hPoint.y += 15;
            }else{
                hPoint.x += 15;
                hPoint.y -= 25;
            }
            if(tmp.isAscendingVertical()){
                vPoint.x -= 25;
                vPoint.y -= 25;
            }else{
                vPoint.x += 15;
                vPoint.y += 15;
            }
            drawLight(g2d, tmp.getTrafficLightHorizontal().getColor(),hPoint);
            drawLight(g2d, tmp.getTrafficLightVertical().getColor(),vPoint);

        }


        for (Car tmp : cars) {
            g2d.setColor(Color.ORANGE);
            g2d.draw(tmp.getHitBox());
            g2d.setColor(Color.green);
            g2d.fill(tmp.getBounds());

        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void maziaj(ArrayList<Car> kary, ArrayList<Crossroad> krosorldy) {
        cars = kary;
        crossroads = krosorldy;

        repaint();
    }

    public void setHorizonalLines(int[] lines) {
        horizontal = lines;

    }

    public void setVerticalLines(int[] lines) {
        vertical = lines;

    }

    private void drawLight(Graphics2D g2d, TrafficLight.LightColor light, Point p){

        if (light == TrafficLight.LightColor.GREEN)
            g2d.setColor(Color.green);
        else if (light == TrafficLight.LightColor.RED)
            g2d.setColor(Color.red);
        else
            g2d.setColor(Color.yellow);
        g2d.fillRect(p.x, p.y, 10, 10);
    }

}