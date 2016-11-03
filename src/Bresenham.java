import java.applet.Applet;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by abduljama on 9/18/16.
 */
public class Bresenham extends Applet {

    // Initial point
    int x0,y0;

    // Endpoints
    int xn , yn ;

    // Delta X & Y
    double dx,dy;

    // p0
    double  pX;

    double  grad;

    int totalPoints;
    public  Bresenham (int  x0 , int y0 , int xn,  int yn ){
        this.x0=x0;
        this.y0=y0;
        this.xn=xn;
        this.yn=yn;
    }
    public Bresenham (){

    }



    public int OctantDetector ( int x0 , int y0 , int xn , int yn ) {
        dx = xn - x0;
        dy = yn - y0;
        double  grad= dy/dx;


        int octant = 0;
        if ( 0 < grad   &&  grad < 1 ){
            if ( x0 >= 0 &&  y0 >=0 && xn>= 0 && yn>0){
                System.out.println("Line is in the first Octant");
                octant= 1;
            }
            else if (x0 <= 0 && y0 <= 0 && xn<=0 && yn<=0 ){
                System.out.println("Line is in the fifth octant");
                octant = 5;
            }
        } else if ( grad > 1 ){
            if ( x0 >= 0 && y0 >= 0 &&  xn>=0 && yn>= 0 ){
                System.out.println("Line is in the 2nd  octant");
                octant= 2;
            }
            else if (x0 <= 0 && y0<=0 && xn<=0  && yn<=0 ){
                System.out.println("Line is in the 6th  octant");
                octant=6;
            }
        }else if (  grad < -1 ){
            if ( x0 <=0 && xn<= 0 && yn>=0 && y0>= 0){
                System.out.println("Line is in the 3rd  octant");
                octant=3;
            }
            else if ( x0 >=0 && xn>= 0 && yn<=0 && y0<= 0){
                System.out.println("Line is in the 7th  octant");
                octant=7;
            }
        }
        else if ( -1 <grad && grad < 0 ){
            if (x0 <= 0 && xn <=0 && yn>= 0 && yn >=0){
                octant=4;
                System.out.println("Line is in the 4th  octant");
            }
            else if (x0 >= 0 && xn >=0 && yn<= 0 && yn <=0){
                System.out.println("Line is in the 4th  octant");
                octant=8;
            }
        }
        else {
            System.out.println("Line is in more than one octant");
            octant=0;
        }
        return  octant;
    }

    public  void  coordinates (){
        System.out.println("Note: 1.Lines that only lay  the 1st Octant and Second Octant can be plotted\n " +
                " 2. Lines that are in more than octant can not be plotted \n");

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter  intial  X-cordinate: ");
        x0 = reader.nextInt();
        System.out.println("Enter  intial  Y-cordinate: ");
        y0 = reader.nextInt();
        System.out.println("Enter  final  X-cordinate: ");
        xn = reader.nextInt();
        System.out.println("Enter  final  Y-cordinate: ");
        yn= reader.nextInt();
    }

    public void paint(Graphics g) {
        x0= 0; y0= 0; xn= 200;yn= 400 ;
        g.fillOval(x0, y0, 5, 5);
        g.fillOval(xn, yn, 5, 5);
        dx = xn - x0;
        dy = yn - y0;

        if  ( Math.abs(xn) > Math.abs(x0))
            totalPoints = Math.abs(xn);
        else
            totalPoints=Math.abs(x0);

        grad = dy/dx;
        System.out.println(grad);
        if  (OctantDetector(x0,y0,xn,yn)==1  ) {
            // System.out.println("Gradient is between 0 <= m <= 1");
            pX= (2 * dy)-dx;
            System.out.println( "pX : " + pX + " X : " + x0 +" Y : " +y0 );
            for (int k = 1; k < totalPoints-1; k++) {
                if (pX <= 0) {
                    g.fillOval(x0++, y0, 5, 5);
                    pX = pX + (2 * dy);
                    System.out.println( "pX : " + pX + " X : " + x0 +" Y : " +y0 );
                } else {
                    g.fillOval(x0++, y0++, 5, 5);
                    pX = pX + (2 * (dy-dx));
                    System.out.println( "pX : " + pX + " X : " + x0 +" Y : " +y0 );
                }

            }
        } else if ( OctantDetector(x0,y0,xn,yn)== 2) {
            int x0=y0;int y0=x0; int x1=yn;  int y1=xn;
            int dy= y1-y0;
            int dx= x1-x0;
            pX= (2 * dy)-dx;
            System.out.println( "pX : " + pX + " X : " + x0 +" Y : " +y0 );
            for (int k = 1; k < x1+1; k++) {
                if (pX <= 0) {
                    x0++;
                    g.fillOval(y0, x0, 5, 5);
                    pX = pX + (2 * dy);
                    System.out.println( "pX : " + pX + " X : " + x0 +" Y : " +y0 );
                } else {
                    x0++; y0++;
                    g.fillOval(y0, x0, 5, 5);
                    pX = pX + (2 * (dy-dx));
                    System.out.println( "pX : " + pX + " X : " + x0 +" Y : " +y0 );
                }

            }

        }

        else if(OctantDetector(x0,y0,xn,yn)==4) {

            //     System.out.println("Gradient is between 0 > m >= -1");
            pX= (-2 * dy)-dx;
            System.out.println( "pX : " + pX + " X : " + xn +" Y : " +yn );
            for (int k = 1; k < totalPoints+1; k++) {
                if (pX >= 0) {
                    g.fillOval(xn++, yn, 5, 5);  // Cannot  be  plotted
                    pX = pX - (2 * dy);
                    System.out.println( "pX : " + pX + " X : " + xn +" Y : " +yn );
                } else {
                    g.fillOval(xn++, yn--, 5, 5);
                    pX = pX - (2 * (dy+dx));   // Cannot be  plotted
                    System.out.println( "pX : " + pX + " X : " + xn +" Y : " +yn );
                }
            }

        } else {
            System.out.println("TBD ");
        }
    }
    public  void main(String[] args) {
        Graphics g = getGraphics();
        Bresenham bresenham = new Bresenham();
        bresenham.paint(g);




    }


}