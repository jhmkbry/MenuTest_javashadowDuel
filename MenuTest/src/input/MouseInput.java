package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameState.GameState;
import main.GamePanel;

public class MouseInput implements MouseListener, MouseMotionListener{
	
	private GamePanel gamePanel;
	private GameState gameState;
	public MouseInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (gamePanel.getGameState() == GameState.MENU) {
			gamePanel.getMenu().mouseMoved(e);
	    }
		
		else if (gamePanel.getGameState() == GameState.MODE) {
			gamePanel.getMode().mouseMoved(e);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gamePanel.getGameState() == GameState.MENU) {
			gamePanel.getMenu().mousePressed(e);
		}
		
		else if (gamePanel.getGameState() == GameState.MODE) {
			gamePanel.getMode().mousePressed(e);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (gamePanel.getGameState() == GameState.MENU) {
			gamePanel.getMenu().mouseReleased(e);
		}
		
		else if (gamePanel.getGameState() == GameState.MODE) {
			gamePanel.getMode().mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
