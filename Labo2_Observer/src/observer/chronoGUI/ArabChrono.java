package observer.chronoGUI;

import subject.Chrono;

import java.awt.*;

public class ArabChrono extends AnalogChrono{
    public ArabChrono(Chrono chrono, String name ) {
        super(chrono, name);
        img = Toolkit.getDefaultToolkit().getImage("images/cadran_chiffres_arabes.jpg").getScaledInstance(dimension.width,dimension.height, Image.SCALE_DEFAULT);

    }
}
