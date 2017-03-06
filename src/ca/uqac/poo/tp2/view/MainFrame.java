package ca.uqac.poo.tp2.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.PrintStream;

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

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        initLogger(bottomPanel);
        add(controlPanel, BorderLayout.EAST);
        add(gameBoard.createBoard(nbRows, nbCols), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);


    }

    private void initLogger(JPanel bottomPanel) {
        EventQueue.invokeLater(() -> {
            LogPanel logPanel = new LogPanel();
            bottomPanel.add(logPanel, BorderLayout.CENTER);

            PrintStream ps = System.out;
            System.setOut(new PrintStream(new StreamLogger("STDOUT", logPanel, ps)));
        });
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
