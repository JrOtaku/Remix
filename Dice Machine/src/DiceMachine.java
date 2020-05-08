import java.util.Random;

import javax.swing.JOptionPane;
public class DiceMachine {

	public static void main(String[] args) {
		int sidesOfDice = Integer.parseInt(JOptionPane.showInputDialog("Number of Sides on Dice"));
		Random rand = new Random();
		System.out.print(rand.nextInt(sidesOfDice) + 1);
	}
}
