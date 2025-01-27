/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package timelineevents;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A sample that demonstrates events triggered during timeline play. The attacker changes its radius in a linear fashion during each key frame and randomly
 * jumps to a new location along the x coordinate at the end of the key frame.
 *
 * @see javafx.animation.KeyFrame
 * @see javafx.animation.KeyValue
 * @see javafx.animation.Timeline
 * @see javafx.util.Duration
 * @see javafx.event.ActionEvent
 * @see javafx.event.EventHandler
 */
public class TimelineEvents extends Application {
    //main timeline

    private Timeline timeline;
    private AnimationTimer timer;
    //variable for storing actual frame
    private Integer i = 0;
    final int COUNTER_WIDTH = 20;
    final int HSPACE = 10;
    final int COUNTER_HEIGHT = 10;
    final int VSPACE = 5;

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 2600, 1000));

        final Unit defender = new Unit("SU", Unit.SIZE.ARMY, 3,3,Unit.TYPE.INFANTRY, "third");
        defender.setTranslateX(Unit.WIDTH * 2);
        defender.setTranslateY(Unit.WIDTH * 2);
        root.getChildren().add(defender);

        final Unit defender2 = new Unit("SU", Unit.SIZE.ARMY, 4,3,Unit.TYPE.INFANTRY, "fourth");
        defender2.setTranslateX(Unit.WIDTH * 3);
        defender2.setTranslateY(Unit.WIDTH * 3);
        root.getChildren().add(defender2);

        //create a attacker with effect
        final Unit attacker = new Unit("GE", Unit.SIZE.ARMY, 12,6,Unit.TYPE.PANZER, "XLV");
//        attacker.setEffect(new Lighting());
        attacker.setTranslateX(Unit.WIDTH * 2);
        attacker.setTranslateY(Unit.WIDTH * 3);
        //create a layout for attacker with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().add(attacker);
//        stack.getChildren().addAll(new Rectangle(100,100,Color.BLUE), new Label("Go!"));
//        stack.setLayoutX(defender.getTranslateX() - (COUNTER_WIDTH + HSPACE));
//        stack.setLayoutY(defender.getTranslateY());

        //create a timeline for moving the attacker

        timeline = new Timeline();
        timeline.setCycleCount(10);

        //one can add a specific action when each frame is started. There are one or more frames during
        // executing one KeyFrame depending on set Interpolator.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
//                text.setText(i.toString());
                i++;
            }
        };

        //create a keyValue with factory: scaling the attacker 2times
        KeyValue keyValueX = new KeyValue(stack.translateXProperty(), COUNTER_WIDTH + HSPACE);
        KeyValue keyValueY = new KeyValue(stack.translateYProperty(), 0);

        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.seconds(1);
        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stack.setTranslateX(java.lang.Math.random() * 200);
                //reset attacker
                i = 0;
            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        root.getChildren().add(stack);
    }

    public void play() {
        timeline.play();
        timer.start();
    }

    @Override
    public void stop() {
        timeline.stop();
        timer.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be launched through
     * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
