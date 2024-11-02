import java.awt.*;
import java.util.*;
import java.awt.*;
import java.util.*;

public abstract class Prisoner {

	protected World myWorld;
	protected Location myLocation;
	protected boolean cooperate;
	protected boolean noChange;
	protected int score;
	protected Color color;
	
	public Prisoner (World myWorld, Location myLocation) {
		this.myWorld = myWorld;
		this.myLocation = myLocation;
	}
	
	public abstract int result(boolean neighborCooperates);
	
	public void setScore (int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean getCooperate() {
		return cooperate;
	}
	
	public Location getLocation() {
		return myLocation;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean getNoChange() {
		return noChange;
	}
	
	public void setNoChange() {
		noChange = true;
	}
}