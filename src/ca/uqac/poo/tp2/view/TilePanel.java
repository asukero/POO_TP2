package ca.uqac.poo.tp2.view;

import ca.uqac.poo.tp2.model.Pigeon;
import ca.uqac.poo.tp2.model.Tile;
import ca.uqac.poo.tp2.utils.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TilePanel extends JPanel implements Observer {

    private Position position;
    private JLabel label = new JLabel("");

    public TilePanel(Position position) {
        this.position = position;
        setBackground(Color.WHITE);
        add(label);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Tile){
            Tile tile = (Tile)o;
            label.setText("");
            if(tile.hasFood()){
                label.setText(label.getText() + " F");
            }
            if(arg instanceof Pigeon){
                Pigeon pigeon = (Pigeon)arg;
                label.setText(label.getText() + " " + pigeon.getName());
            }
        }

    }
}