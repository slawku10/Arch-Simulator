/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arch.v2.pkg0.output;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author michal
 */
public class OutputFXMLController implements Initializable {
    @FXML
    TextField TTotal;
    @FXML
    TextField TAsc;
    @FXML
    TextField TDesc;
    @FXML
    TextField MaxHy;
    @FXML
    TextField MaxHx;
    @FXML
    TextField MaxX;

public void setTotalTime(String t)
{
    TTotal.setText(t);
}

public void setAscendTime(String t)
{
    TAsc.setText(t);
}

public void setDescendTime(String t)
{
    TDesc.setText(t);
}

public void setMaxHy(String t)
{
    MaxHy.setText(t);
}
public void setMaxHx(String t)
{
    MaxHx.setText(t);
}
public void setMaxX(String t)
{
    MaxX.setText(t);
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
}
