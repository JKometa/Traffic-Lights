package com.pszt.TrafficLights.view;

import com.pszt.TrafficLights.model.Car;
import com.pszt.TrafficLights.model.Crossroad;
import com.pszt.TrafficLights.model.Model;
import com.pszt.TrafficLights.model.SpawnPoint;

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
        g2d.setColor(Color.green);



        if (modelPoprawiony) {
            for (Car tmp : cars) {

                g2d.draw(tmp.getBounds());
            }


        } else {
            for (Car tmp : cars) {

                int x, y, w, h;
                x = (int) tmp.getPositionX();
                y = (int) tmp.getPositionY();
                w = tmp.getBounds().width;
                h = tmp.getBounds().height;

                if (x > 1000)
                    x -= 100;
                else if (x < 0)
                    x += 100;
                if (y > 1000)
                    y -= 100;
                else if (y < 0)
                    y += 100;


                g2d.drawRect(x, y, w, h);
            }

        }


        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void maziaj() {

        repaint();
    }


}