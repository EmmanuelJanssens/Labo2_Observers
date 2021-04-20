package observer.chronoGUI;

import observer.Observer;
import subject.Chrono;
import subject.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChronoPanel extends JPanel implements Observer {
    protected String labelName; // = labelName is : Chrono#id
    //protected JFrame frame;
    protected final Dimension dimension = new Dimension(200,200);
    protected Chrono chrono; // possede ref sur sujet concret

    protected ChronoPanel(Chrono chrono,String labelName){
        this.labelName = labelName;

        setPreferredSize(dimension);

        addMouseListener(new MouseAdapter() {
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

    }
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
