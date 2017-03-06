package ca.uqac.poo.tp2.view;

import ca.uqac.poo.tp2.utils.Position;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/*
    GameBoard Panel.
 */
public class GameBoard extends JPanel {
    private TilePanel tiles[][];

    public GameBoard() {
        super(new BorderLayout());
    }

    public JPanel createBoard(int nbRows, int nbCols){
        tiles = new TilePanel[nbRows][nbCols];
        JPanel board = new JPanel(new GridLayout(nbRows, nbCols));
        board.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        for (int row = 0; row < nbRows; row++)
            for (int col = 0; col < nbCols; col++) {
                TilePanel tile = new TilePanel(new Position(row,col));
                tile.setBorder(new LineBorder(Color.DARK_GRAY, 2));
                tiles[row][col] = tile;
                board.add(tiles[row][col]);
            }
        return board;
    }

    public TilePanel[][] getTiles() {
        return tiles;
    }
}
