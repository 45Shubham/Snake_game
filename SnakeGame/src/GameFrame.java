import javax.swing.JFrame;


public class GameFrame extends JFrame {
	
	GameFrame(){
		this.add(new GamePanel());
		//this.setSize(500, 500);
		this.setTitle("SnakeGame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
