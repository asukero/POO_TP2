package ca.uqac.poo.tp2.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private JSlider settingSlider;
    private String namePanel;

    public SettingsPanel(LayoutManager layout, String settingName, int min, int max, int defaultVal) {
        super(layout);
        namePanel = settingName;
        setBorder(new EmptyBorder(10, 10, 10, 10));
        add(new JLabel(namePanel));
        settingSlider = new JSlider(min, max, defaultVal);
        settingSlider.setMinorTickSpacing(1);
        settingSlider.setMajorTickSpacing(10);
        settingSlider.setPaintTicks(true);
        settingSlider.setPaintLabels(true);
        add(settingSlider);

    }

    public JSlider getSettingSlider() {
        return settingSlider;
    }

    public String getNamePanel() {
        return namePanel;
    }
}