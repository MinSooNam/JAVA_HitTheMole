import javax.swing.JFrame;

public class MoleApp {

	public static JFrame frame;

	public static void main(String[] args) {

		frame = new JFrame("Mole ¶§·Á?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//InitPanel initPanel = new InitPanel();

		frame.getContentPane().add(new InitPanel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
