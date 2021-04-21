package observer.chronoGUI;

import subject.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class AnalogChrono extends ChronoPanel {

    protected Image img;
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

    public AnalogChrono(Chrono chrono, String name ){
        super(chrono,name);
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

    public void updatePointers(Graphics g ){

        drawBackground(g); // image + label

        // moved second pointer
        drawUpdatedPointer(chrono.getSeconds(), secondPointerWidth,secondPointerColor, endXSecond, endYSecond);
        // moved minute pointer
        drawUpdatedPointer(chrono.getMinutes(), minutePointerWidth, minutePointerColor, endXMinute, endYMinute);
        // moved hour pointer
        drawUpdatedPointer(chrono.getHours(), hourPointerWidth, hourPointerColor, endXHour, endYHour);
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

    @Override
    public void update() {
        updatePointers(getGraphics());
    }

}
