package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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

        //Building neighbors
        for (ArrayList<Tile> row : grid) {
            for (Tile tile : row) {
                HashSet<Tile> set = new HashSet<>();
                int rowIndex = tile.getPosition().getX();
                int columnIndex = tile.getPosition().getY();
                if (rowIndex - 1 >= 0) {
                    set.add(grid.get(rowIndex - 1).get(columnIndex));
                }
                if (rowIndex + 1 < nbRows) {
                    set.add(grid.get(rowIndex + 1).get(columnIndex));
                }
                if (columnIndex + 1 < nbCols) {
                    set.add(grid.get(rowIndex).get(columnIndex + 1));
                }
                if (columnIndex - 1 >= 0) {
                    set.add(grid.get(rowIndex).get(columnIndex - 1));
                }
                tile.setNeighbors(set);
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
