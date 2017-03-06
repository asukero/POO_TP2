package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;
public class Board {
    private ArrayList<ArrayList<Tile>> grid;
    private int nbRows, nbCols;

    public Board(int nbRows, int nbCols) { //Builds a nxn map
        this.nbRows = nbRows;
        this.nbCols = nbCols;

        grid = new ArrayList<>(nbRows);
        for (int i = 0; i < nbRows; i++) {
            grid.add(new ArrayList<>(nbCols));
            for (int j = 0; j < nbCols; j++) {
                grid.get(i).add(new Tile(new Position(i, j)));
            }
        }

    }

    public ArrayList<ArrayList<Tile>> getGrid() {
        return grid;
    }

    public Tile getTile(int i, int j) {
        return grid.get(i).get(j);
    }
}
