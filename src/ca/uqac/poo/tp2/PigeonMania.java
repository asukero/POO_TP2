package ca.uqac.poo.tp2;

import ca.uqac.poo.tp2.controllers.PauseResumeController;
import ca.uqac.poo.tp2.controllers.TileController;
import ca.uqac.poo.tp2.model.AfraidRun;
import ca.uqac.poo.tp2.model.Environment;
import ca.uqac.poo.tp2.model.Pigeon;
import ca.uqac.poo.tp2.model.Tile;
import ca.uqac.poo.tp2.view.GameSettingsFrame;
import ca.uqac.poo.tp2.view.MainFrame;
import ca.uqac.poo.tp2.view.TilePanel;

import java.util.ArrayList;

/*
    Main class of the program.
 */
public class PigeonMania {
    private MainFrame mainFrame;
    private Environment environment;
    private TileController tileController;
    private PauseResumeController pauseResumeController;
    private ArrayList<Pigeon> pigeons;

    /*
     main() starts the game settings frame.
      */
    public static void main(String args[]) {
        GameSettingsFrame gameSettingsFrame = new GameSettingsFrame();
    }

    /*
        called by the start button on the game settings frame, initialize models, controllers, views and lauches pigeons threads and afraid thread
     */
    public static void launchGame(int nbRows, int nbCols, int nbPigeons, int speed) {
        PigeonMania game = new PigeonMania();
        game.environment = new Environment(nbRows, nbCols, nbPigeons, speed);
        game.initControllers();
        game.initView(nbRows, nbCols);
        game.pigeons = game.environment.spawnPigeons();
        game.pauseResumeController.setPigeons(game.pigeons);
        for (Pigeon pigeon : game.pigeons) {
            Thread t = new Thread(pigeon);
            t.start();
        }
        Thread t = new Thread(new AfraidRun(speed));
        t.start();

    }

    private void initControllers() {

        tileController = new TileController(environment);
        pauseResumeController = new PauseResumeController();
    }

    private void initView(int nbRows, int nbCols) {
        mainFrame = new MainFrame(nbRows, nbCols);

        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                Tile tileModel = environment.getBoard().getTile(i, j);
                TilePanel tileView = mainFrame.getGameBoard().getTiles()[i][j];
                tileModel.addObserver(tileView);
                tileView.addMouseListener(tileController);
            }
        }
        mainFrame.getControlPanel().getPauseButton().addMouseListener(pauseResumeController);

        tileController.addObserver(mainFrame.getControlPanel());
    }
}
