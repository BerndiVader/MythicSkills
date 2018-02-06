package com.gmail.berndivader.mythicskills.mythicmobs.mechanics;

import org.bukkit.entity.LivingEntity;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.skills.Skill;
import com.sucy.skill.api.skills.SkillShot;
import com.sucy.skill.api.skills.TargetSkill;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillString;

public class CastSkillAPI 
extends 
SkillMechanic 
implements
ITargetedEntitySkill,
INoTargetSkill {
	String s1;
	boolean bl1;
	Skill sk;

	public CastSkillAPI(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		ASYNC_SAFE=false;
		if ((s1=mlc.getString("skill"))==null) return;
		s1=SkillString.parseMessageSpecialChars(s1.substring(1,s1.length()-1));
		if ((sk=SkillAPI.getSkill(s1))==null||!sk.canCast()) {
			MythicSkills.logger.warning("SkillAPI skill "+s1+" doesnt exist or cant be casted!");
		};
		bl1=mlc.getBoolean("uml",false);
	}

	@Override
	public boolean cast(SkillMetadata data) {
		return castSkill(data,null,null);
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
		return castSkill(data,target,null);
	}
	
	boolean castSkill(SkillMetadata data,AbstractEntity e1,AbstractLocation l1) {
		ActiveMob am;
		int i1=1;
		boolean bl2=false;
		if (bl1&&(am=(ActiveMob)data.getCaster())!=null) i1=am.getLevel();
		if (sk instanceof SkillShot) {
			bl2=((SkillShot)sk).cast((LivingEntity)data.getCaster().getEntity().getBukkitEntity(),i1);
		} else if (sk instanceof TargetSkill) {
			bl2=((TargetSkill)sk).cast((LivingEntity)data.getCaster().getEntity().getBukkitEntity(),(LivingEntity)e1.getBukkitEntity(),i1,false);
		}
		if (bl2) this.setCooldown(data.getCaster(),(float)sk.getCooldown(i1));
		return bl2;
	}

}
