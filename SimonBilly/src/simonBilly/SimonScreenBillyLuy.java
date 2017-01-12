package simonBilly;

import java.awt.Color;
import java.util.ArrayList;

import gui.components.Action;
import gui.components.ClickableScreen;
import gui.components.TextLabel;
import gui.components.Visible;

public class SimonScreenBillyLuy extends ClickableScreen implements Runnable {
	
	private ProgressInterfaceBillyLuy user;
	private ArrayList<MoveInterfaceBillyLuy> order;
	private ButtonInterface[] button;
	private int round;
	private boolean acceptingInput;
	private int orderIndex;
	private int last;
	private TextLabel label;
	private ProgressInterfaceBillyLuy progress;

	public SimonScreenBillyLuy(int width, int height) {
		super(width, height);
		Thread simonStart = new Thread(this);
		simonStart.start();
	}

	@Override
	public void run(){
	    label.setText("");
	    nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		round++;
		order.add(randomMove());
		ProgressInterfaceBillyLuy.setRound(round);
		ProgressInterfaceBillyLuy.setSequenceSize(order.size());
		changeText("Simon's turn");
		label.setText("");
		playSequence();
		changeText("Your turn");
		acceptingInput = true;
		orderIndex = 0;
	}

	private void playSequence() {
		ButtonInterface b = null;
		for(int i =0; i<order.size();i++){
			if(b != null){
				b.dim();
			}
			//b = ((ButtonInterface)order).getButton();
			b = order.get(i).getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(round+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40," ");
		order = new ArrayList<MoveInterfaceBillyLuy>();
		//add 2 moves to start
		round = 0;
		last = -1;
		order.add(randomMove());
		order.add(randomMove());
		viewObjects.add(progress);
		viewObjects.add(label);
	}
	
	private MoveInterfaceBillyLuy randomMove() {
		ButtonInterface b;
		//code that randomly selects a ButtonInterfaceX
		int rand = (int)(Math.random()*button.length);
		//if its equal then pick a new
		while(rand == last){
			rand = (int) (Math.random()*button.length);
		}
		//change the last select into rand
		last = rand;
		/**
		 * FIX LATER
		 */
		b = button[rand];
		return getAMove(b);
	}


	private MoveInterfaceBillyLuy getAMove(ButtonInterface b) {
		return null;
	}

	private void addButtons() {
		int numberOfButtons = 5;
		//colors
		Color[] buttonColor = {Color.blue, Color.red,Color.black,Color.orange,Color.pink};
		//place all buttons
		for(int i =0; i < numberOfButtons; i++){
			//b is an object that is a button interface
			final ButtonInterface b = getAButton();
			b.setColor(buttonColor[i]);
			b.setX(100+(i*20));
			b.setY(300);
			b.setAction(new Action(){
				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if(b == order.get(orderIndex).getButton()){
							orderIndex++;
						}else{
							ProgressInterfaceBillyLuy.getOver();
						}
						if(orderIndex == order.size()){
							Thread nextRound = new Thread(SimonScreenBillyLuy.this);
							nextRound.start(); 
						}
					}
				}
			});
			viewObjects.add(b);
		}
	}
	
	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * FOR PARTNER TO FINSISH
	 */
	private ProgressInterfaceBillyLuy getProgress() {
		return null;
	}
	
	/**
	 * FOR PARTNER TO FINSISH
	 */
	private ButtonInterface getAButton() {
		return null;
	}
}
