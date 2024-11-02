//Taryn Boonpongmanee, Justin Ahn, Cooper Pertchik, Tynan Creagh
//Unconditional Cooperators and Defectors AND Checkboard Layout
//Cellular Autonoma Project

import java.util.ArrayList;
import acm.util.RandomGenerator;

public class World {
	
	private int width;
	private int height;
	private final int noChangePercent = 3;
	private ArrayList<Prisoner> prisonerList;
	private RandomGenerator rgen = new RandomGenerator();
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.prisonerList = new ArrayList<Prisoner>();
		
	//unconditional cooperator and defectors
	for (int x = 0; x < 20; x++) {
		for (int y = 0; y < 20; y++) {
			//determines whether next prisoner should be a cooperator or defector
			boolean random = rgen.nextBoolean(0.5);	
			int rand = rgen.nextInt(100);
				//cooperator
				if(random == true) {
					Cooperator coop = new Cooperator(this, new Location(x, y));
					// 3/100 percent will be unconditional cooperator
					if(rand <= noChangePercent) {
						coop.setNoChange();
					}
					if(rand < 10) {
						prisonerList.remove(coop);
					}
						prisonerList.add(coop);
				//defector
				} else {
					Defector defect = new Defector(this, new Location(x,y));
					// 3% will be unconditonal defector
					if(rand <= noChangePercent) {
						defect.setNoChange();
					}
					prisonerList.add(defect);
				}
		}
	}
		//checkerboard layout
		/*for (int x = 0; x < 5; x++) {

			for (int y = 0; y < 5; y++) {
				
				if(x % 2 == 1) {
					if(y % 2 == 0) {
						Cooperator coop = new Cooperator(this, new Location(x,y));
						prisonerList.add(coop);
					}else if(y % 2 == 1) {
						Defector defect = new Defector(this, new Location(x,y));
						prisonerList.add(defect);
					}
				}
				if(x % 2 == 0) {
					if(y % 2 ==0) {
						Defector defect = new Defector(this, new Location(x,y));
						prisonerList.add(defect);
					}else if(y % 2 == 1) {
						Cooperator coop = new Cooperator(this, new Location(x,y));
						prisonerList.add(coop);
					}
				}
			}*/
		
	}
	

	public void letTimePass() {
		calculateScores();
		adaptBehavior();
		System.out.println("time passed");
	}
	
	
	public void printOut() {
		for (Prisoner p:prisonerList) {
			System.out.print(p.getCooperate());
			System.out.println(p.getScore());
		}
		System.out.println("");
	}
	
	
	public void calculateScores() {
		printOut();
		for(Prisoner p : prisonerList) {
			int score=0;
			for(Prisoner n : prisonerList) {
				if(isNeighbor(n, p)) {
					score += p.result(n.getCooperate());
				}
			}
			p.setScore(score);
		}
	}
	
	public void adaptBehavior() {
		for(int i = 0; i < prisonerList.size(); i++) {
			Prisoner p = prisonerList.get(i);
			//if neighbor is more successful change strategy
			if(!p.getNoChange() && mostSuccessfulNeighbor(p).getCooperate() != p.getCooperate()) {
				changeType(i);
			}
		}
	}
	
	public Prisoner mostSuccessfulNeighbor(Prisoner myself) {
		//determine who is the most successful between neighbors
		Prisoner mostSuccessfulNeighbor = myself;
		for (int i = 0; i < prisonerList.size(); i++) {
			Prisoner next = prisonerList.get(i);
			if (isNeighbor(myself, next)) {
				if (next.getScore() > mostSuccessfulNeighbor.getScore()) {
					mostSuccessfulNeighbor = next;
				}
			}
		}
		return mostSuccessfulNeighbor;
	}

	public void changeType(int i) {
		//change strategy
		Prisoner p = prisonerList.get(i);
		if(p.getCooperate()==true) {
			prisonerList.set(i, new Defector(this, p.getLocation()));
		} else {
			prisonerList.set(i, new Cooperator(this, p.getLocation()));

		}
	}

	public boolean isNeighbor(Prisoner p1, Prisoner p2) {
		
		int x1 = p1.getLocation().getX();
		int y1 = p1.getLocation().getY();
		int x2 = p2.getLocation().getX();
		int y2 = p2.getLocation().getY();
		 
		if ((x1 <= x2 + 1 && x1 >= x2 - 1) && (y1 <= y2 + 1 && y1 >= y2 - 1)) {
			return true;
		}
		
		return false;
	}

	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<Prisoner> getPrisonerList() {
		return prisonerList;
	}
	public void setPrisonerList(ArrayList<Prisoner> prisonerList) {
		this.prisonerList = prisonerList;
	}

	@Override
	public String toString() {
		return "World [width=" + width + ", height=" + height
				+ ", creatureList=" + prisonerList + "]";
	}
}