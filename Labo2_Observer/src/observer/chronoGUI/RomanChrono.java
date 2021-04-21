package observer.chronoGUI;

import subject.Chrono;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

public class RomanChrono extends AnalogChrono{

    private Image img;
    private final static String IMG_FILEPATH = "images/cadran_chiffres_romains.jpg";

    public RomanChrono(Chrono chrono, String name) {
        super(chrono, name);
        img = Toolkit.getDefaultToolkit().getImage(IMG_FILEPATH).getScaledInstance(getPreferredSize().width,getPreferredSize().height, Image.SCALE_DEFAULT);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this); // draw background image from resources (images folder)
        drawPointersAndLabel(g);
    }

    @Override
    public void update() {
        paintComponent(getGraphics());

    }
}
