package observer.chronoGUI;

import subject.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

abstract public class AnalogChrono extends ChronoPanel {

    // Pointers end position (the moving position)
    private int SECOND_POINTER_END_X = (int) getPreferredSize().getWidth()/2;
    private int SECOND_POINTER_END_Y = 40;
    private int MINUTE_POINTER_END_X = (int) getPreferredSize().getWidth()/2;
    private int MINUTE_POINTER_END_Y = 65;
    private int HOUR_POINTER_END_X = (int) getPreferredSize().getWidth()/2;
    private int HOUR_POINTER_END_Y = 81;

    // Pointers line stroke width
    private final int SECOND_POINTER_WIDTH = 1;
    private final int MINUTE_POINTER_WIDTH = 3;
    private final int HOUR_POINTER_WIDTH = 5;

    // Pointers color
    private final Color SECOND_POINTER_COLOR = Color.RED;
    private final Color MINUTE_POINTER_COLOR = Color.BLUE;
    private final Color HOUR_POINTER_COLOR = Color.BLACK;

    private final int LABEL_POS_X = (int) getPreferredSize().getWidth()/2;
    private final int LABEL_POS_Y = (int) getPreferredSize().getHeight()/2;

    // Rotation angle of a unit
    private final double rotAngle = 6; // 360 / 60 = 6

    protected AnalogChrono(Chrono chrono, String name ){
        super(chrono,name);
    }

    protected void drawPointersAndLabel(Graphics g){
        g.drawString(getLabelName(), LABEL_POS_X, LABEL_POS_Y);
        // TODO contrôler avec switchState pour update ou pas les pointers
        drawPointer((Graphics2D) g, getChrono().getSeconds(), SECOND_POINTER_WIDTH, SECOND_POINTER_COLOR,
                SECOND_POINTER_END_X, SECOND_POINTER_END_Y);
        drawPointer((Graphics2D) g, getChrono().getMinutes(), MINUTE_POINTER_WIDTH, MINUTE_POINTER_COLOR,
                MINUTE_POINTER_END_X, MINUTE_POINTER_END_Y);
        drawPointer((Graphics2D) g, getChrono().getHours(), HOUR_POINTER_WIDTH, HOUR_POINTER_COLOR,
                HOUR_POINTER_END_X, HOUR_POINTER_END_Y);
    }

    protected void drawPointer(Graphics2D g2d, long chronoValue, float pointerWidth, Color pointerColor,
                               float pointerEndX, float pointerEndY){
        g2d.setStroke(new BasicStroke(pointerWidth));
        g2d.setColor(pointerColor);

        Line2D pointerLine = new Line2D.Float(this.getWidth()/2f, this.getHeight()/2f, pointerEndX, pointerEndY);
        AffineTransform atSecond =
                AffineTransform.getRotateInstance(
                        Math.toRadians(rotAngle * chronoValue),
                        pointerLine.getX1(), pointerLine.getY1());
        g2d.draw(atSecond.createTransformedShape(pointerLine));
    }
}
