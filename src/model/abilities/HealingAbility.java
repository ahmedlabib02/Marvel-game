package model.abilities;

import java.util.ArrayList;

import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets) {

			d.setCurrentHP(d.getCurrentHP() + healAmount);
		if(d instanceof Champion) {
		((Champion)d).animation.currentHp = d.getCurrentHP();
		((Champion)d).animation.attacked=true;}
		if(d instanceof Cover) {
			((Cover)d).animation.currentHp = d.getCurrentHP();
			((Cover)d).animation.attacked=true;}
		}

	}
	public String toString()
	{
		return super.toString() + "HealingAbility Heal Amount" + healAmount + "\n";
				
	
	}
	

}
