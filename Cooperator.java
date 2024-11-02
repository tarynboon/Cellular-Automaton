import java.awt.*;
import java.util.*;

import acm.util.RandomGenerator;

public class Cooperator extends Prisoner {
	private static RandomGenerator rgen = new RandomGenerator();
	
	//random scores assigned to choices
	public static final int REWARD = rgen.nextInt(0,1);
	public static final int SUCKER = rgen.nextInt(0,1);

	public Cooperator(World myWorld, Location myLocation) {
		super(myWorld, myLocation);
		color = Color.GREEN;
		cooperate = true;
	}
	
	public int result(boolean neighborCooperates) {
		//different outcomes for who cooperates or defects (table)
		if(neighborCooperates = true) {
			return REWARD;
		} else {
			return SUCKER;
		}
	}
	
}