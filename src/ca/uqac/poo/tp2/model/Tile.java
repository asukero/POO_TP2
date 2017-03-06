package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Tile extends Observable {
    private Position position;
    private ArrayList<Pigeon> pigeons;
    private Food food;

    public Tile(Position position) {
        this.position = position;
        this.pigeons = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public synchronized void putFood(Food food) {
        if (this.food == null) {
            this.food = food;
            setChanged();
            notifyObservers();
            System.out.println("[*] Food added on Tile " + position.toString());
        } else {
            System.out.println("[!] Food already on Tile " + position.toString() + "!");
        }

    }

    public synchronized void putPigeon(Pigeon pigeon) {
        this.pigeons.add(pigeon);
        setChanged();
        notifyObservers();
    }

    public synchronized void removePigeon(Pigeon pigeon) {
        if (pigeon != null) {
            Iterator<Pigeon> it = pigeons.iterator();

            while (it.hasNext()) {
                Pigeon pigeonIt = it.next();

                if (pigeonIt == pigeon)
                    it.remove();
            }
            setChanged();
            notifyObservers();
        }
    }

    public synchronized void removeFood(Pigeon pigeon) {
        if (food != null) {
            food = null;
            System.out.println(String.format("[*] Pigeon %s has eaten food on Tile %s", pigeon.getName(), position.toString()));
            setChanged();
            notifyObservers();
        }
    }

    public ArrayList<Pigeon> getPigeons() {
        return pigeons;
    }

    public Food getFood() {
        return food;
    }

    public boolean hasPigeon() {
        return pigeons.size() > 0;
    }

    public boolean hasFood() {
        return food != null;
    }

    public void resetTile() {
        food = null;
        pigeons.clear();
        setChanged();
        notifyObservers();
    }


}
