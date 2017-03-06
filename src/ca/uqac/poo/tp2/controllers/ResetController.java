package ca.uqac.poo.tp2.controllers;

import ca.uqac.poo.tp2.model.Environment;
import ca.uqac.poo.tp2.model.Pigeon;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*
    Controller for Resume Button. Removes all Food and pigeons on the board and spawns the pigeons randomly.
    Like the PauseResumeController, the previous threads are stopped and new ones are started with the new pigeons created

 */
public class ResetController extends MouseAdapter {
    private Environment environment;
    private PauseResumeController pauseResumeController;

    public ResetController(Environment environment, PauseResumeController pauseResumeController) {
        this.environment = environment;
        this.pauseResumeController = pauseResumeController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            System.out.println("[*] The board is reset");
            Pigeon.pauseResumePigeons(true);
            environment.resetBoard();
            ArrayList<Pigeon> pigeons = environment.spawnPigeons();
            pauseResumeController.setPigeons(pigeons);
            Pigeon.pauseResumePigeons(false);
            for (Pigeon pigeon : pigeons) {
                Thread t = new Thread(pigeon);
                t.start();
            }
        }
    }
}
