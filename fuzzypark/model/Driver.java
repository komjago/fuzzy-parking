package fuzzypark.model;

public class Driver {
	final public static double MIN_X = -1000.0;
	final public static double MIN_BETA =-180.0;
	final public static double MIN_ALPHA= -45.0;
	final public static double MAX_X= 1000.0;
	final public static double MAX_BETA= 180.0;
	final public static double MAX_ALPHA =45.0;
	final public static double X_Threshold= 100.0;
	private double UXmin,UXzero,UXmax;
	private double UBmin,UBzero,UBmax;

	double getUXmin(double x)
	{
		if(x<-X_Threshold)
	        return 1.0;
	    return (x<=0)?x/(-X_Threshold):0.0;
	}

	double getUXzero(double x)
	{
	    return 1.0 - Math.abs(x/MAX_X);
	}

	double getUXmax(double x)
	{
		if(x>X_Threshold)
	        return 1.0;
	    return (x>0)?x/X_Threshold:0.0;
	}

	double getUBmin(double beta)
	{
	    return (beta<=0)?beta/MIN_BETA:0.0;
	}

	double getUBzero(double beta)
	{
	    return 1.0 - Math.abs(beta/MAX_BETA);
	}

	double getUBmax(double beta)
	{
	    return (beta>0)?beta/MAX_BETA:0.0;
	}
	public double drive(double beta ,double x ){
		UXmin = getUXmin(x);
	    UXzero = getUXzero(x);
	    UXmax = getUXmax(x);

	    UBmin = getUBmin(beta);
	    UBzero = getUBzero(beta);
	    UBmax = getUBmax(beta);
	    
	    double newAlpha = 45.0 * ( UBmax*UXzero + UBzero*UXmax + UBmin*UXmin - ( UBmax*UXmax + UBzero*UXmin + UBmin*UXzero ) ) / ( (UXmax+UXzero+UXmin)*(UBmax+UBzero+UBmin));

	    return newAlpha;

	}

}
