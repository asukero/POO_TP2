package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;
import java.util.Random;
/*
    Pigeon runnable class. The pigeon knows the environment and iteracts with it in his thread
 */
public class Pigeon implements Runnable {
    private String name;
    private Position position;
    private Environment environment;
    private int speed;
    private static boolean PAUSED = false;
    private static boolean AFRAID = false;

    public Pigeon(String name, Position position, Environment environment, int speed) {
        this.name = name;
        this.position = position;
        this.speed = speed;
        this.environment = environment;
    }

    public String getName() {
        return name;
    }


    @Override
    public void run() {
        while (!PAUSED) { // while simulation not paused by the user
            try {
                if (!AFRAID) { //if not afraid go to the fresh food

                    //find fresh food and move to the neighbor of the current tile to reach the tile where the food is
                    ArrayList<Tile> tilesWithFood = environment.getTilesWithFood();

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

                        //moves the pigeon
                        environment.getTile(position).removePigeon(this);

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

                        Tile newTile = environment.getTile(position);
                        newTile.putPigeon(this);

                        //if the new tile where the pigeon has the fresh food, he eats it.
                        if (newTile.getPosition() == freshTile.getPosition()) {
                            newTile.removeFood(this);
                        }
                    }
                } else { //if afraid the pigeon will move randomly

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
                            if (position.getX() < environment.getNbRows() - 1) {
                                nextPosition = new Position(position.getX() + 1, position.getY());
                            }
                            break;
                        case 2: //MOVE UP
                            if (position.getY() > 0) {
                                nextPosition = new Position(position.getX(), position.getY() - 1);
                            }
                            break;
                        case 3: //MOVE DOWN
                            if (position.getY() < environment.getNbCols() - 1) {
                                nextPosition = new Position(position.getX(), position.getY() + 1);
                            }
                            break;
                    }

                    if(nextPosition != position){
                        environment.getTile(position).removePigeon(this);
                        position = nextPosition;
                        Tile newTile = environment.getTile(position);
                        newTile.putPigeon(this);
                    }
                }

                //speed = tick, e.g: if speed = 2 pigeons will make 2 moves per second.
                Thread.sleep(1000 / speed);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void pauseResumePigeons(boolean PAUSED) {
        Pigeon.PAUSED = PAUSED;
    }

    public static void setAfraid(boolean AFRAID) {
        Pigeon.AFRAID = AFRAID;
    }

    public static boolean isAfraid() {
        return AFRAID;
    }
}
