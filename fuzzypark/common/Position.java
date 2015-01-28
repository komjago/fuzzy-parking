package fuzzypark.common;

/**
 * Klasa przechowuje współrzędne
 * @author J.Komjago
 *
 */
public class Position {
	public static final Position ZERO = new Position(0,0);
	private int xCoord;
	private int yCoord;
	/**
	 * konstruktor
	 */
	public Position(final int x,final int y){
		xCoord=x;
		yCoord=y;
	}
	/**
	 * konstruktor kopiujący
	 */
	public Position(Position pos) {
		xCoord=pos.getX();
		yCoord=pos.getY();
	}
	
	/**
	 * @return współrzędna x
	 */
	public int getX() {
		return xCoord;
	}
	/**
	 * @param xCoord
	 */
	public void setX (final int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	 * @return współrzędna y
	 */
	public int getY() {
		return yCoord;
	}
	/**
	 * @param yCoord
	 */
	public void setY(final int yCoord) {
		this.yCoord = yCoord;
	}
	@Override
	public boolean equals(Object o){
		Position pos=(Position)o;
		return (this.xCoord==pos.getX()&&this.yCoord==pos.getY());
	}
	/**
	 * @param p
	 * @return 
	 */
	public void addCoords(Position p){
		xCoord += p.getX();
		yCoord += p.getY();
	}
	public Position invert(){
		return new Position(-xCoord,-yCoord);
	}
}

