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
import main.Main;


public class Menu implements StateMethods {

	private GamePanel gamePanel;
    private BufferedImage spriteSheet;
    private BufferedImage[][] menuButtons = new BufferedImage[3][2]; // 3 buttons with 2 states each
    private BufferedImage mainBG;
    private boolean mouseOver, mousePressed;
    private Rectangle[] bounds = new Rectangle[3];
    private int selectedButtonIndex = -1; // Tracks the selected button (-1 means none)

    public Menu(GamePanel gamePanel) {
    	this.gamePanel = gamePanel;
        loadSpriteSheet();
        initBounds();
    }

    private void initBounds() {
        bounds[0] = new Rectangle(280, 170, 400, 100); // Start Button
        bounds[1] = new Rectangle(280, 280, 400, 100); // Quit Button
        bounds[2] = new Rectangle(280, 390, 400, 100); // About Us Button
    }

    public void loadSpriteSheet() {
        try {
            InputStream is = getClass().getResourceAsStream("/menuButtons.png");
            spriteSheet = ImageIO.read(is);
            
            InputStream is1 = getClass().getResourceAsStream("/MainMenuBG.png");
            mainBG = ImageIO.read(is1);

            for (int i = 0; i < 3; i++) {
                menuButtons[i][0] = spriteSheet.getSubimage(0, i * 100, 400, 100);      // Normal state
                menuButtons[i][1] = spriteSheet.getSubimage(400, i * 100, 400, 100);    // Hover/Pressed state
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
    	g.drawImage(mainBG, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), null);

    	
        for (int i = 0; i < bounds.length; i++) {
            BufferedImage buttonImage = menuButtons[i][mouseOver && selectedButtonIndex == i ? 1 : 0];
            g.drawImage(buttonImage, bounds[i].x, bounds[i].y,null);
        }
    }

    public void update() {
        if (mousePressed && selectedButtonIndex != -1) {
            switch (selectedButtonIndex) {
                case 0: // Start Button
                    System.out.println("Start Game!");
                    // Change state to PLAYING
                   gamePanel.setGameState(GameState.MODE);
                    break;
                case 1: // Quit Button
                    System.out.println("Quit Game!");
                    System.exit(0);
                    break;
                case 2: // About Us Button
                    System.out.println("About Us Screen!");
                    // Implement a method to handle the About Us screen
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
    public void mouseClicked(MouseEvent e) {
        // Not needed for this implementation
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        // Not needed for this implementation
    }

    @Override
    public void KeyReleased(KeyEvent e) {
        // Not needed for this implementation
    }
}

