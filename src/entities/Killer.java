package entities;

public class Killer extends Block {
	int damage = 2;
	public Killer(int x, int y) {
		super(x, y);
		super.type =1;
		super.red = 255;
		super.blue = 0;
		super.green = 0;
		// TODO Auto-generated constructor stub
	}
	public int getDamage() {
		return damage;
	}

}
