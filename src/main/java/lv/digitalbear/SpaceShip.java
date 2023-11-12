package lv.digitalbear;

public class SpaceShip extends BaseObject {

	private static final int[][] matrix = {
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0},
			{0, 0, 1, 0, 0},
			{0, 1, 1, 1, 0},
	};
	private double dx;

	public SpaceShip(double x, double y) {
		super(x, y, 3);
	}

	public void moveLeft() {
		dx = -1;
	}

	public void moveRight() {
		dx = 1;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'M');
	}

	@Override
	public void move() {
		x = x + dx;
		checkBorders(radius, Start.game.getWidth() - radius + 1, 1, Start.game.getHeight() + 1);
	}

	public void fire() {
		Start.game.getRockets().add(new Rocket(x - 2, y));
		Start.game.getRockets().add(new Rocket(x + 2, y));

	}

}
