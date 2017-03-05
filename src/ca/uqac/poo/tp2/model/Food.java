package ca.uqac.poo.tp2.model;

import ca.uqac.poo.tp2.utils.Position;

public class Food {
    private long freshness;
    private Position position;

    public Food(Position position) {
        this.position = position;
        this.freshness = System.currentTimeMillis();
    }

    public long getFreshness() {
        return freshness;
    }
}
