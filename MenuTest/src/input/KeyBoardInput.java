package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyBoardInput implements KeyListener{
	
	public boolean attacking, up, down, left, right;
	private GamePanel gamePanel;
	
	public KeyBoardInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        	case KeyEvent.VK_Q -> attacking = true;
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_D -> right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        	case KeyEvent.VK_Q -> attacking = false;
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

}
