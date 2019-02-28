/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arch.v2.pkg0.control;

import arch.v2.pkg0.output.OutputFXMLController;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import symulation.*;



/**
 *
 * @author michal
 */
public class FXMLDocumentController implements Initializable {
    @FXML
   TextField TfAngle;
    @FXML
    TextField TfV0;
    @FXML
    TextField TfVWind;
    @FXML
    TextField TfMass;
    @FXML
    ComboBox<String> CBWindDirection;
    @FXML
    TextField TfH0;
    
    //inne zmienne
    Calculation calc;
    Point point;
    Point maxPoint;
    ArrayList<Point> pointsList; 
    
    
    
    //zmienne początkowe 
    double angle=45.00;
    double v0=30.00;
    double vWind=2.00;
    double m=0.5;
    int windDirection=1;
    double h0=1.2;
    double x;
    double y;
    double t;
    boolean firstTime=true;
    OutputFXMLController controller;
    Symulation symulation;
 
 
    
    @FXML
    private void shootButtonClicked(ActionEvent event)
    {
        t=0;
        x=0;
        y=h0;
        angle=Double.valueOf(TfAngle.getText());
        v0=Double.valueOf(TfV0.getText());
        vWind=Double.valueOf(TfVWind.getText());
        m=Double.valueOf(TfMass.getText());
        h0=Double.valueOf(TfH0.getText());
        windDirection=changeDirection();
       // System.out.println("Wartości: "+angle+" " +v0+ " " +vWind);
        calc=new Calculation(angle,v0, vWind, m,windDirection, h0);
        savePoints();
        for(int i=0; i< pointsList.size();i++)
        {
            System.out.println("Punkt X:" + pointsList.get(i).x+" Y:" + pointsList.get(i).y+" Czas:"+pointsList.get(i).t);
        }
        if(firstTime)
        {
         
                outputShow();
                symulationShow();
                
            
}
        else
        {
            changeOutputParameter();
        }
    }
   void savePoints()
    {
        pointsList = new ArrayList<>();
        while(y>=0)
            {
               y=this.calc.calcY(t);
               x=this.calc.calcX(t);
               t+=0.01;
               point=new Point(x,y,t);
               pointsList.add(point);
            }
            
            maxPoint=this.calc.getMax(pointsList);
            System.out.println("Wysokość maksywalna: "+maxPoint.y +" osiągnieta na gługości: "+maxPoint.x + "Po czasie: "+maxPoint.t);
           // calc.showAllPoints(pointsList);
            Point lastPoint= pointsList.get(pointsList.size()-1);
            System.out.println("Zasięg strzału: "+lastPoint.x+"Czas lotu: "+lastPoint.t+ "Czas wznoszenia: "+(lastPoint.t-maxPoint.t)+" Czas opadania: "+(lastPoint.t-(lastPoint.t-maxPoint.t)));
           calc.maxHeightX=maxPoint.x;
           calc.maxHeightY=maxPoint.y;
           calc.distanse=lastPoint.x;
           calc.timeTotal=lastPoint.t;
           calc.timeAscent=lastPoint.t-maxPoint.t;
           calc.timeDescent=lastPoint.t-(lastPoint.t-maxPoint.t);
    }
   
   private void symulationShow()
   {
      // FXMLLoader loader= new FXMLLoader();
      // loader.setLocation(getClass().getResource("/arch/simulation/Sample.fxml"));
     //  Stage stage=new Stage();
       //stage.setScene(new Scene((Parent) loader.load()));
      // stage.setResizable(false);
//       symulation=loader.getController();
//      // symulation.setPrimaryStage(stage);
//       symulation.setLoader(loader);
//        try {
//            symulation.draw();
//            //stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
       symulation = new Symulation();
       symulation.setPointsList(pointsList);
        try {
            symulation.draw();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   private void outputShow()
   {
       try{
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/arch/v2/pkg0/output/OutputFXML.fxml"));
           //Parent root1 =loader; //FXMLLoader.load(getClass().getResource("/arch/v2/pkg0/output/OutputFXML.fxml"));
           Stage stage = new Stage();
                 
                stage.setScene(new Scene((Parent) loader.load()));  
                stage.setResizable(false);
                stage.setTitle("Wyniki");
                stage.setX(300);
                stage.setY(800);
           controller=loader.getController();
                changeOutputParameter();
                stage.show();
               
       } catch(IOException e) {
           e.printStackTrace();
          }
        
   }
   
  
   
   private String setDecimalFormat(double l)
   {
       java.text.DecimalFormat df= new java.text.DecimalFormat("0.00");
       
       String a=df.format(l);
       return a;
   }
    
    @FXML
    private void cbWindDirectionChange(ActionEvent event)
    {
        if(CBWindDirection.getSelectionModel().getSelectedItem().equals("W twarz"))
        {
            windDirection=1;
        }
        else{ 
            windDirection=-1;
        }
    }
    private int changeDirection()
    {
         if(CBWindDirection.getSelectionModel().getSelectedItem().equals("W twarz"))
        {
            return 1;
        }
        else{ 
            return -1;
        }
    }
    
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "W plecy",
        "W twarz"
    );
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       String value;

//        
        
         CBWindDirection.setItems(options);
         TfAngle.setText(String.valueOf(40));
         TfH0.setText(String.valueOf(1.2));
         TfVWind.setText(String.valueOf(2));
         TfMass.setText(String.valueOf(0.3));
        TfV0.setText(String.valueOf(35));
         CBWindDirection.getSelectionModel().selectFirst();
         
         
    }   
    private void changeOutputParameter()
    {
           controller.setTotalTime(setDecimalFormat(calc.timeTotal));
           controller.setDescendTime(setDecimalFormat(calc.timeDescent));
           controller.setAscendTime(setDecimalFormat(calc.timeAscent));
           controller.setMaxHx(setDecimalFormat(calc.maxHeightX));
           controller.setMaxHy(setDecimalFormat(calc.maxHeightY));
           controller.setMaxX(setDecimalFormat(calc.distanse));
    }
    
}
