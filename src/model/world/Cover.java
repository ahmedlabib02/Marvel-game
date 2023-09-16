package model.world;

import java.awt.Point;

import entity.Entity;

public class Cover implements Damageable {
	private int currentHP;

	private Point location;
	public Entity animation;

	public Cover(int x, int y) {
		this.currentHP = (int)(( Math.random() * 900) + 100);
		location = new Point(x, y);
	}

	public int getCurrentHP() {
		return this.currentHP;
	}

	public void setCurrentHP(int newHp) {
		if (newHp < 0) {
			currentHP = 0;
		
		} else
			currentHP = newHp;
	}

	public Point getLocation() {
		return location;
	}

	

	

}
