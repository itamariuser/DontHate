package model.policy;

import java.util.ArrayList;
import java.util.LinkedList;

import commons.Level;
import commons.Level2D;
import model.data.BlankSpace;
import model.data.Crate;
import model.data.GameObject;
import model.data.GoalPoint;
import model.data.MainCharacter;
import model.data.Position;
import model.data.Position2D;
import model.data.Wall;


/**
 * My implementation of a policy, fit for 2D levels
 * @author itamar sheffer
 *
 */
public class MySokobanPolicy implements SokobanPolicy { 
	Level2D level;
	
	
	public MySokobanPolicy() {
		this.level.setWinCondition(false);
		this.level=new Level2D();
	}
	
	public MySokobanPolicy(Level2D level) {
		this.level = level;
	}
	
	private Level2D moveObject(GameObject obj, Position2D dest)
	{
		//remove object from source point
		ArrayList<GameObject> temp=this.level.getGameObjectArrayOf(obj.getCurrentLocation());
		temp.remove(obj);
		
		if(this.level.getGameObjectArrayOf(obj.getCurrentLocation()).isEmpty())
		{
			
			//add a BlankSpace if no BlankSpace is in the source point
			temp.add(0,new BlankSpace(obj.getCurrentLocation()));
		}
		
		//put the new array (without obj) in the source point
		this.level.putInLayout(obj.getCurrentLocation(), temp);
		
		//change object's position to the destination point
		obj.setCurrentLocation(dest);
		
		//put the object in the destination point's array
		ArrayList<GameObject> arr= this.level.getGameObjectArrayOf(dest);
		arr.add(obj);
		
		//put the new dest array (without obj) in the destination point
		this.level.putInLayout(dest, arr);
		return this.level;
	}
	
	
	
	private boolean collisionBetween(GameObject moved,GameObject stat,String direction)
	{
		String cl=stat.getClass().toString();
		
		//check if moving a crate
		if(moved.getClass()==new Crate().getClass())
		{
			
			//check if moving on a wall
			if(cl.equals(new Wall().getClass().toString()))
				return true;
			
			//check if moving on a crate
			if(cl.equals(new Crate().getClass().toString()))
				return true;
			
			//if moving from goalPoint, decrease score by 1
			for (GameObject obj : this.level.getGameObjectArrayOf(moved.getCurrentLocation())) {
				if(obj instanceof GoalPoint)
				{
					this.level.setNumberOfGoals(this.level.getNumberOfGoals()-1);
				}
			}
			
			//if moving to goalPoint, increase score by 1
			for(GameObject obj: level.getGameObjectArrayOf(stat.getCurrentLocation())){
				if(obj instanceof GoalPoint)
				{
					this.level.setNumberOfGoals(this.level.getNumberOfGoals()+1);
					if(this.level.getNumberOfGoals()==0)
					{
						this.level.setWinCondition(true);
					}
				}
			}
			return false;
		}
		
		//check if moving a mainCharacter
		if(moved.getClass()==new MainCharacter().getClass())
		{
			
			//check if moving on a crate
			if(cl.equals(new Crate().getClass().toString()))
			{
				Position crateDest = this.getNextPos(stat, direction);
				ArrayList<GameObject> arrAtDest=this.level.getGameObjectArrayOf(crateDest);
				
				//check if there are collisions in the next position for the crate pushed (collision between crate at {next}and objects at {next->next})
				for (GameObject gameObject : arrAtDest) {
					if(collisionBetween(stat, gameObject,direction))
						return true;
				}
			}
			//check if moving on a wall
			if(cl.equals(new Wall().getClass().toString()))
			{
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	//return the next position in a given direction
	private Position2D getNextPos(GameObject obj,String direction)
	{
		int cX=obj.getCurrentLocation().getPositions().get(0);
		int cY=obj.getCurrentLocation().getPositions().get(1);
		Position2D nextPos= new Position2D(0,0);
		
		switch (direction.toLowerCase().trim()) {
		case "right":
			nextPos = new Position2D(cX+1, cY);
			break;
		case "left":
			nextPos = new Position2D(cX-1, cY);
			break;
		case "up":
			nextPos = new Position2D(cX, cY-1);
			break;
		case "down":
			nextPos = new Position2D(cX, cY+1);
			break;
		default:
			return null;
		}
		return nextPos;
	}
	
	public boolean moveMainCharacter(MainCharacter ch,String direction)
	{
		
		Position2D nextPos= getNextPos(ch, direction);
		ArrayList<GameObject> nextObjArray = this.level.getGameObjectArrayOf(nextPos);
		GameObject nextObj=null;
		boolean canMove=true;
		for (GameObject gameObject : nextObjArray) {
			nextObj=gameObject;
			
			//check collision between the character and the objects in the next position, if no collision, call the move function in model
			if(this.collisionBetween(ch,nextObj, direction))
			{
				canMove=false;
			}
		}
		
		if(canMove)
		{
			if (nextObj instanceof Crate) {
				this.moveObject(nextObj, getNextPos(nextObj, direction));
			}
			this.level=this.moveObject(ch, nextPos);
			this.level.increaseNumOfSteps();
			return true;
		}
		return false;

	}


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level2D level) {
		this.level = level;
	}
	
}
