package partnerCodeHerePlease;

import java.awt.Graphics2D;

import gui.components.Component;
import simonBilly.ProgressInterfaceBillyLuy;

public class Progress extends Component implements ProgressInterfaceBillyLuy {
	private static final int width = 100;
	private static final int height = 50;
	private boolean gameOver;
	private String round;
	private String sequence;
	
	public Progress(int x, int y, int w, int h) {
		super(60, 60, width, height);
	}

	@Override 
	public void update(Graphics2D arg0) {
		// TODO Auto-generated method stub
 
	}

}
