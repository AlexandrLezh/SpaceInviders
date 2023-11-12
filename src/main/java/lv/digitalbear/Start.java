package lv.digitalbear;

public class Start {
	public static Space game;

	public static void main(String[] args) {
		game = new Space(15, 15, 1200);
		game.setShip(new SpaceShip(8, 14));
		game.run();
	}
}
