package partnerCodeHerePlease;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
	public void update(Graphics2D g) {
		g = clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if(gameOver){
			g.setColor(Color.RED);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			String text = "Game Over";
			g.drawString(text, 40,20);
			g.drawString(sequence, 40, 40);
		}else{
			g.setColor(Color.pink);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawRect(0, 0, width-1, height-1);
			if(round !=null && sequence != null){
				g.drawString(round, 40, 20);
				g.drawString(sequence, 40, 40);
			}
		}
	}

}
