package observer.chronoGUI;

import subject.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

abstract public class AnalogChrono extends ChronoPanel {

    protected Image img;
    private Graphics2D g2d;

    // Pointers end position (the moving position)
    private int endXSecond = (int) getPreferredSize().getWidth()/2;
    private int endYSecond = 40;
    private int endXMinute = (int) getPreferredSize().getWidth()/2;
    private int endYMinute = 65;
    private int endXHour = (int) getPreferredSize().getWidth()/2;
    private int endYHour = 81;

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

        g2d = (Graphics2D) g;

        // draw background image from resources (images folder)
        int x = (this.getWidth() - img.getWidth(this)) / 2;
        int y = (this.getHeight() - img.getHeight(this)) / 2;
        g2d.drawImage(img, x, y, this);

        // draw label name around the middle of the clock
        g2d.drawString(getLabelName(), getWidth()/2, getHeight()/2);

        // TODO contrôler avec switchState pour update ou pas les pointers
        // moved second pointer
        drawPointer(getChrono().getSeconds(), secondPointerWidth,secondPointerColor, endXSecond, endYSecond);
        // moved minute pointer
        drawPointer(getChrono().getMinutes(), minutePointerWidth, minutePointerColor, endXMinute, endYMinute);
        // moved hour pointer
        drawPointer(getChrono().getHours(), hourPointerWidth, hourPointerColor, endXHour, endYHour);

    }

    // draw updated pointer(), used for each pointer
    private void drawPointer(long chronoValue, float pointerWidth, Color pointerColor, float pointerEndX, float pointerEndY){
        Line2D pointerLine = new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, pointerEndX, pointerEndY);
        AffineTransform atSecond =
                AffineTransform.getRotateInstance(
                        Math.toRadians(rotAngle * chronoValue),
                        pointerLine.getX1(), pointerLine.getY1());

        g2d.setStroke(new BasicStroke(pointerWidth));
        g2d.setColor(pointerColor);
        g2d.draw(atSecond.createTransformedShape(pointerLine));

        // g2d.drawLine((int) getPreferredSize().getWidth()/2, (int) getPreferredSize().getHeight()/2, pointerEndX, pointerEndY);
    }

    @Override
    public void update() {
        paintComponent(getGraphics());
    }

}
