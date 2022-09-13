import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

	Random random = new Random();
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int TOTAL_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 100;
	final int x[] = new int[TOTAL_UNITS];
	final int y[] = new int[TOTAL_UNITS];
	int body_parts = 6;
	int apple_eaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	JButton button;
	
	GamePanel(){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH , SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		button = new JButton();
		button.setText("New Game");
		button.setForeground(Color.GREEN);
		button.setBackground(Color.white);
		button.setFocusable(false);
		//button.setSize(100, 30);
		
		button.addActionListener(this);
		//this.add(button);
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY , this);
		timer.start();
	
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
        if(running) {
//        	for(int i = 0 ; i < SCREEN_WIDTH/UNIT_SIZE ; i++) {
//        		g.drawLine(i * UNIT_SIZE, 0 , i* UNIT_SIZE, SCREEN_HEIGHT);
//        		g.drawLine(0, i * UNIT_SIZE , SCREEN_WIDTH, i* UNIT_SIZE);
//        	}
        	
        	g.setColor(Color.red);
        	g.fillOval(appleX, appleY, UNIT_SIZE,UNIT_SIZE);
		
        	for(int i = 0 ; i < body_parts ; i++) {
        		if(i == 0) {
        			g.setColor(Color.green);
        			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        		}
        		else {
        			g.setColor(Color.blue);
        			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

        		}
        	}
        	g.setColor(Color.red);
    		g.setFont(new Font("Times New Roman" , Font.PLAIN , 20));
        	g.drawString("Score :"+apple_eaten, 0,25);
        }
        else {
        	gameOver(g);
        }
        
	}
	
	public void newApple(){
		appleX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
		appleY = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
		
		
	}
	
	public void move() {
		for(int i = body_parts ; i > 0 ; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'R' :
			x[0] = x[0]+ UNIT_SIZE;
			break;
		case 'L' :
			x[0] = x[0]- UNIT_SIZE;
			break;
		case 'U' :
			y[0] = y[0]- UNIT_SIZE;
			break;
		case 'D' :
			y[0] = y[0]+ UNIT_SIZE;
			break;
		}
	}
	
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			body_parts++;
			apple_eaten++;
			newApple();
		}
	}
	
	public void checkCollisions() {
		// head collides with body
		for(int i = body_parts ; i > 0 ; i--) {
			if(x[0] == x[i] && y[0] == y[i]) {
				running = false;
				
			}
			
		}
		// check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		
		// if ghead touches top border
		if(y[0] < 0) {
			running = false;
		}
		
		// check head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		button.setBounds(SCREEN_WIDTH/3, UNIT_SIZE, 100, 30);
		this.add(button);
		
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman" , Font.BOLD , 50));
		g.drawString("GAME OVER", SCREEN_WIDTH/4, SCREEN_HEIGHT/2);
		
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman" , Font.HANGING_BASELINE , 40));
    	g.drawString("Score :"+apple_eaten,SCREEN_WIDTH/4 ,SCREEN_HEIGHT/3  );
    	
//    	MyKeyAdapter start = new MyKeyAdapter();
//    	start.keyPressed(e);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		else {
			if(e.getSource() == button) {
				GameFrame newGame = new GameFrame();
			}
		}
		
		repaint();
		
		
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_A: 
				if(direction != 'R') {
					direction = 'L';
				}
				break;
				
			case KeyEvent.VK_RIGHT: 
				if(direction != 'L') {
					direction = 'R';
				}
				break;
				
			case KeyEvent.VK_D: 
				if(direction != 'L') {
					direction = 'R';
				}
				break;
				
			case KeyEvent.VK_UP: 
				if(direction != 'D') {
					direction = 'U';
				}
				break;
				
			case KeyEvent.VK_W: 
				if(direction != 'D') {
					direction = 'U';
				}
				break;
				
			case KeyEvent.VK_DOWN: 
				if(direction != 'U') {
					direction = 'D';
				}
				break;
				
			case KeyEvent.VK_S: 
				if(direction != 'U') {
					direction = 'D';
				}
				break;
				
			case KeyEvent.VK_ENTER:
				if(!running) {
					new GameFrame();
				}
			
			}
		}
	}

}
