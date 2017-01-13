package partnerCodeHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import gui.components.Action;
import simonBilly.ButtonInterface;

public class Button implements ButtonInterface {
	
	private static final int w = 50;
	private static final int h = 50;
	private Action action;
	private Color color;
	private Color displayColor;
	private boolean highlight;
	
	public Button() {
		super(0,0,w,h);
	}

	@Override
	public void act() {
		action.act();
	}
	@Override
	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+w/2), 2)+Math.pow(y-(getY()+w/2), 2));
		return distance < w/2;
	}
	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, w, h);
		g.setColor(Color.black);
		g.drawOval(0, 0, w-1, h-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			
			int s = (int)(5/8.0 * w);
			int t = (int)(1.0/5*h)+4;
			p.addPoint(s-4, t-4);
			p.addPoint(s+7, t-2);
			p.addPoint(s+10, t);
			p.addPoint(s+14, t+10);
			p.addPoint(s+12, t+14);
			p.addPoint(s+8, t+3);
			g.fill(p);
		}
	}
	public void setColor(Color color) {
		this.color = color;
		displayColor = color;
		update();
	}
	public void setAction(Action a) {
		this.action = a;

	}
	public void highlight() {
		if(color != null) displayColor = color;
		highlight = true;
		update();
	}
	public void dim() {
		displayColor = Color.gray;
		highlight = false;
		update();
	}
	
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setX(int i) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setY(int i) {
		// TODO Auto-generated method stub

	}
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null; 
	}
	@Override
	public boolean isAnimate() {
		// TODO Auto-generated method stub
		return false;
	} 
}
