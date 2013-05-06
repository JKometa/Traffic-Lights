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
        g2d.setColor(Color.blue);

        for (int horizontalLine : horizontal) {
            g2d.drawLine(0, horizontalLine, 1000, horizontalLine);
        }
        for (int verticalLine : vertical) {
            g2d.drawLine(verticalLine, 0, verticalLine, 500);
        }
        g2d.setColor(Color.green);


        for (Car tmp : cars) {

            g2d.draw(tmp.getBounds());
        }

        for (Crossroad tmp : crossroads) {

            Point cords = tmp.getPosition();
            TrafficLight.LightColor light = tmp.getTrafficLightHorizontal().getColor();
            if (light == TrafficLight.LightColor.GREEN)
                g2d.setColor(Color.green);
            else if (light == TrafficLight.LightColor.RED)
                g2d.setColor(Color.red);
            else
                g2d.setColor(Color.yellow);
            g2d.fillRect(cords.x -5, cords.y-5, 10, 10);
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

}