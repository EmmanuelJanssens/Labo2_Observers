import javax.swing.*;
import java.text.DecimalFormat;

public class DigitalChrono extends Observer {

    private Chrono chrono; // possede ref sur sujet concret (class Subject ici ou Chrono?)

    private JLabel label;
    DecimalFormat timeFormat = new DecimalFormat("00");


    public DigitalChrono(){
        JFrame frame = new JFrame("Bouncer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        JPanel panel = new JPanel();

        label = new JLabel("00h 00m 00s");
        panel.add(label);

        frame.setContentPane(panel);
        frame.setVisible(true);

    }

    public void setChrono(Chrono c){
        chrono = c;
    }

    @Override
    void update() {
        ChronoData newChronoState = chrono.getChronoState();
        label.setText(timeFormat.format(newChronoState.getHours()) + "h "
                + timeFormat.format(newChronoState.getMinutes()) + "m "
                + timeFormat.format(newChronoState.getSeconds()) + "s ");
    }
}
