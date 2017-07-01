package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

import model.data.GameObject;
import model.data.MainCharacter;
import model.data.Position;
import model.data.Position2D;


/**
 * A level fit for 2D games
 * @author itamar sheffer
 *
 */
@XmlRootElement
@SuppressWarnings("serial")
public class Level2D extends Level implements Serializable {
	
	
	protected HashMap<Position2D, ArrayList<GameObject>> positionObjectLayout;
	
	public int getNumberOfGoals() {
		return numberOfGoals;
	}


	public void setNumberOfGoals(int numberOfGoals) {
		this.numberOfGoals = numberOfGoals;
	}

	public Level2D() {
		super();
		this.positionObjectLayout = new HashMap<Position2D, ArrayList<GameObject>>();
		this.objReferences = new ArrayList<GameObject>();
	}
	
	
	public ArrayList<GameObject> getGameObjectArrayOf(Position pos1)
	{
		ArrayList<GameObject> temp=null;
		for (Position2D pos : positionObjectLayout.keySet()) {
			if(pos.getX()==pos1.getPositions().get(0)&&pos.getY()==pos1.getPositions().get(1))
			{
				temp= positionObjectLayout.get(pos);
			}
		}
		return temp;
	}
	
	public boolean putInLayout(Position pos1,ArrayList<GameObject> arr1)
	{
		for (Position2D pos : positionObjectLayout.keySet()) {
			if(pos.getX()==pos1.getPositions().get(0)&&pos.getY()==pos1.getPositions().get(1))
			{
				this.positionObjectLayout.put(pos, arr1);
				return true;
			}
		}
		return false;
	}
	
	

	public MainCharacter getMainCharacter() throws Exception
	{
		for (GameObject gameObject : objReferences) {
			if(gameObject instanceof MainCharacter)
			{
				return (MainCharacter)gameObject;
			}
		}
		throw new Exception("No main character in level, please add a main character");
	}
	
	
	
	public void putInLayoutZ(Position2D key, ArrayList<GameObject> value)
	{
		ArrayList<GameObject> arr = new ArrayList<GameObject>();
		for (GameObject gameObject : value) {
			arr.add(gameObject);
		}
		this.positionObjectLayout.put(key, value);
	}
	
	
	public void addToObjRef(GameObject lmao)
	{
		this.objReferences.add(lmao);
	}
	

	public int getDifficulty() {
		return difficulty;
	}
	
	

	public ArrayList<GameObject> getObjReferences() {
		return objReferences;
	}

	public void setObjReferences(ArrayList<GameObject> objReferences) {
		this.objReferences = objReferences;
	}

	public HashMap<Position2D, ArrayList<GameObject>> getPositionObjectLayout() {
		return positionObjectLayout;
	}

	public void setPositionObjectLayout(HashMap<Position2D, ArrayList<GameObject>> positionObjectLayout) {
		this.positionObjectLayout = positionObjectLayout;
	}
}
