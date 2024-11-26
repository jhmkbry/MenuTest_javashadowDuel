package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import input.KeyBoardInput;
import main.GamePanel;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyBoardInput keyboardInput;

    // Arrays to store the sprite images
    private BufferedImage spriteSheet;
    private BufferedImage[] idleSprites = new BufferedImage[8]; // 6 idle sprites
    private BufferedImage[][] movementSprites = new BufferedImage[4][9]; // 4 directions, 9 frames per direction
    private BufferedImage swordsman;
    
    private String player = "swordsman"; // "warlord"; 
    
    private int currentIdleSprite = 0;  // For idle animation
    private int currentMovementSprite = 0; // For movement animation
    private int currentDirection = -1; // -1 means not moving, 0 = up, 2 = down, 1 = left, 3 = right
    private int currentIdleSpriteNum = 0, currentMovementSpriteNum = 0;
    
    private long lastTime = System.currentTimeMillis();
    private final long animationInterval = 200; // Interval for sprite change (milliseconds)

    public Player(GamePanel gamePanel, KeyBoardInput keyboardInput) {
        this.gamePanel = gamePanel;
        this.keyboardInput = keyboardInput;
        SetDefaultValues();
        loadSpriteSheet(); // Load the sprite sheet and extract frames
    }

    public void SetDefaultValues() {
        x = 100;
        y = 100;
        speed = 3;
    }
    
    

    public void loadSpriteSheet() {
        try {
            // Load the sprite sheet image
        	
            
            
            
		            
			 switch(player) {
			 
			 case "warlord":
				 
				 InputStream is = getClass().getResourceAsStream("/Warlord2.png"); 
		         spriteSheet = ImageIO.read(is);
		         
				 currentIdleSpriteNum = 2;
				 currentMovementSpriteNum = 9;
				 
				 idleSprites[0] = spriteSheet.getSubimage(0 * 64, 142, 64, 54);
	             idleSprites[1] = spriteSheet.getSubimage(1 * 64, 142, 64, 54);
	            
	          
	             // Extract movement sprites for each direction
	                for (int i = 0; i < 9; i++) {
	                    movementSprites[0][i] = spriteSheet.getSubimage(i * 64, 527, 64, 54); // Up
	                    movementSprites[1][i] = spriteSheet.getSubimage(i * 64, 586, 64, 54); // Left
	                    movementSprites[2][i] = spriteSheet.getSubimage(i * 64, 655, 64, 54); // Down
	                    movementSprites[3][i] = spriteSheet.getSubimage(i * 64, 719, 64, 54); // Right
	                }
	         break;
	         
			 case "swordsman":
				 
				 InputStream is1 = getClass().getResourceAsStream("/swordsman.png"); 
		         swordsman = ImageIO.read(is1);
				 
		         currentIdleSpriteNum = 2;
				 currentMovementSpriteNum = 5;
				 
				 idleSprites[0] = swordsman.getSubimage(0 * 96, 0, 96, 96);
	             idleSprites[1] = swordsman.getSubimage(1 * 288, 0, 96, 96);
	             
				// Extract movement sprites for each direction
	                for (int i = 0; i < 5; i++) {
	                	movementSprites[0][i] = swordsman.getSubimage(i * 96, 192, 96, 96); // Up
	                	movementSprites[1][i] = swordsman.getSubimage(i * 96, 96, 96, 96); // Left
	                	movementSprites[2][i] = swordsman.getSubimage(i * 96, 0, 96, 96); // Down
	                	movementSprites[3][i] = swordsman.getSubimage(i * 96, 288, 96, 96); // Right
	                }
			 }
			 
		        
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Check keyboard input and update position
        if (keyboardInput.up) {
            y -= speed;
            currentDirection = 0; // Moving up
        } else if (keyboardInput.down) {
            y += speed;
            currentDirection = 2; // Moving down
        } else if (keyboardInput.left) {
            x -= speed;
            currentDirection = 1; // Moving left
        } else if (keyboardInput.right) {
            x += speed;
            currentDirection = 3; // Moving right
        } else {
            currentDirection = -1; // Not moving
        }

        // Handle animation based on time
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= animationInterval) {
            if (currentDirection == -1) {
                // If not moving, cycle through idle sprites
                currentIdleSprite = (currentIdleSprite + 1) % currentIdleSpriteNum;
            } else {
                // If moving, cycle through the current direction's movement sprites
                currentMovementSprite = (currentMovementSprite + 1) % currentMovementSpriteNum;
            }
            lastTime = currentTime;
        }

        //System.out.println("Player position: " + x + ", " + y);
    }
    

    public void paintComponent(Graphics g) {
        // Draw the current sprite based on whether the player is moving or idle
        if (currentDirection == -1) {
            // If not moving, draw the idle sprite
            g.drawImage(idleSprites[currentIdleSprite], (int) x, (int) y, null);
        } else {
            // If moving, draw the movement sprite based on direction
            g.drawImage(movementSprites[currentDirection][currentMovementSprite], (int) x, (int) y, null);
        }
    }
}
