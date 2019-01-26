
/* *************************************************
* Project: Ch 14.17 Hangman
* Dev: Justin Mangan
* Date 6 January 2018
* Task: Creates a traditional stick-figure "hangman"
*****************************************************/

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ch14Hangman extends Application {
    
    // Pane dimension variables 
    private static double width = 600;
    private static double height = 600;

    @Override
    public void start(Stage primaryStage) {
    
        // Creates the pane and arrayList for nodes
        Pane pane = new Pane();
        ObservableList<Node> list = pane.getChildren();
        
        // The Pane must be put in the Scene first if the parameters for the body parts
        // access the pane dimensions via methods pane.getHeight() or pane.getWidth()
//        primaryStage.setScene(new Scene(pane, WIDTH, HEIGHT));
        
        // creates the gallows ***might be shorter code as a PolyLine
        Arc base = new Arc(width / 4, height - height / 12, width / 5, height / 12, 0, 180);
        base.setFill(Color.TRANSPARENT);
        base.setStroke(Color.BLACK);
        base.setType(ArcType.OPEN);
        Line pole = new Line(base.getCenterX(), base.getCenterY() - base.getRadiusY(), base.getCenterX(), height / 25);
        Line arm = new Line(pole.getEndX(), pole.getEndY(), width / 1.5, pole.getEndY());
        Line drop = new Line(arm.getEndX(), arm.getEndY(), arm.getEndX(), height / 6);
        list.addAll(base, pole, arm, drop);

        // Creates the head 
        double radius = width / 15;
        Circle c = new Circle(arm.getEndX(), height / 6 + radius, radius);
        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.BLACK);
        list.add(c);
        
        // Creates body line ***the entire body could be PolyLine 
        Line body = new Line(c.getCenterX(), c.getCenterY() + c.getRadius(), width / 1.5, height / 1.6);
        list.add(body);
        
        // Creates both arms
        Line leftArm = new Line(body.getStartX(), body.getStartY(), width / 2, height / 2.3);
        Line rightArm = new Line(body.getStartX(), body.getStartY(), width / 1.2, height / 2.3);
        list.addAll(leftArm, rightArm);
        
        // Creates both legs
        Line leftLeg = new Line(body.getEndX(), body.getEndY(), width / 2, height / 1.3);
        Line rightLeg = new Line(body.getEndX(), body.getEndY(), width / 1.2, height / 1.3);
        list.addAll(leftLeg, rightLeg);
        
        // Add pane to scene
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(new Scene(pane, width, height));   
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}