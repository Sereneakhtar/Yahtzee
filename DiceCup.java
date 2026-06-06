package yahtzee;

public class DiceCup {
	private Die[] dice;
	private boolean[] held;
	
	public DiceCup() {
		dice = new Die[5];
		held = new boolean[5];
		for (int i = 0; i < 5; i++) {
			dice[i] = new Die();
		}
	}
	
	public void roll() {
		for (int i = 0; i < 5; i++) {
			if (!held[i]) {
				dice[i].roll();
			}
		}
	}
	
	public void toggleHold(int index) {
		held[index] = !held[index];
	}
	
	public void resetHolds() {
		for (int i = 0; i < 5.; i++) {
			held[i] = false;
		}
	}
	
	public int[] getValues() {
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = dice[i].getValue();
		}
		return values;
	}
	
	public void displayDice() {
		for (int i = 0; i < 5; i++) {
	        String mark = held[i] ? "*" : " ";
	        System.out.print((i + 1) + ":[" + dice[i].getValue() + mark + "] ");
	    }
	    System.out.println();
	    System.out.println("* = held");
	}
}
