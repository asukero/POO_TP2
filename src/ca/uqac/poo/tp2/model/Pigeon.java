package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;
import java.util.Random;

public class Pigeon implements Runnable {
    private String name;
    private Position position;
    private Environnement environnement;
    private int speed;
    private static boolean PAUSED = false;
    private static boolean CRAZY = false;

    public Pigeon(String name, Position position, Environnement environnement, int speed) {
        this.name = name;
        this.position = position;
        this.speed = speed;
        this.environnement = environnement;
    }

    public String getName() {
        return name;
    }


    @Override
    public void run() {
        while (!PAUSED) {
            try {
                if (!CRAZY) {
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

                        environnement.getTile(position).removePigeon(this);

                        if (xDiff != 0 || yDiff != 0) {
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                // MOVE LEFT
                                if (xDiff < 0) {
                                    position = new Position(position.getX() - 1, position.getY());
                                }
                                // MOVE RIGHT
                                else {
                                    position = new Position(position.getX() + 1, position.getY());

                                }
                            } else {
                                // MOVE UP
                                if (yDiff < 0) {
                                    position = new Position(position.getX(), position.getY() - 1);
                                }
                                // MOVE DOWN
                                else {
                                    position = new Position(position.getX(), position.getY() + 1);

                                }
                            }
                        }

                        Tile newTile = environnement.getTile(position);
                        newTile.putPigeon(this);

                        if (newTile.getPosition() == freshTile.getPosition()) {
                            newTile.removeFood(this);
                        }
                    }
                } else {
                    Random rn = new Random();
                    int direction = rn.nextInt(4);

                    Position nextPosition = position;
                    switch (direction) {
                        case 0: //MOVE LEFT
                            if (position.getX() > 0) {
                                nextPosition = new Position(position.getX() - 1, position.getY());
                            }
                            break;
                        case 1: //MOVE RIGHT
                            if (position.getX() < environnement.getNbRows() - 1) {
                                nextPosition = new Position(position.getX() + 1, position.getY());
                            }
                            break;
                        case 2: //MOVE UP
                            if (position.getY() > 0) {
                                nextPosition = new Position(position.getX(), position.getY() - 1);
                            }
                            break;
                        case 3: //MOVE DOWN
                            if (position.getY() < environnement.getNbCols() - 1) {
                                nextPosition = new Position(position.getX(), position.getY() + 1);
                            }
                            break;
                    }

                    if(nextPosition != position){
                        environnement.getTile(position).removePigeon(this);
                        position = nextPosition;
                        Tile newTile = environnement.getTile(position);
                        newTile.putPigeon(this);
                    }
                }

                Thread.sleep(1000 / speed);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void pauseResumePigeons(boolean PAUSED) {
        Pigeon.PAUSED = PAUSED;
    }

    public static void setCrazy(boolean CRAZY) {
        Pigeon.CRAZY = CRAZY;
    }

    public static boolean isCrazy() {
        return CRAZY;
    }
}
