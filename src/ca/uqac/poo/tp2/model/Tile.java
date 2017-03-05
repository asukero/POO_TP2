package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.HashSet;
import java.util.Observable;

public class Tile extends Observable {
    private Position position;
    private Pigeon pigeon;
    private Food food;
    private HashSet<Tile> neighbors;

    public Tile(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void putFood(Food food) {
        this.food = food;
        setChanged();
        notifyObservers();
    }

    public void putPigeon(Pigeon pigeon) {
        this.pigeon = pigeon;
        setChanged();
        notifyObservers(pigeon);
    }

    public void removePigeon(){
        if(pigeon != null){
            pigeon = null;
            setChanged();
            notifyObservers();
        }
    }

    public void removeFood(){
        if(food != null){
            food = null;
            setChanged();
            notifyObservers();
        }
    }

    public Food getFood() {
        return food;
    }

    public boolean hasPigeon() {
        return pigeon != null;
    }

    public boolean hasFood() {
        return food != null;
    }

    public HashSet<Tile> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(HashSet<Tile> neighbors) {
        this.neighbors = neighbors;
    }

}
