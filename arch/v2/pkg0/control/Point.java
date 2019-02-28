/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arch.v2.pkg0.control;

/**
 *
 * @author michal
 * */
public class Point {
   //przechowywanie położenia x i y względem czasu
    double x;
    double y;
    double t;
    
    public Point(double xw,double yw, double tw){
        this.x=xw;
        this.y=yw;
        this.t=tw;
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
    
    public void setX(double xw)
    {
        this.x=xw;
    }
    
    public void setY(double yw)
    {
        this.y=yw;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
    

    
}