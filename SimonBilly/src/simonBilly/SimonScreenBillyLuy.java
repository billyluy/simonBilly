package simonBilly;

import java.awt.Color;
import java.util.ArrayList;

import gui.components.Action;
import gui.components.ClickableScreen;
import gui.components.TextLabel;
import gui.components.Visible;
import partnerCodeHerePlease.Button;
import partnerCodeHerePlease.Move;
import partnerCodeHerePlease.Progress;

public class SimonScreenBillyLuy extends ClickableScreen implements Runnable{

	private TextLabel label;
	private ButtonInterface[] buttons;
	private ProgressInterfaceBillyLuy progress;
	private ArrayList<MoveInterfaceBillyLuy> sequence; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelected;

	public SimonScreenBillyLuy(int width, int height) {
		super(width, height);
		Thread screen = new Thread(this);
		screen.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		label = new TextLabel(375,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceBillyLuy>();
		lastSelected = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private void addButtons() {
		Color[] colors = {Color.BLUE, Color.PINK, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE};
		int[] coordX = {300, 300, 400, 400, 500, 500};
		int[] coordY = {300, 400, 300, 400, 300, 400};
		int buttonCount = 6;
		buttons = new ButtonInterface[buttonCount];
		for(int i = 0; i < buttonCount; i++ ){
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setX(coordX[i]);
			buttons[i].setY(coordY[i]);
			final ButtonInterface b = buttons[i];
			b.dim();
			buttons[i].setAction(new Action() {
				public void act() {
						Thread buttonPress = new Thread(new Runnable() {
							
							public void run() {
								b.highlight();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
							}
						});
						buttonPress.start();
						if(acceptingInput && sequence.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}else if(acceptingInput){
							gameOver();
							return;
						}
						if(sequenceIndex == sequence.size()){
							Thread nextRound = new Thread(SimonScreenBillyLuy.this);
							nextRound.start();
						}
					}

			});
			viewObjects.add(buttons[i]);
		}
	}

	public void gameOver() {
		ProgressInterfaceBillyLuy.gameOver();
	}

	public void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		ProgressInterfaceBillyLuy.setRound(roundNumber);
		sequence.add(randomMove());
		ProgressInterfaceBillyLuy.setSequenceSize(sequence.size());
		changeText("Simon's turn");
		label.setText("");
		playSequence();
		changeText("Your turn");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private MoveInterfaceBillyLuy randomMove() {
		int select = (int) (Math.random()*buttons.length);
		while(select == lastSelected){
			select = (int) (Math.random()*buttons.length);
		}
		lastSelected = select;
		return new Move(buttons[select]);
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run() {
		changeText("");
		nextRound();
	}

	private void playSequence() {
		ButtonInterface b = null;
		for(int i =0; i<sequence.size();i++){
			if(b != null){
				b.dim();
			}
			b = sequence.get(i).getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}
	
	private ProgressInterfaceBillyLuy getProgress() {
		return new Progress();
	}
	
	private ButtonInterface getAButton() {
		return new Button();
	}
}
