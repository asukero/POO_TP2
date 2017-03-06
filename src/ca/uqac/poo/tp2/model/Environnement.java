package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Environnement extends Observable {
    private Board board;
    private int nbPigeons, speed, nbRows, nbCols;

    public Environnement(int nbRows, int nbCols, int nbPigeons, int speed) {
        this.nbPigeons = nbPigeons;
        this.speed = speed;
        this.board = new Board(nbRows, nbCols);
        this.nbRows = nbRows;
        this.nbCols = nbCols;
    }


    public Board getBoard() {
        return board;
    }

    public Tile getTile(Position pos) {
        return board.getTile(pos.getX(), pos.getY());
    }

    public ArrayList<Tile> getTilesWithFood() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                Tile tile = board.getTile(i, j);
                if (tile.hasFood()) {
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }

    public ArrayList<Pigeon> spawnPigeons() {
        Random rn = new Random();
        ArrayList<Pigeon> pigeons = new ArrayList<>(nbPigeons);
        int pigeonsSpawned = 0;
        while (pigeonsSpawned < nbPigeons) {
            int x = rn.nextInt(nbRows);
            int y = rn.nextInt(nbCols);
            Tile tile = board.getGrid().get(x).get(y);
            pigeonsSpawned++;
            Pigeon pigeon = new Pigeon(String.format("P%d", pigeonsSpawned), new Position(x, y), this, speed);
            pigeons.add(pigeon);
            tile.putPigeon(pigeon);
        }
        return pigeons;
    }

    public void resetBoard(){
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                board.getTile(i, j).resetTile();
            }
        }
    }

    public int getNbRows() {
        return nbRows;
    }

    public int getNbCols() {
        return nbCols;
    }
}
