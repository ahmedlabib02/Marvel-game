package model.abilities;

import java.util.ArrayList;

import model.effects.Dodge;
import model.effects.Effect;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)
			if(this.getCastArea() != AreaOfEffect.SELFTARGET  ) {
				if( d instanceof Champion && !containsDodge((Champion)d)) {
				d.setCurrentHP(d.getCurrentHP() - damageAmount);
				((Champion)d).animation.currentHp = d.getCurrentHP();
				((Champion)d).animation.attacked=true;}
			else if(d instanceof Cover) {
				d.setCurrentHP(d.getCurrentHP() - damageAmount);
				((Cover)d).animation.currentHp = d.getCurrentHP();
				((Cover)d).animation.attacked=true;
			}
			}
	}
	public static Boolean containsDodge(Champion c) {
		ArrayList<Effect> a = c.getAppliedEffects();
		for(Effect e: a) {
			if(e instanceof Dodge)
				return true;
		}
		return false;
	}
	public String toString()
	{
		return super.toString() +  "DamagingAbility DamageAmount: " + damageAmount +"\n";
	}
}
