package observer;

import javax.swing.*;
import java.awt.*;

abstract public class Observer {

    protected String labelName; // = labelName is : Chrono#id
    protected JFrame frame;
    //protected JLabel label;
    protected final Dimension dimension = new Dimension(200,200);

    protected Observer(String labelName){
        this.labelName = labelName;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(dimension.width+30,dimension.height+50);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

    }

    public abstract void update();
}
