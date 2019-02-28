/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arch.v2.pkg0.control;

//import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author michal
 */
public class Calculation {
    //zmienne początkowe 
    double angle;
    double v0;
    double vWind;
    double m;
    int windDirection=1;
    double h0;
    
    
    //zmienne pomocnicze
    double gamma;
    double k=0.5;
    static double g=9.8;
    double v0x;
    double v0y;
    double x;
    double y;
    double dif;
    
    //zienne wyjściowe
    double distanse;
    double maxHeightX;
    double maxHeightY;
    double timeTotal;
    double timeAscent;
    double timeDescent;


    
    
    
    
    public Calculation(double angle,double v0, double vWind, double m,int windDirection,double h0)
    {
        this.angle=angle;
        this.v0=v0;
        this.m=m;
        this.vWind=vWind;
        this.windDirection=windDirection;
        this.h0=h0;
        double a=cos(angle);
        
        this.gamma=k/m;
        
        this.v0y=this.v0*sin(angle);
        this.v0x=this.v0*(1-(sin(angle)*sin(angle)));
    }
    //główne funkcje liczące
    public double calcX(double t)
    {
        double z=(1-Math.pow(Math.E,(-gamma*t)));
        double q=(1/gamma)*(v0x-(vWind*windDirection));
        double d=z*q;
        x=d+(vWind*windDirection)*t;    
        return x;
    }
    
    public double calcY(double t)
    {
        y=((v0y/gamma)+(g/(gamma*gamma)))*(1-Math.pow(Math.E, (-gamma*t)))-(g*t/gamma)+h0;
        return y;
    }
    
   //funkcja wyliczająca maxymalną wysokość
    public Point getMax(ArrayList<Point> points)
    {
        Point p= new Point(0,0,0);
        for(int i=0; i<=points.size();i++)
        {
            if(i+1<points.size())
                if(points.get(i).y>points.get(i+1).y)
                {
                    p.setX(points.get(i).x);
                    p.setY(points.get(i).y);
                    p.setT(points.get(i).t);
                    break;
                }                   
        }
        return p;
    }
    public void correctX(ArrayList<Point> points)
    {
        dif=getDif(points);
        double d;
        Random r= new Random();
        double temp;
        for (int i=0;i<points.size();i++)
        {
           if(i+1<points.size())
           {
               if(points.get(i).x>points.get(i+1).x)
               {
                    d=r.nextDouble()*0.05;
                   temp=points.get(i).x+(dif+d);
                   points.set(i+1, new Point(temp,points.get(i+1).y,points.get(i+1).t));
               }
           }
        }
    }
    
    public double getDif(ArrayList<Point> points)
    {
        double d;
        double x1;
        double x2;
        
        for (int i=0;i<points.size();i++)
        {
            if(i+1<points.size())
            {
                if(points.get(i).x>points.get(i+1).x)
                {
                    x1=points.get(i).x;
                    x2=points.get(i+1).x;
                    d = x1-x2;
                    return d;
                }
                
            }
        }
        return 0;
    }
    
    
    //funkcja pokazująca wszystkie punkty
    public void showAllPoints(ArrayList<Point> points)
    {
        for(int i=0; i<points.size();i++)
        {
          System.out.println("x: "+points.get(i).x+" y: "+points.get(i).y);                
        }
    }
    
    

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getV0() {
        return v0;
    }

    public void setV0(double v0) {
        this.v0 = v0;
    }

    public double getvWind() {
        return vWind;
    }

    public void setvWind(double vWind) {
        this.vWind = vWind;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public double getH0() {
        return h0;
    }

    public void setH0(double h0) {
        this.h0 = h0;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public static double getG() {
        return g;
    }

    public static void setG(double g) {
        Calculation.g = g;
    }

    public double getV0x() {
        return v0x;
    }

    public void setV0x(double v0x) {
        this.v0x = v0x;
    }

    public double getV0y() {
        return v0y;
    }

    public void setV0y(double v0y) {
        this.v0y = v0y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistanse() {
        return distanse;
    }

    public void setDistanse(double distanse) {
        this.distanse = distanse;
    }

    public double getMaxHeightX() {
        return maxHeightX;
    }

    public void setMaxHeightX(double maxHeightX) {
        this.maxHeightX = maxHeightX;
    }

    public double getMaxHeightY() {
        return maxHeightY;
    }

    public void setMaxHeightY(double maxHeightY) {
        this.maxHeightY = maxHeightY;
    }

    public double getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(double timeTotal) {
        this.timeTotal = timeTotal;
    }

    public double getTimeAscent() {
        return timeAscent;
    }

    public void setTimeAscent(double timeAscent) {
        this.timeAscent = timeAscent;
    }

    public double getTimieDescent() {
        return timeDescent;
    }

    public void setTimieDescent(double timieDescent) {
        this.timeDescent = timieDescent;
    }
    
    
}
