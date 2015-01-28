package fuzzypark.model;

public class Car {
	private double a;
	private double b;
	private double x;
	private double y;
	private double d;
	
	public Car(){
		setA(setB(setX(setY(0))));
		setD(5);
	}
	public Car(double a, double b, double x, double y ,double d)
	{
		this.setA(a);
		this.setB(b);
		this.setX(x);
		this.setY(y);
		this.setD(d);
	}
	public double getB() {
		return b;
	}
	public double setB(double b) {
		this.b = b;
		return b;
	}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getX() {
		return x;
	}
	public double setX(double x) {
		this.x = x;
		return x;
	}
	public double getY() {
		return y;
	}
	public double setY(double y) {
		this.y = y;
		return y;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public void doStep()
	{
		x = x + Math.sin((a+b)*Math.PI/180.0) - Math.sin(a*Math.PI/180.0) * Math.cos(b*Math.PI/180.0);
	    y = y - Math.cos((a+b)*Math.PI/180.0) - Math.sin(a*Math.PI/180.0) * Math.sin(b*Math.PI/180.0);
	    b = b - 180.0*Math.asin(2.0 * Math.sin(a*Math.PI/180.0) / d)/Math.PI;
	    if(b<-180)
	        b+=360;
	    else if(b>180)
	       b -= 360;
	}
}
