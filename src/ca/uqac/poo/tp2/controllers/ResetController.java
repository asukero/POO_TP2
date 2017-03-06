package ca.uqac.poo.tp2.controllers;

import ca.uqac.poo.tp2.model.Environnement;
import ca.uqac.poo.tp2.model.Pigeon;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ResetController extends MouseAdapter {
    private Environnement environnement;
    private PauseResumeController pauseResumeController;

    public ResetController(Environnement environnement, PauseResumeController pauseResumeController) {
        this.environnement = environnement;
        this.pauseResumeController = pauseResumeController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            System.out.println("[*] The board is reset");
            Pigeon.pauseResumePigeons(true);
            environnement.resetBoard();
            ArrayList<Pigeon> pigeons = environnement.spawnPigeons();
            pauseResumeController.setPigeons(pigeons);
            Pigeon.pauseResumePigeons(false);
            for (Pigeon pigeon : pigeons) {
                Thread t = new Thread(pigeon);
                t.start();
            }
        }
    }
}
