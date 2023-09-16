package model.world;

import java.awt.Point;
import java.util.ArrayList;

import entity.Entity;
import model.abilities.Ability;
import model.effects.Effect;

@SuppressWarnings("rawtypes")
public abstract class Champion implements Damageable, Comparable {
	private String name;
	private int maxHP;
	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	private int currentHP;
	private int mana;
	private int maxMana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	public Entity animation;

	public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.maxMana = mana;
		this.currentHP = this.maxHP;
		this.maxActionPointsPerTurn = actions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.condition = Condition.ACTIVE;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.currentActionPoints = maxActionPointsPerTurn;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public void setCurrentHP(int hp) {

		if (hp <= 0) {
			currentHP = 0;
			condition = Condition.KNOCKEDOUT;

		} else if (hp > maxHP)
			currentHP = maxHP;
		else
			currentHP = hp;

	}

	public int getCurrentHP() {

		return currentHP;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int currentSpeed) {
		if (currentSpeed < 0)
			this.speed = 0;
		else
			this.speed = currentSpeed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point currentLocation) {
		this.location = currentLocation;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if (currentActionPoints > maxActionPointsPerTurn)
			currentActionPoints = maxActionPointsPerTurn;
		else if (currentActionPoints < 0)
			currentActionPoints = 0;
		this.currentActionPoints = currentActionPoints;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}

	public int compareTo(Object o) {
		Champion c = (Champion) o;
		if (speed == c.speed)
			return name.compareTo(c.name);
		return -1 * (speed - c.speed);
	}

	public abstract void useLeaderAbility(ArrayList<Champion> targets);

	public String toString2() {
		String m = "Effects: ";
		for (int i = 0; i < appliedEffects.size(); i++) {
			if (i > 0)
				m += "                ";
			m += appliedEffects.get(i).toString2() + "<br/>";
		}
		String r = "";
		if (this instanceof AntiHero)
			r = "AntiHero";
		else if (this instanceof Hero)
			r = "Hero";
		else
			r = "Villain";
		return "<html>Name: " + name + "<br/>Type: " + r +// "<br/>Current Healthpoints: " + currentHP + "<br/>Mana: "
//				+ mana + "<br/>Current Action Points : " + currentActionPoints +
//
//				"<br/>Max HP: " + maxHP +
//
//				"<br/>Max Action Points Per Turn: " + maxActionPointsPerTurn +

				"<br/>Speed: " + speed + "<br/>Attack Range: " + attackRange + "<br/>Attack Damage: " + attackDamage
				+ "<br/>" + m
				+ "<br/>"
				+ "<html/>";
	}

	public String toString3() {
		String s = "";
		for (int i = 0; i < abilities.size(); i++) {
			if (i > 0)
				s += "                ";
			s += abilities.get(i).toString2() + "<br/>";
		}
		return "<html>Abilities: " + s
				+ "<br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/>"
				+ "<html/>";
	}

	public String toString() {
		String s = "Abilities: ";
		for (int i = 0; i < abilities.size(); i++) {
			if (i > 0)
				s += "                ";
			s += abilities.get(i) + "\n";
		}
		String r = "";
		if (this instanceof AntiHero)
			r = "AntiHero";
		else if (this instanceof Hero)
			r = "Hero";
		else
			r = "Villain";
		return "Name: " + name + "\n" + "\n" + "Max HP: " + maxHP + "\n" + "\n" + "Mana: " + mana + "\n" + "\n"
				+ "Max Action Points Per Turn: " + maxActionPointsPerTurn + "\n" + "\n" + "Speed: " + speed + "\n"
				+ "\n" + "Attack Range: " + attackRange + "\n" + "\n" + "Attack Damage: " + attackDamage + "\n" + "\n"
				+ s;
	}

	public String toStringl() {
		String s;
		s = "Champion Name: " + name + "\n" + "Mana: " + mana + "\n" + "CurrentHealthPoints: " + currentHP + "\n"
				+ "Speed: " + speed + "\n" + "AttackRange: " + attackRange + "\n" + "AttackDamage: " + attackDamage
				+ "\n" + "Condition: " + condition + "\n" + "CurrentActionPoints: " + currentActionPoints + "\n";
		if (this instanceof AntiHero)
			s = s + "Type:AntiHero" + "\n" + "\n";
		else if (this instanceof Hero)
			s = s + "Type:Hero" + "\n" + "\n";
		else
			s = s + "Type:Villain" + "\n" + "\n";
		s = s + "Abilities: " + "\n";

		for (int i = 0; i < this.getAbilities().size(); i++) {
			s = s + this.getAbilities().get(i).toStringl();
		}
		for (int i = 0; i < this.getAppliedEffects().size(); i++) {
			s = s + this.getAppliedEffects().get(i).toString();
		}

		return s;
	}

}
