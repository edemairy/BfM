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

    public static int WIDTH = 70;
    public static int HEIGHT = WIDTH;

    public enum TYPE {
        INFANTRY, PANZER
    };

    public enum SIZE {
        ARMY, CORPS, DIVISION, BRIGADE, REGIMENT, BATALLION, COMPANY
    };

    public Unit(String nation, SIZE size, int combat, int movement, TYPE type, String id) {
        super();
        Color unitColor = null;
        if (nation.equals("GE")) {
          unitColor = Color.LIGHTGREEN;
        } else if (nation.equals("SU")) {
            unitColor = Color.INDIANRED;
        }
        Rectangle base = new Rectangle(WIDTH, HEIGHT, unitColor);
        getChildren().add(base);
        setUnitId(id);

        drawTypeRectangle();
        fillTypeRectangle(type);
        setCombatValue(combat);
        setMovementValue(movement);
    }

    public void drawTypeRectangle() {
        Rectangle typeRectangle = new Rectangle(0.4 * WIDTH, .25 * WIDTH, Color.RED);
        typeRectangle.setStroke(Color.BLACK);
        typeRectangle.setX(0.25 * WIDTH);
        typeRectangle.setY(0.22 * WIDTH);
        getChildren().add(typeRectangle);
    }

    public void fillTypeRectangle(TYPE type) {
        switch (type) {
            case PANZER:
                Ellipse tank = new Ellipse(0.18 * WIDTH, .12 * WIDTH);
                tank.setCenterX(.45 * WIDTH);
                tank.setCenterY(.35 * WIDTH);
                getChildren().add(tank);
                break;
            case INFANTRY:
                Line line1 = new Line(0.25 * WIDTH, 0.22 * WIDTH, 0.65 * WIDTH, 0.48 * WIDTH);
                Line line2 = new Line(0.25 * WIDTH, 0.48 * WIDTH, 0.65 * WIDTH, 0.22 * WIDTH);
                getChildren().addAll(line1, line2);
                break;
        }
    }

//    public void displayUnitSize() {
//       switch (size)
//    }

    public void setCombatValue(int value) {
        Text combatValue = new Text(Integer.toString(value));
        combatValue.setFont(Font.font("Times Bold", 0.22 * WIDTH));
        combatValue.setX(.17 * WIDTH);
        combatValue.setY(.77 * WIDTH);
        combatValue.setTextAlignment(TextAlignment.RIGHT);
        getChildren().add(combatValue);
    }

    public void setMovementValue(int value) {
        Text combatValue = new Text(Integer.toString(value));
        combatValue.setFont(Font.font("Times Bold", 0.22 * WIDTH));
        combatValue.setX(.54 * WIDTH);
        combatValue.setY(.77 * WIDTH);
        combatValue.setTextAlignment(TextAlignment.RIGHT);
        getChildren().add(combatValue);
    }

    public void setUnitId(String id) {
        Text combatValue = new Text(id);
        combatValue.setFont(Font.font("Times Bold", 0.22 * WIDTH));
        combatValue.getTransforms().add(new Rotate(-90));
        combatValue.getTransforms().add(new Translate(-0.94 * WIDTH, 0.94 * WIDTH));
        combatValue.setWrappingWidth(WIDTH);
        combatValue.setStroke(Color.BLUE);
        combatValue.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(combatValue);
    }

    private SIZE size;
}
