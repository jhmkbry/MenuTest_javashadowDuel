package main;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60; 
    
    public Game() {
        
        gamePanel = new GamePanel(); // Create the game panel
        gameWindow = new GameWindow(gamePanel); // Create the game window and pass the game panel to it
        startGameLoop();
        gamePanel.requestFocus(); // Request focus for the game panel to capture keyboard input
        
    }
    
    // Method to start the game loop on a separate thread
    private void startGameLoop() {
        gameThread = new Thread(this);  // Create a new thread for the game loop
        gameThread.start();  // Start the game loop
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		double timePerFrame = 1000000000.0 / FPS_SET;  // Calculate time per frame to maintain FPS
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();

            // Update game every FPS_SET frames
            if (now - lastFrame >= timePerFrame) {
                update();  // Update the game logic
                gamePanel.repaint();  // Repaint the game panel to render new visuals
                lastFrame = now;
                frames++;
            }

            // Log FPS every second
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
	}
	
	 private void update() {
	    	gamePanel.update(); 
	    }

}
