package main;

import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JPanel;

import GameState.GameState;
import GameState.Menu;
import GameState.Mode;
import GameState.Single;
import GameState.Tournament;
import entity.Player;
import input.KeyBoardInput;
import input.MouseInput;

public class GamePanel extends JPanel {

	private MouseInput mouseInput;
	private KeyBoardInput keyboardInput;
	private Player player; 
	private GameState gameState;
	private Menu menu;
	private Mode mode;
	private Single single;
	private Tournament tournament;
	
	
	
	
	public GamePanel() {
		
		single = new Single(this);
		tournament = new Tournament(this);
		mode = new Mode(this);
		menu = new Menu(this);
		gameState = GameState.MENU;
		mouseInput = new MouseInput(this);
		keyboardInput = new KeyBoardInput(this);
		player = new Player(this, keyboardInput);
		setPanelSize();
		addKeyListener(keyboardInput);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		setFocusable(true);
		requestFocusInWindow();
	}

	
	private void setPanelSize() {
		Dimension size = new Dimension(960, 600);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}
	
	public void update() 
	{
		
		
		switch(gameState)
		{
		case GameState.MENU:
			menu.update();
				break;
			
		case GameState.PLAYING:
			player.update();
			break;
		
		case GameState.MODE:
			mode.update();
			break;
			
		case GameState.SINGLE:
			single.update();
			break;
			
		case GameState.TOURNAMENT:
			tournament.update();
			break;
		default:
			break;
		}
	}
	
	 public void paintComponent(Graphics g) {
		 
		 
		 switch(gameState)
			{
			case GameState.MENU:
				super.paintComponent(g);
				menu.paintComponent(g);
				break;
				
			case GameState.PLAYING:
				super.paintComponent(g);
				player.paintComponent(g);
				break;
			
			case GameState.MODE:
				super.paintComponent(g);
				mode.paintComponent(g);
				break;
				
			case GameState.SINGLE:
				super.paintComponent(g);
				single.paintComponent(g);
				break;
				
			case GameState.TOURNAMENT:
				super.paintComponent(g);
				tournament.paintComponent(g);
				break;
				
			default:
				break;
			}

	    }
	
	 public void draw(Graphics g) {
		 
//		 if (gameState == GameState.MENU) 
//			{
//				
//			}
//			
//		 else if (gameState == GameState.PLAYING) 
//			{
				////player.paintComponent(g);
				
			//}
		 
		 
		 switch(gameState)
			{
			case GameState.MENU:
				super.paintComponent(g);
				menu.paintComponent(g);
				break;
				
			case GameState.PLAYING:
				super.paintComponent(g);
				player.paintComponent(g);
				break;
			
			case GameState.MODE:
				super.paintComponent(g);
				mode.paintComponent(g);
				break;
				
			case GameState.SINGLE:
				super.paintComponent(g);
				single.paintComponent(g);
				break;
				
			case GameState.TOURNAMENT:
				super.paintComponent(g);
				tournament.paintComponent(g);
				break;
				
			default:
				break;
			}
		 
	    }
	 
	 public Menu getMenu() {
		    return menu;
		}
	 public Mode getMode() {
		    return mode;
		}
	public GameState getGameState() {
	    return gameState;
	}

	public void setGameState(GameState state) {
	    this.gameState = state;
	}

}
