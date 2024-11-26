package GameState;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Mode implements StateMethods{
	
	private GamePanel gamePanel;
	private Rectangle[] bounds = new Rectangle[3];
	private BufferedImage spriteSheet;
	private BufferedImage modeBG;
    private BufferedImage[][] menuButtons = new BufferedImage[3][2]; // 3 buttons with 2 states each
    private boolean mouseOver, mousePressed;
    private int selectedButtonIndex = -1; // Tracks the selected button (-1 means none)


	
	   public Mode(GamePanel gamePanel) {
	    	this.gamePanel = gamePanel;
	        loadSpriteSheet();
	        initBounds();
	    }
	   
	private void initBounds() {
		bounds[0] = new Rectangle(280, 170, 400, 100); // Single Button
        bounds[1] = new Rectangle(280, 280, 400, 100); // Tournament Button
        bounds[2] = new Rectangle(280, 390, 400, 100); // Back Button
    
	}

	private void loadSpriteSheet() {
		try {
            InputStream is = getClass().getResourceAsStream("/menuButtons.png");
            spriteSheet = ImageIO.read(is);
            
            InputStream is1 = getClass().getResourceAsStream("/modeBG.png");
            modeBG = ImageIO.read(is1);


            for (int i = 0; i <3; i++) {
                menuButtons[i][0] = spriteSheet.getSubimage(0, (i+3)* 100, 400, 100);      // Normal state
                menuButtons[i][1] = spriteSheet.getSubimage(400,(( i+3) * 100), 400, 100);    // Hover/Pressed state
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public void paintComponent(Graphics g) {
		
		g.drawImage(modeBG, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), null);

		for (int i = 0; i < bounds.length; i++) {
            BufferedImage buttonImage = menuButtons[i][mouseOver && selectedButtonIndex == i ? 1 : 0];
            g.drawImage(buttonImage, bounds[i].x, bounds[i].y, null);
        }
	}
	
	public void update() {
        if (mousePressed && selectedButtonIndex != -1) {
            switch (selectedButtonIndex) {
                case 0: // Single Button
                    System.out.println("Single Game!");
                    // Change state to PLAYING
                   gamePanel.setGameState(GameState.SINGLE);
                    break;
                case 1: // Tournament Button
                    System.out.println("Tournament");
                    gamePanel.setGameState(GameState.TOURNAMENT);
                    break;
                case 2: // Back Button
                    System.out.println("Back!");
                    gamePanel.setGameState(GameState.MENU);
                    break;
            }
            mousePressed = false; // Reset after handling action
        }
    }


    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
        selectedButtonIndex = -1;
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < bounds.length; i++) {
            if (bounds[i].contains(e.getPoint())) {
                selectedButtonIndex = i;
                mousePressed = true;
                break;
            }
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mousePressed && selectedButtonIndex != -1) {
            update(); // Trigger button action
        }
        resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseOver = false;
        selectedButtonIndex = -1;

        // Check if the mouse is over any button
        for (int i = 0; i < bounds.length; i++) {
            if (bounds[i].contains(e.getPoint())) {
                mouseOver = true;
                selectedButtonIndex = i;
                break;
            }
        }
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
