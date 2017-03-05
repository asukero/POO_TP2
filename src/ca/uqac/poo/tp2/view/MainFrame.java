package ca.uqac.poo.tp2.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static final String TITLE = "Pigeon Mania";
    private ControlPanel controlPanel = new ControlPanel();
    private GameBoard gameBoard;

    public MainFrame(int nbRows, int nbCols) {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLayout(new BorderLayout(0, 0));

        gameBoard = new GameBoard();
        add(controlPanel, BorderLayout.EAST);
        add(gameBoard.createBoard(nbRows, nbCols), BorderLayout.CENTER);
        setVisible(true);


    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
