/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symulation;

import arch.v2.pkg0.control.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
//import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

/**
 *
 * @author michal
 */



public class Symulation implements Initializable {

        
    private Stage primaryStage;
    private ArrayList<Point> pointsList;

    public ArrayList<Point> getPointsList() {
        return pointsList;
    }

    public void setPointsList(ArrayList<Point> pointsList) {
        this.pointsList = pointsList;
    }

    
    

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    
    public void draw() throws IOException
    {

                List<TrackProbe> lista = new ArrayList<TrackProbe>();
		List<Line> punkty = new ArrayList<Line>();
		List<LineTo> road = new ArrayList<LineTo>();
                
                List<Path> animacje = new ArrayList<Path>();
                List<PathTransition> animation = new ArrayList<PathTransition>();
                List<RotateTransition> rotate =new ArrayList<RotateTransition>();
                double czasAnimacji;
                int skala;
                int max=(int)pointsList.get(0).getX();;
                
                for (int j=0; j<pointsList.size(); j++){

                       if (max<(int)pointsList.get(j).getX()){
                           max=(int)pointsList.get(j).getX();
                       }
                   
                   lista.add(new TrackProbe(pointsList.get(j).getX(), pointsList.get(j).getY(), pointsList.get(j).getT()));
               }
	
                Line arrow = new Line(35, 637, 50, 637);
                arrow.setStroke(Paint.valueOf("red"));
		Line arrowK1 = new Line(50, 637, 47, 640);
                arrowK1.setStroke(Paint.valueOf("red"));
		Line arrowK2 = new Line(50, 637, 47, 634);
                arrowK2.setStroke(Paint.valueOf("red"));
                Group group = new Group(arrow) ;
                group.getChildren().add(arrowK1);
                group.getChildren().add(arrowK2);
                arrow.setStrokeWidth(2.2);
                
               
                
                primaryStage= new Stage();
                Pane root = (Pane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene = new Scene(root,1300,700);
                scene.getStylesheets().add("symulation/application.css");
		primaryStage.setScene(scene);
                primaryStage.setTitle("Animacja lotu strzaly");
                
		try{
//                Budowa animacji -rejestrowanie drogi krzywej
                Path path = new Path();
		MoveTo moveTo = new MoveTo();
		moveTo.setX(45.0f);
		moveTo.setY(637.0f);
		//wysuwanie strzały z łuku
		HLineTo hLineTo = new HLineTo();
		hLineTo.setX(50.0f);

		path.getElements().add(moveTo);
		path.getElements().add(hLineTo);
                PathTransition ruch = new PathTransition(Duration.ONE, path, group);
                ruch.play();

			for (int i = 0; i < lista.size(); i++) {
			punkty.add(new Line(41*(lista.get(i).posisionX)+50, 678-(lista.get(i).posisionY/lista.get(0).posisionY*41), 41*(lista.get(i).posisionX)+50, 678-(lista.get(i).posisionY/lista.get(0).posisionY*41)));
				punkty.get(i).setId(String.valueOf(lista.get(i).time));
				punkty.get(i).setStrokeWidth(2.5);
                                
                                road.add(new LineTo(punkty.get(i).getEndX(), punkty.get(i).getEndY()));
                                animacje.add(new Path());

                                if (i==0){

                                    animacje.get(i).getElements().add(new MoveTo(55.0f, 637.0f));
                                    animacje.get(i).getElements().add(road.get(i));
                                    
                                    animation.add(new PathTransition(Duration.ONE, animacje.get(i), group));
                                    animation.get(i).setDelay(Duration.ONE);
                                    
                                    rotate.add(new RotateTransition(Duration.seconds(0.5), group));
                                    rotate.get(i).setDelay(Duration.seconds(0.1));
                                    //rotate.get(i).setByAngle(-Math.toDegrees(Math.atan((lista.get(i).posisionY-0.0)/(lista.get(i).posisionX-0))));
                                }
                                else{
                                    czasAnimacji = Double.valueOf(punkty.get(i).getId())- Double.valueOf(punkty.get(i-1).getId());
                                    
                                    animacje.get(i).getElements().add(new MoveTo(punkty.get(i-1).getStartX(), punkty.get(i-1).getStartY()));
                                    animacje.get(i).getElements().add(road.get(i));
                                    
                                    animation.add(new PathTransition(Duration.seconds(czasAnimacji), animacje.get(i), group));
                                    czasAnimacji = Double.valueOf(punkty.get(i).getId())+5.0d;
                                    animation.get(i).setDelay(Duration.seconds(czasAnimacji));
                                    
                                    rotate.add(new RotateTransition(Duration.ONE, group));
                                    rotate.get(i).setDelay(Duration.seconds(czasAnimacji));
                                    if(punkty.get(i).getEndX()>punkty.get(i-1).getEndX()){
                                        rotate.get(i).setByAngle(Math.toDegrees(Math.atan((punkty.get(i).getStartY()-punkty.get(i-1).getStartY())/(punkty.get(i).getStartX()-punkty.get(i-1).getStartX()))));
                                    }
                                    else{
                                        rotate.get(i).setByAngle(-1.5*Math.toDegrees(Math.atan((punkty.get(i).getStartY()-punkty.get(i-1).getStartY())/(punkty.get(i).getStartX()-punkty.get(i-1).getStartX()))));
                                    }
                                }
                                
				punkty.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
                                                int indexSeparatorInIDString;
                                                String iDString;
						System.out.println("X ="+(event.getX()-50)/41);
						System.out.println("Y ="+(678-event.getY())/41);
                                                
                                               indexSeparatorInIDString= event.getSource().toString().indexOf(0x002c);
						System.out.println("Czas: "+event.getSource().toString().substring(8, indexSeparatorInIDString));						
					}
					
					
				});

                              
                              animation.get(i).play();
                              if (i==1){
                                 // ruch.play();
                              }
                              rotate.get(i).play();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

                
		root.getChildren().add(group);
		//dodawanie punktów do formatki
		for(int i=0; i<punkty.size(); i++){
			root.getChildren().add(punkty.get(i));
		} 
                primaryStage.show();
    }
 
    public Symulation()  
    {
         pointsList= new ArrayList<Point>();
            
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    
       
}
