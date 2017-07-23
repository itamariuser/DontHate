package common;

import java.awt.Point;
import java.util.ArrayList;

import gameObjects.Position;

@SuppressWarnings("serial")
public class Position2D extends Position {
	
	protected Integer y;
	
	public Position2D() {
		this.x=0;
		this.y=0;
	}
	
	public Position2D(int x, int y) {
		this.x=x;
		this.y=y;
		
	}
	
	public Position2D(Point p)
	{
		this.x = p.x;
		this.y = p.y;
	}
	
	public static double getDistanceFromPosition2D(Position2D p1,Position2D p2)
	{
		return Math.sqrt(Math.pow(p1.x-p2.getX(), 2)+Math.pow(p1.y-p2.getY(), 2));
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public ArrayList<Integer> getPositions()
	{
		ArrayList<Integer> arr = super.getPositions();
		arr.add(this.y);
		return arr;
	}
	
	@Override
	public String toString() {
		return ("("+x+", "+y+")");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position2D)
		{
			Position2D posObj=(Position2D) obj;
			return (x.intValue()==posObj.x.intValue() && y.intValue()==posObj.y.intValue());
		}
		return false;
	}
	@Override
	public int hashCode() {
		return x.hashCode()+y.hashCode();
	}
	
}
