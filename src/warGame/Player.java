package warGame;

public class Player {
	
	private String name;

	public Player(String name) {
		this.setName(name);
	}
	
	public String toString() {
		return getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isWinner() {
		// TODO Auto-generated method stub
		return false;
	}

}
