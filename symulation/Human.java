/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symulation;

import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author michal
 */
public class Human  {
public Human()
{
    
}
public void drawHuman(double wight,double hight, GraphicsContext gc)
{
    gc.fillOval(30, hight-50, 10, 10);
    gc.setFill(Color.RED);
    gc.arcTo(10, 30, 30, 80, 10);
    
}

    
    
}
