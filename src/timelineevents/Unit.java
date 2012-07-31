/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timelineevents;

import java.awt.Transparency;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class Unit extends Group {

    public final int WIDTH = 35;
    public final int HEIGHT = WIDTH;

    public enum TYPE {

        INFANTRY, PANZER
    };

    public enum SIZE {

        ARMY, CORPS, DIVISION, BRIGADE, REGIMENT, BATALLION, COMPANY
    };

    public Unit(String nation, SIZE size, int combat, int movement, TYPE type, String id) {
        super();
        Rectangle base = new Rectangle(WIDTH, HEIGHT, Color.ROSYBROWN);
//        base.setStroke(Color.BLACK);
        getChildren().add(base);
        setUnitId(id);

        drawTypeRectangle();
        fillTypeRectangle(type);
        setCombatValue(combat);
        setMovementValue(movement);
    }

    public void drawTypeRectangle() {
        Rectangle typeRectangle = new Rectangle(14, 9, Color.RED);
        typeRectangle.setStroke(Color.BLACK);
        typeRectangle.setX(9);
        typeRectangle.setY(8);
        getChildren().add(typeRectangle);
    }

    public void fillTypeRectangle(TYPE type) {
        switch (type) {
            case PANZER:
                Ellipse tank = new Ellipse(6.5, 4.5);
                tank.setCenterX(16);
                tank.setCenterY(12.5);
                getChildren().add(tank);
                break;
            case INFANTRY:
                Line line1 = new Line(9, 8, 23, 17);
                Line line2 = new Line(9, 17, 23, 8);
                getChildren().addAll(line1, line2);
                break;
        }
    }

    public void setCombatValue(int value) {
        Text combatValue = new Text(Integer.toString(value));
        combatValue.setFont(Font.font("Times", 8));
        combatValue.setX(6);
        combatValue.setY(27);
        combatValue.setTextAlignment(TextAlignment.RIGHT);
        getChildren().add(combatValue);
    }

    public void setMovementValue(int value) {
        Text combatValue = new Text(Integer.toString(value));
        combatValue.setFont(Font.font("Times", 8));
        combatValue.setX(19);
        combatValue.setY(27);
        combatValue.setTextAlignment(TextAlignment.RIGHT);
        getChildren().add(combatValue);
    }

    public void setUnitId(String id) {
        Text combatValue = new Text(id);
        combatValue.setFont(Font.font("Times", 8));
        combatValue.getTransforms().add(new Rotate(-90));
        combatValue.getTransforms().add(new Translate(-33, 33));
        combatValue.setWrappingWidth(WIDTH);
        combatValue.setStroke(Color.BLUE);
        combatValue.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(combatValue);
    }
}
