package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public abstract class Ability {
	private String name;
	private int manaCost;
	private int baseCooldown;
	private int currentCooldown;
	private int castRange;
	private AreaOfEffect castArea;
	private int requiredActionPoints;

	public Ability(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required) {
		this.name = name;
		this.manaCost = cost;
		this.baseCooldown = baseCoolDown;
		this.currentCooldown = 0;
		this.castRange = castRange;
		this.castArea = area;
		this.requiredActionPoints = required;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}
	public abstract void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;

	public void setCurrentCooldown(int currentCoolDown) {
		if (currentCoolDown < 0)
			currentCoolDown = 0;
		else if (currentCoolDown > baseCooldown)
			currentCoolDown = baseCooldown;
		this.currentCooldown = currentCoolDown;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public int getCastRange() {
		return castRange;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}
	public String toString() {
		return name;
	}
	public String toString2()
	{    String t ;
	     if(this instanceof DamagingAbility)
	    	 t="AbilityType:" + "DamagingAbility";
	     else if(this instanceof HealingAbility)
	    	 t="AbilityType:" + "HealingAbility";
	     else 
	    	 t="AbilityType:" + "CrowdControlAbility";
	     
		return "<html>Ability Name: " + name+ "<br/>" + t  +"<br/>" + "Ability Manacost: " + manaCost+  "<br/>" + "AbilityBaseCoolDown: " + baseCooldown+ "<br/>"+ "AbilityCurrentCooldown: " + 
		currentCooldown + "<br/>" + "AbilityCastRange: " + castRange + "<br/>" + "AbilityCastArea: "+ castArea +"<br/>" + "AbilityrequiredActionPoints: " + requiredActionPoints+"<br/>";
		
	}
	
	public String toStringl()
	{
		return "Ability Name: " + name+ "\n"  + "Ability Manacost: " + manaCost + "\n" + "AbilityBaseCoolDown: " + baseCooldown+ "\n" + "AbilityCurrentCooldown: " + 
		currentCooldown + "\n" + "AbilityCastRange: " + castRange + "\n"  + "AbilityCastArea: "+ castArea +"\n" + "AbilityrequiredActionPoints: " + requiredActionPoints+"\n" + "\n";
		
	}
	
}
