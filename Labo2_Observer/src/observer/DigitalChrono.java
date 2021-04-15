package observer;

import subject.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DigitalChrono extends Observer {

    private Chrono chrono; // possede ref sur sujet concret (class Subject ici ou Chrono?)
    private JPanel panel;
    private JLabel timeLabel;
    private final DecimalFormat timeFormat = new DecimalFormat("00");


    public DigitalChrono(String name){
        super(name);

        panel = new JPanel();
        panel.setPreferredSize(dimension);

        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel(name + ": "));
        timeLabel = new JLabel("00h 00m 00s");
        panel.add(timeLabel);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void setChrono(Chrono c){
        chrono = c;
    }

    @Override
    public void update() {
        ChronoData newChronoState = chrono.getChronoState();
        timeLabel.setText(timeFormat.format(newChronoState.getHours()) + "h "
                + timeFormat.format(newChronoState.getMinutes()) + "m "
                + timeFormat.format(newChronoState.getSeconds()) + "s ");
    }
}
