package observer.chronoGUI;

import subject.Chrono;

import java.awt.*;

public class RomanChrono extends AnalogChrono{
    public RomanChrono(Chrono chrono, String name) {
        super(chrono, name);
        img = Toolkit.getDefaultToolkit().getImage("images/cadran_chiffres_romains.jpg").getScaledInstance(dimension.width,dimension.height, Image.SCALE_DEFAULT);

    }
}
