package ca.uqac.poo.tp2.controllers;

import ca.uqac.poo.tp2.model.Environnement;
import ca.uqac.poo.tp2.model.Food;
import ca.uqac.poo.tp2.view.TilePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileController extends MouseAdapter{

    private Environnement environnement;

    public TileController(Environnement environnement) {
        this.environnement = environnement;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof TilePanel){
            TilePanel tile = (TilePanel)e.getSource();
            environnement.getTile(tile.getPosition()).putFood(new Food(tile.getPosition()));
        }
    }
}
