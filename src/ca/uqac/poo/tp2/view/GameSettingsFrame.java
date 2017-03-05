package ca.uqac.poo.tp2.view;

import ca.uqac.poo.tp2.PigeonMania;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameSettingsFrame extends JFrame implements ChangeListener {
    static final String TITLE = "Game Settings";
    private int nbRows = 9;
    private int nbCols = 9;
    private int speed = 5;
    private int nbPigeons = 2;

    public GameSettingsFrame() {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout(0, 0));
        JPanel gameSettingPanel = new JPanel(new GridLayout(5, 1, 5, 5));


        SettingsPanel sPRows = new SettingsPanel(new FlowLayout(FlowLayout.CENTER, 20, 0), "Number of Rows", 3, 30, nbRows);
        sPRows.getSettingSlider().addChangeListener(this);
        gameSettingPanel.add(sPRows);

        SettingsPanel sPCols = new SettingsPanel(new FlowLayout(FlowLayout.CENTER, 20, 0), "Number of Columns", 3, 30, nbCols);
        sPCols.getSettingSlider().addChangeListener(this);
        gameSettingPanel.add(sPCols);

        SettingsPanel sPPigeons = new SettingsPanel(new FlowLayout(FlowLayout.CENTER, 20, 0), "Number of Pigeons", 0, 10, 2);
        sPPigeons.getSettingSlider().addChangeListener(this);
        gameSettingPanel.add(sPPigeons);

        SettingsPanel sPSpeed = new SettingsPanel(new FlowLayout(FlowLayout.CENTER, 20, 0), "Speed (tick per second)", 1, 60, 5);
        sPSpeed.getSettingSlider().addChangeListener(this);
        gameSettingPanel.add(sPSpeed);


        JButton startButton = new JButton("Start!");
        JFrame frame = this;
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PigeonMania.launchGame(nbRows,nbCols, nbPigeons, speed);
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(startButton);
        gameSettingPanel.add(buttonPanel);
        add(gameSettingPanel);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            JSlider slider = (JSlider) e.getSource();

            if (slider.getParent() instanceof SettingsPanel) {
                SettingsPanel settingPanel = (SettingsPanel) slider.getParent();
                switch (settingPanel.getNamePanel()) {
                    case "Number of Rows":
                        nbRows = slider.getValue();
                        break;
                    case "Number of Columns":
                        nbCols = slider.getValue();
                        break;
                    case "Number of Pigeons":
                        nbPigeons = slider.getValue();
                        break;
                    case "Speed (tick per second)":
                        speed = slider.getValue();
                        break;
                    default:
                        break;
                }
            }

        }
    }
}
