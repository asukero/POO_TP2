package ca.uqac.poo.tp2.controllers;

import ca.uqac.poo.tp2.model.Environnement;
import ca.uqac.poo.tp2.model.Food;
import ca.uqac.poo.tp2.view.TilePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

public class TileController extends Observable implements MouseListener{

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

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof TilePanel){
            TilePanel tile = (TilePanel)e.getSource();
            setChanged();
            notifyObservers(environnement.getTile(tile.getPosition()));
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
