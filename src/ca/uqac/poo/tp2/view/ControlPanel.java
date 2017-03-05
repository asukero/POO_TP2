package ca.uqac.poo.tp2.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ControlPanel extends JPanel {

    JButton pauseButton = new JButton("Pause");
    JButton resetButton = new JButton("Reset");
    JLabel timeLabel = new JLabel("time elapsed : 00:00:00");

    public ControlPanel() {
        setLayout(new GridLayout(3,1,0,20));
        setBorder(new EmptyBorder(100,10,100,10));
        setPreferredSize(new Dimension(200,100));

        add(pauseButton);
        add(resetButton);
        add(timeLabel);

    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
}