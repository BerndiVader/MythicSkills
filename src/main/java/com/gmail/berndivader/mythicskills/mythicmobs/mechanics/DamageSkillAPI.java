package com.gmail.berndivader.mythicskills.mythicmobs.mechanics;

import org.bukkit.entity.LivingEntity;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.skills.Skill;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillString;

public class DamageSkillAPI 
extends
SkillMechanic 
implements
ITargetedEntitySkill {
	Skill sk;
	double d1;

	public DamageSkillAPI(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		ASYNC_SAFE=false;
		String s1;
		if ((s1=mlc.getString("skill"))==null) return;
		s1=SkillString.parseMessageSpecialChars(s1.substring(1,s1.length()-1));
		if ((sk=SkillAPI.getSkill(s1))==null) {
			MythicSkills.logger.warning("SkillAPI skill "+s1+" doesnt exist!");
		};
		d1=mlc.getDouble("damage",1d);
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity e1) {
		if(!Skill.isSkillDamage()) {
			sk.damage((LivingEntity)e1.getBukkitEntity(),d1,(LivingEntity)data.getCaster().getEntity().getBukkitEntity());
			return true;
		}
		return false;
	}
}
