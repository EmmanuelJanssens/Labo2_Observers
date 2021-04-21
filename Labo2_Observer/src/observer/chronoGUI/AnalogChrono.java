package observer.chronoGUI;

import subject.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

abstract public class AnalogChrono extends ChronoPanel {

    private class Pointer
    {
        int X;
        int Y;
        int width;
        Color color;

        Pointer(int x, int y, int w, Color c)
        {
            X = x;
            Y = y;
            width = w;
            color = c;
        }
    }

    private final int PANEL_W = (int) getPreferredSize().getWidth()/2;
    private final Pointer SECONDS = new Pointer(PANEL_W, 40,1,Color.red);
    private final Pointer MINUTES = new Pointer(PANEL_W, 65,3,Color.blue);;
    private final Pointer HOURS = new Pointer(PANEL_W, 81,5,Color.black);;


    private final int LABEL_POS_X = PANEL_W;
    private final int LABEL_POS_Y = (int) getPreferredSize().getHeight()/2;

    // Rotation angle of a unit
    private final double ROT_ANGLE = 6; // 360 / 60 = 6

    protected AnalogChrono(Chrono chrono, String name ){
        super(chrono,name);
    }

    protected void drawPointersAndLabel(Graphics g){
        g.drawString(getLabelName(), LABEL_POS_X, LABEL_POS_Y);
        // TODO contrôler avec switchState pour update ou pas les pointers
        drawPointer((Graphics2D) g, getChrono().getSeconds(), SECONDS.width, SECONDS.color,
                SECONDS.X, SECONDS.Y);
        drawPointer((Graphics2D) g, getChrono().getMinutes(), MINUTES.width, MINUTES.color,
                MINUTES.X, MINUTES.Y);
        drawPointer((Graphics2D) g, getChrono().getHours() * 5, HOURS.width, HOURS.color,
                HOURS.X, HOURS.Y);
    }

    protected void drawPointer(Graphics2D g2d, long chronoValue, float pointerWidth, Color pointerColor,
                               float pointerEndX, float pointerEndY){
        g2d.setStroke(new BasicStroke(pointerWidth));
        g2d.setColor(pointerColor);

        Line2D pointerLine = new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, pointerEndX, pointerEndY);
        AffineTransform atSecond =
                AffineTransform.getRotateInstance(
                        Math.toRadians(ROT_ANGLE * chronoValue),
                        pointerLine.getX1(), pointerLine.getY1());
        g2d.draw(atSecond.createTransformedShape(pointerLine));
    }
}
