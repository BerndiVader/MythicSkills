package com.gmail.berndivader.mythicskills.mythicmobs.mechanics;

import org.bukkit.entity.LivingEntity;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.skills.Skill;
import com.sucy.skill.api.skills.SkillShot;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillString;

public class CastSkillAPI 
extends 
SkillMechanic 
implements
ITargetedEntitySkill,
ITargetedLocationSkill,
INoTargetSkill {
	String s1;
	boolean bl1;
	Skill sk;

	public CastSkillAPI(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		if ((s1=mlc.getString("skill"))==null) return;
		s1=SkillString.parseMessageSpecialChars(s1.substring(1,s1.length()-1));
		if ((sk=SkillAPI.getSkill(s1))==null) {
			MythicSkills.logger.warning("SkillAPI skill "+s1+" doesnt exist!");
			return;
		};
		bl1=mlc.getBoolean("uml",false);
	}

	@Override
	public boolean cast(SkillMetadata data) {
		return false;
	}

	@Override
	public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
		return false;
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
		return castSkill(data,target,null);
	}
	
	boolean castSkill(SkillMetadata data,AbstractEntity e1,AbstractLocation l1) {
		ActiveMob am;
		int i1=1;
		if (bl1&&(am=(ActiveMob)data.getCaster())!=null) i1=am.getLevel();
		SkillShot ss=(SkillShot)sk;
		System.err.println("loaded skill: "+sk.getName());
		if (ss.cast((LivingEntity)data.getCaster().getEntity().getBukkitEntity(),i1)) {
			System.err.println("executed: true");
		} else {
			System.err.println("executed: false");
		};
		return true;
	}

}
