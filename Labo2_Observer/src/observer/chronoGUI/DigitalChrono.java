package observer.chronoGUI;

import subject.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DigitalChrono extends ChronoPanel {

    private JLabel timeLabel;
    private final DecimalFormat timeFormat = new DecimalFormat("00");

    public DigitalChrono( Chrono chrono, String name){
        super(chrono,name);
        setLayout(new GridBagLayout());
        add(new JLabel(name + ": "));
        timeLabel = new JLabel();
        setTimeLabel(0,0,0);
        add(timeLabel);
    }

    @Override
    public void update() {
        setTimeLabel(getChrono().getHours(), getChrono().getMinutes(), getChrono().getSeconds());
    }

    private void setTimeLabel(long hour, long minute, long second){
        timeLabel.setText(timeFormat.format(hour) + "h "
                + timeFormat.format(minute) + "m "
                + timeFormat.format(second) + "s ");
    }

}
