package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;

public class Pigeon implements Runnable {
    private String name;
    private Position position;
    private Environnement environnement;
    private int speed;
    private Object monitor;

    public Pigeon(String name, Position position, Environnement environnement, int speed, Object monitor) {
        this.name = name;
        this.position = position;
        this.speed = speed;
        this.environnement = environnement;
        this.monitor = monitor;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            while (true) {
                try {
                    monitor.wait();
                    ArrayList<Tile> tilesWithFood = environnement.getTilesWithFood();

                    if (tilesWithFood.size() > 0) {
                        double freshnessTile = 0;
                        Tile freshTile = tilesWithFood.get(0);
                        for (Tile tile : tilesWithFood) {
                            if (tile.getFood().getFreshness() > freshnessTile) {
                                freshTile = tile;
                                freshnessTile = tile.getFood().getFreshness();
                            }
                        }
                        int xDiff = freshTile.getPosition().getX() - position.getX();
                        int yDiff = freshTile.getPosition().getY() - position.getY();

                        environnement.getTile(position).removePigeon();
                        if (xDiff != 0 || yDiff != 0) {


                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                // MOVE UP
                                if (xDiff < 0) {
                                    position = new Position(position.getX() - 1, position.getY());
                                }
                                // MOVE DOWN
                                else {
                                    position = new Position(position.getX() + 1, position.getY());

                                }
                            } else {
                                // MOVE LEFT
                                if (yDiff < 0) {
                                    position = new Position(position.getX(), position.getY() - 1);
                                }
                                // MOVE RIGHT
                                else {
                                    position = new Position(position.getX(), position.getY() + 1);

                                }
                            }
                        }
                        Tile newTile = environnement.getTile(position);
                        newTile.putPigeon(this);

                        if (newTile.getPosition() == freshTile.getPosition()) {
                            newTile.removeFood();
                        }

                        Thread.sleep(1000 / speed);
                        monitor.notifyAll();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
