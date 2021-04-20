package observer;

import subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class DigitalChrono extends Observer {

    private Chrono chrono; // possede ref sur sujet concret (class Subject ici ou Chrono?)
    private JPanel panel;
    private JLabel timeLabel;
    private final DecimalFormat timeFormat = new DecimalFormat("00");


    public DigitalChrono( Chrono chrono, String name){
        super(name);

        panel = new JPanel();
        panel.setPreferredSize(dimension);

        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel(name + ": "));
        timeLabel = new JLabel("00h 00m 00s");
        panel.add(timeLabel);

        //frame.setContentPane(panel);
        //frame.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(chrono.getChronoData().getCurrentState() == State.PAUSED || chrono.getChronoData().getCurrentState() == State.RESET){
                    chrono.setChronoData(State.RUNNING);
                } else { // currentState == State.RUNNING
                    chrono.setChronoData(State.PAUSED);
                }
            }
        });

        this.chrono = chrono;
        chrono.attach(this);
    }

    @Override
    public void update() {
        ChronoData newChronoState = chrono.getChronoData();
        timeLabel.setText(timeFormat.format(newChronoState.getHours()) + "h "
                + timeFormat.format(newChronoState.getMinutes()) + "m "
                + timeFormat.format(newChronoState.getSeconds()) + "s ");
    }

    public JPanel getPanel()
    {
        return panel;
    }

    @Override
    public void setFrameListener() {
        JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(panel);
        parent.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                chrono.detach(DigitalChrono.this);
            }
        });
    }
}
