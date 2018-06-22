package com.gmail.berndivader.mythicskills.mythicmobs.conditions;

import org.bukkit.entity.Entity;

import com.gmail.berndivader.mythicskills.MythicSkills;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;

public class LastSapiDamageCause 
extends
AbstractCustomCondition 
implements
IEntityCondition {
	String[]causes;

	public LastSapiDamageCause(String line, MythicLineConfig mlc) {
		super(line, mlc);
		this.causes=mlc.getString(new String[] {"cause","c"},"any").toUpperCase().split(",");
	}

	@Override
	public boolean check(AbstractEntity ae) {
		boolean match=false;
		String cause=null;
		Entity entity=ae.getBukkitEntity();
		if (entity.hasMetadata(MythicSkills.str_LastSapiDamageCause)) cause=entity.getMetadata(MythicSkills.str_LastSapiDamageCause).get(0).asString().toUpperCase();
		if (cause!=null) {
			
		}
		if (cause!=null&&!causes[0].equals("ANY")) {
			for(String s1:causes) {
				if (s1.equals(cause)) {
					match=true;
					break;
				}
			}
		}
		entity.removeMetadata(MythicSkills.str_LastSapiDamageCause,MythicSkills.getPlugin());
		return match;
	}
}
