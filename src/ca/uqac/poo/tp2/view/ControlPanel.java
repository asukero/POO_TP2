package ca.uqac.poo.tp2.view;

import ca.uqac.poo.tp2.model.Pigeon;
import ca.uqac.poo.tp2.model.Tile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ControlPanel extends JPanel implements Observer{

    JButton pauseButton = new JButton("Pause");
    JLabel infoTileLabel = new JLabel("Tile Info : ");

    public ControlPanel() {
        setLayout(new GridLayout(2,1,0,20));
        setBorder(new EmptyBorder(100,10,100,10));
        setPreferredSize(new Dimension(200,100));

        add(infoTileLabel);
        add(pauseButton);

    }

    public JButton getPauseButton() {
        return pauseButton;
    }


    /*
        Observes the TileController to display the elements on a tile hovered by the user's mouse.
     */
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Tile){
            Tile tile = (Tile)arg;
            String text = "<html><p>Tile " + tile.getPosition().toString() + ":</p><p>";
            for(Pigeon pigeon: tile.getPigeons()){
                text += pigeon.getName() + " ";
            }
            if(tile.hasFood()){
                text += "F ";
            }
            text += "</p></html>";
            infoTileLabel.setText(text);
        }
    }
}