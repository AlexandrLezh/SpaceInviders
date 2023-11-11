package lv.digitalbear;

public abstract class BaseObject {
	protected double x;
	protected double y;
	protected double radius;
	private boolean isAlive;

	public BaseObject(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.isAlive = true;
	}

	public void draw(Canvas canvas) {

	}

	public void move() {

	}

	public void die() {
		isAlive = false;
	}


	public boolean isAlive() {
		return isAlive;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void checkBorders(double minX, double maxX, double minY, double maxY) {
		if (x < minX) x = minX;
		if (x > maxX) x = maxX;
		if (y < minY) y = minY;
		if (y > maxY) y = maxY;
	}

	public boolean isIntersect(BaseObject o) {
		double dx = x - o.x;
		double dy = y - o.y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double distance2 = Math.max(radius, o.radius);
		return distance <= distance2;
	}
}
