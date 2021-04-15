import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class ArabChrono extends Observer {

    private Chrono chrono; // possede ref sur sujet concret (class Subject ici ou Chrono?)

    private final Dimension dimension = new Dimension(200,200);

    private JFrame frame;
    private Canvas panel;

    public ArabChrono(){

        frame = new JFrame();
        panel = new Canvas();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(dimension.width+30,dimension.height+50);
        frame.setResizable(false);

        frame.setLayout(new FlowLayout());
        //panel.setLayout(new FlowLayout());
        panel.setPreferredSize(dimension);
        System.out.println(panel.getWidth()/2);


        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private class Canvas extends JPanel{

        private Image img;
        private Graphics2D g2d;

        private float endX = 108;
        private float endY = 81.5f; // 106.5 - 25

        private double rotAngle = 6; // 360 / 60 = 6
        private final float rayon = 25f;

        public Canvas(){
            setLayout(new FlowLayout());
            img = Toolkit.getDefaultToolkit().getImage("images/cadran_chiffres_arabes.jpg").getScaledInstance(dimension.width,dimension.height, Image.SCALE_DEFAULT);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g2d = (Graphics2D) g;
            int x = (this.getWidth() - img.getWidth(this)) / 2;
            int y = (this.getHeight() - img.getHeight(this)) / 2;
            g2d.drawImage(img, x, y, this);
            g2d.setStroke(new BasicStroke(5));
            g2d.draw(new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, endX, endY));

        }

        public void updatePointers(Graphics g){
            g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(5));

            int x = (this.getWidth() - img.getWidth(this)) / 2;
            int y = (this.getHeight() - img.getHeight(this)) / 2;
            g2d.drawImage(img, x, y, this);
            g2d.setStroke(new BasicStroke(5));

            rotAngle += 6;

            Line2D pointer = new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, endX, endY);
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(rotAngle), pointer.getX1(), pointer.getY1());
            g2d.draw(at.createTransformedShape(pointer));
        }
    }

    public void setChrono(Chrono c){
        chrono = c;
    }

    @Override
    void update() {
        ChronoData newChronoState = chrono.getChronoState();

        panel.updatePointers(panel.getGraphics());
        /*
        label.setText(timeFormat.format(newChronoState.getHours()) + "h "
                + timeFormat.format(newChronoState.getMinutes()) + "m "
                + timeFormat.format(newChronoState.getSeconds()) + "s ");*/
    }

}
