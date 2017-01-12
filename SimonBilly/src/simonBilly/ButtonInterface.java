package simonBilly;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterface extends Clickable {

	void setColor(Color color);

	void setX(int i);

	void setY(int i);
	
	void setAction(Action a);

	void highlight();

	void dim();

	void update(Graphics2D g);

	

}
