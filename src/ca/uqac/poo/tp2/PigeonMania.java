package ca.uqac.poo.tp2;

import ca.uqac.poo.tp2.controllers.TileController;
import ca.uqac.poo.tp2.model.Environnement;
import ca.uqac.poo.tp2.model.Pigeon;
import ca.uqac.poo.tp2.model.Tile;
import ca.uqac.poo.tp2.view.GameSettingsFrame;
import ca.uqac.poo.tp2.view.MainFrame;
import ca.uqac.poo.tp2.view.TilePanel;

import java.util.ArrayList;

public class PigeonMania {
    private MainFrame mainFrame;
    private Environnement environnement;
    private TileController tileController;

    public static void main(String args[]) {
        GameSettingsFrame gameSettingsFrame = new GameSettingsFrame();
    }

    public static void launchGame(int nbRows, int nbCols, int nbPigeons, int speed) {
        PigeonMania game = new PigeonMania();
        game.environnement = new Environnement(nbRows, nbCols, nbPigeons, speed);
        game.initControllers();
        game.initView(nbRows, nbCols);
        Object monitor = new Object();
        ArrayList<Pigeon> pigeons = game.environnement.spawnPigeons(nbPigeons, monitor);
        long cT = System.currentTimeMillis();

        for (Pigeon pigeon : pigeons) {
            Thread t = new Thread(pigeon);
            t.start();
        }
        synchronized (monitor) {
            while (System.currentTimeMillis() - cT < 10000) {
                monitor.notify();
                try {
                    monitor.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    private void initControllers() {
        tileController = new TileController(environnement);
    }

    private void initView(int nbRows, int nbCols) {
        mainFrame = new MainFrame(nbRows, nbCols);

        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                Tile tileModel = environnement.getBoard().getTile(i, j);
                TilePanel tileView = mainFrame.getGameBoard().getTiles()[i][j];
                tileModel.addObserver(tileView);
                tileView.addMouseListener(tileController);
            }
        }
    }
}
