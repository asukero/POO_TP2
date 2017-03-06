package ca.uqac.poo.tp2.controllers;


import ca.uqac.poo.tp2.model.Pigeon;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*
    Controller for Pause/Resume Button
    When PAUSE static boolean of Pigeon class is set to true, every thread is stopped so when we want to resume
    the threads we have to create new ones.
 */
public class PauseResumeController  extends MouseAdapter {
    private ArrayList<Pigeon> pigeons;

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton button = (JButton)e.getSource();
            if(button.getText() == "Pause"){
                Pigeon.pauseResumePigeons(true);
                button.setText("Resume");
                System.out.println("[*] All pigeons are stopped");
            } else {
                Pigeon.pauseResumePigeons(false);
                button.setText("Pause");

                for (Pigeon pigeon : pigeons) {
                    Thread t = new Thread(pigeon);
                    t.start();
                }

                System.out.println("[*] All pigeons are resumed");

            }


        }
    }

    public void setPigeons(ArrayList<Pigeon> pigeons) {
        this.pigeons = pigeons;
    }
}
