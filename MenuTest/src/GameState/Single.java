package GameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entity.Player;
import input.KeyBoardInput;
import main.GamePanel;

public class Single implements StateMethods{
	
	private GamePanel gamePanel;
	private Player player;
	private KeyBoardInput keyBoardInput;
	
	public Single(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		keyBoardInput = new KeyBoardInput(gamePanel);
		new Player(gamePanel, keyBoardInput);
		
	}

	@Override
	public void paintComponent(Graphics g) {
		player.paintComponent(g);
		
	}

	@Override
	public void update() {
		player.update();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		keyBoardInput.keyPressed(e);
		
	}

	@Override
	public void KeyReleased(KeyEvent e) {
		keyBoardInput.keyReleased(e);
		
	}

}
