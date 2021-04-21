package observer.chronoGUI;

import observer.Observer;
import subject.Chrono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract public class ChronoPanel extends JPanel implements Observer {

    private Chrono chrono; // possede ref sur sujet concret
    private String labelName; // = labelName is : Chrono#id
    private final Dimension dimension = new Dimension(200,200);

    protected ChronoPanel(Chrono chrono,String labelName){
        this.labelName = labelName;

        setPreferredSize(dimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                chrono.switchState();
            }
        });

        this.chrono = chrono;
        chrono.attach(this);

    }

    protected Chrono getChrono() {
        return chrono;
    }

    protected String getLabelName() {
        return labelName;
    }

    /*
    @Override
    public Dimension getPreferredSize(){
        return dimension;
    }*/

    public void setFrameListener() {
        JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        parent.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                chrono.detach(ChronoPanel.this);
            }
        });
    }
}
