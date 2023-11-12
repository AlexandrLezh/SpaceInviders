package lv.digitalbear;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Space {

	private final int width;
	private final int height;
	private static int delay;

	private SpaceShip ship;
	private final List<Ufo> ufos = new ArrayList<>();
	private final List<Rocket> rockets = new ArrayList<>();
	private final List<Bomb> bombs = new ArrayList<>();

	public Space(int width, int height, int delay) {
		this.width = width;
		this.height = height;
		this.delay = delay;
	}

	public void run() {
		Canvas canvas = new Canvas(width, height);
		KeyboardObserver keyboardObserver = new KeyboardObserver();
		keyboardObserver.start();

		while (ship.isAlive()) {
			if (keyboardObserver.hasKeyEvents()) {
				KeyEvent event = keyboardObserver.getEventFromTop();
				System.out.print(event.getKeyCode());
				if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					ship.moveLeft();
				} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					ship.moveRight();
				} else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
					ship.fire();
				}
			}
			moveAllItems();
			checkBombs();
			checkRockets();
			removeDead();
			createUfo();
			canvas.clear();
			draw(canvas);
			canvas.print();
			Space.sleep();
		}
		System.out.println("Game over!!!");
	}

	public static void sleep() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ignored) {
		}
	}

	public void draw(Canvas canvas) {

		for (int i = 0; i < width + 2; i++) {
			for (int j = 0; j < height + 2; j++) {
				canvas.setPoint(i, j, '.');
			}
		}

		for (int i = 0; i < width + 2; i++) {
			canvas.setPoint(i, 0, '-');
			canvas.setPoint(i, height + 1, '-');
		}

		for (int i = 0; i < height + 2; i++) {
			canvas.setPoint(0, i, '|');
			canvas.setPoint(width + 1, i, '|');
		}

		for (BaseObject object : getAllItems()) {
			object.draw(canvas);
		}
	}

	public void moveAllItems() {
		for (BaseObject object : getAllItems()) {
			object.move();
		}
	}

	public List<BaseObject> getAllItems() {
		ArrayList<BaseObject> list = new ArrayList<>(ufos);
		list.add(ship);
		list.addAll(bombs);
		list.addAll(rockets);
		return list;
	}

	public void createUfo() {
		if (ufos.size() > 0) {
			return;
		}
		int random10 = (int) (Math.random() * 10);
		if (random10 == 0) {
			double x = Math.random() * width;
			double y = Math.random() * height / 2;
			ufos.add(new Ufo(x, y));
		}
	}

	public void checkBombs() {
		for (Bomb bomb : bombs) {
			if (ship.isIntersect(bomb)) {
				ship.die();
				bomb.die();
			}
			if (bomb.getY() >= height) {
				bomb.die();
			}
		}
	}

	public void checkRockets() {
		for (Rocket rocket : rockets) {
			for (Ufo ufo : ufos) {
				if (ufo.isIntersect(rocket)) {
					ufo.die();
					rocket.die();
				}
			}
			if (rocket.getY() <= 0) {
				rocket.die();
			}
		}
	}

	public void removeDead() {
		for (BaseObject object : new ArrayList<>(bombs)) {
			if (!object.isAlive()) {
				bombs.remove(object);
			}
		}

		for (BaseObject object : new ArrayList<>(rockets)) {
			if (!object.isAlive()) {
				rockets.remove(object);
			}
		}

		for (BaseObject object : new ArrayList<>(ufos)) {
			if (!object.isAlive()) {
				ufos.remove(object);
			}
		}
	}

	public void setShip(SpaceShip ship) {
		this.ship = ship;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public List<Rocket> getRockets() {
		return rockets;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}


}