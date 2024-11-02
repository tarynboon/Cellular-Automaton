import java.awt.*;
import java.util.*;

import acm.util.RandomGenerator;

public class Defector extends Prisoner {
	private static RandomGenerator rgen = new RandomGenerator();
	
	//random scores assigned to choices
	public static final int TEMPTATION = rgen.nextInt(0,1);
	public static final int PUNISHMENT = rgen.nextInt(0,1);

	public Defector(World myWorld, Location myLocation) {
		super(myWorld, myLocation);
		color = Color.RED;
		cooperate = false;
	}

	public int result(boolean neighborCooperates) {
		//different outcomes for who cooperates or defects (table)
		if(neighborCooperates = true) {
			return TEMPTATION;
		} else {
			return PUNISHMENT;
		}
	}
}