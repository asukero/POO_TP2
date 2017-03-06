package ca.uqac.poo.tp2;

import ca.uqac.poo.tp2.controllers.PauseResumeController;
import ca.uqac.poo.tp2.controllers.ResetController;
import ca.uqac.poo.tp2.controllers.TileController;
import ca.uqac.poo.tp2.model.CrazyRun;
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
    private PauseResumeController pauseResumeController;
    private ResetController resetController;
    private ArrayList<Pigeon> pigeons;

    public static void main(String args[]) {
        GameSettingsFrame gameSettingsFrame = new GameSettingsFrame();
    }

    public static void launchGame(int nbRows, int nbCols, int nbPigeons, int speed) {
        PigeonMania game = new PigeonMania();
        game.environnement = new Environnement(nbRows, nbCols, nbPigeons, speed);
        game.initControllers();
        game.initView(nbRows, nbCols);
        game.pigeons = game.environnement.spawnPigeons();
        game.pauseResumeController.setPigeons(game.pigeons);
        for (Pigeon pigeon : game.pigeons) {
            Thread t = new Thread(pigeon);
            t.start();
        }
        Thread t = new Thread(new CrazyRun(speed));
        t.start();

    }

    private void initControllers() {

        tileController = new TileController(environnement);
        pauseResumeController = new PauseResumeController();
        resetController = new ResetController(environnement, pauseResumeController);
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
        mainFrame.getControlPanel().getPauseButton().addMouseListener(pauseResumeController);
        mainFrame.getControlPanel().getResetButton().addMouseListener(resetController);

        tileController.addObserver(mainFrame.getControlPanel());
    }
}
