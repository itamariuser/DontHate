package model.policy;

import model.data.MainCharacter;
/**
 * Dictates how game objects interact in the level, the game's logic and physics
 * @author itamar sheffer
 *
 */
public interface SokobanPolicy {
	public boolean moveMainCharacter(MainCharacter ch,String direction);
}
