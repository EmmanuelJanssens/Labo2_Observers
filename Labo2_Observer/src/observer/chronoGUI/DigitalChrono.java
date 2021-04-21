package observer.chronoGUI;

import subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class DigitalChrono extends ChronoPanel {

    private JLabel timeLabel;
    private final DecimalFormat timeFormat = new DecimalFormat("00");


    public DigitalChrono( Chrono chrono, String name){
        super(chrono,name);
        setLayout(new GridBagLayout());
        add(new JLabel(name + ": "));
        timeLabel = new JLabel("00h 00m 00s");
        add(timeLabel);
    }

    @Override
    public void update() {
        timeLabel.setText(timeFormat.format(chrono.getHours()) + "h "
                + timeFormat.format(chrono.getMinutes()) + "m "
                + timeFormat.format(chrono.getSeconds()) + "s ");
    }
}
