package observer;

import subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class AnalogChrono extends Observer {

    private Chrono chrono; // possede ref sur sujet concret
    private Canvas panel;
    private String imgFilePath;


    public AnalogChrono(String name, String imgFilePath){

        super(name);
        this.imgFilePath = imgFilePath;

        panel = new Canvas();
        panel.setPreferredSize(dimension);

        frame.setContentPane(panel);
        frame.setVisible(true);

        Observer current = this;

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    chrono.detach(current);
                }
        });
    }

    private class Canvas extends JPanel{

        private Image img;
        private Graphics2D g2d;

        // Pointers end position (the moving position)
        private float endXSecond = 108;
        private float endYSecond = 40; // 106.5 - 50
        private float endXMinute = 108;
        private float endYMinute = 65f; // 106.5 - 50
        private float endXHour = 108;
        private float endYHour = 81.5f; // 106.5 - 25

        // Pointers line stroke width
        private final int secondPointerWidth = 1;
        private final int minutePointerWidth = 3;
        private final int hourPointerWidth = 5;

        // Pointer color
        private final Color secondPointerColor = Color.RED;
        private final Color minutePointerColor = Color.BLUE;
        private final Color hourPointerColor = Color.BLACK;

        // Rotation angle of a unit
        private final double rotAngle = 6; // 360 / 60 = 6

        public Canvas(){
            img = Toolkit.getDefaultToolkit().getImage(imgFilePath).getScaledInstance(dimension.width,dimension.height, Image.SCALE_DEFAULT);
        }

        @Override
        public void paintComponent(Graphics g){

            super.paintComponent(g);

            drawBackground(g); // image + label

            // Seconds pointer
            drawDefaultPointer(secondPointerWidth, secondPointerColor, endXSecond, endYSecond);
            // Minutes pointer
            drawDefaultPointer(minutePointerWidth, minutePointerColor, endXMinute, endYMinute);
            // Hour pointer
            drawDefaultPointer(hourPointerWidth, hourPointerColor, endXHour, endYHour);
        }

        public void updatePointers(Graphics g, ChronoData chronoState){

            drawBackground(g); // image + label

            // moved second pointer
            drawUpdatedPointer(chronoState.getSeconds(), secondPointerWidth,secondPointerColor, endXSecond, endYSecond);
            // moved minute pointer
            drawUpdatedPointer(chronoState.getMinutes(), minutePointerWidth, minutePointerColor, endXMinute, endYMinute);
            // moved hour pointer
            drawUpdatedPointer(chronoState.getHours(), hourPointerWidth, hourPointerColor, endXHour, endYHour);
        }

        // draw image + label
        private void drawBackground(Graphics g){

            g2d = (Graphics2D) g;

            // draw background image from resources (images folder)
            int x = (this.getWidth() - img.getWidth(this)) / 2;
            int y = (this.getHeight() - img.getHeight(this)) / 2;
            g2d.drawImage(img, x, y, this);

            // draw label name around the middle of the clock
            g2d.drawString(labelName, dimension.width/2, dimension.height/2);

        }

        // draw default pointer (before starting chrono), used for each pointer
        private void drawDefaultPointer(float pointerWidth, Color pointerColor, float pointerEndX, float pointerEndY){
            g2d.setStroke(new BasicStroke(pointerWidth));
            g2d.setColor(pointerColor);
            g2d.draw(new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, pointerEndX, pointerEndY));
        }

        // draw updated pointer(), used for each pointer
        private void drawUpdatedPointer(long chronoValue, float pointerWidth, Color pointerColor, float pointerEndX, float pointerEndY){
            Line2D pointerLine = new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, pointerEndX, pointerEndY);
            AffineTransform atSecond =
                    AffineTransform.getRotateInstance(
                            Math.toRadians(rotAngle * chronoValue),
                            pointerLine.getX1(), pointerLine.getY1());

            g2d.setStroke(new BasicStroke(pointerWidth));
            g2d.setColor(pointerColor);
            g2d.draw(atSecond.createTransformedShape(pointerLine));
        }
    }

    public void setChrono(Chrono c){
        chrono = c;
    }

    @Override
    public void update() {
        ChronoData newChronoState = chrono.getChronoState();
        panel.updatePointers(panel.getGraphics(), newChronoState);
    }

}
