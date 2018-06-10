package com.gmail.berndivader.mythicskills.mythicmobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.gmail.berndivader.mythicskills.mythicmobs.conditions.LastSapiDamageCause;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.CastSkillAPI;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.DamageSkillAPI;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.SapiDamage;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;

public class Events 
implements
Listener {
	
	public Events(MythicSkills plugin) {
		MythicSkills.pluginmanager.registerEvents(this,plugin);
	}

	@EventHandler
	public void onMythicMobsLoadMechanics(MythicMechanicLoadEvent e) {
		String s1=e.getMechanicName().toLowerCase();
		SkillMechanic skill;
		switch(s1) {
		case "castskillapi":
			skill=new CastSkillAPI(e.getContainer().getConfigLine(),e.getConfig());
			e.register(skill);
			break;
		case "damageskillapi":
			skill=new DamageSkillAPI(e.getContainer().getConfigLine(),e.getConfig());
			e.register(skill);
			break;
		case "sapidamage":
			e.register(new SapiDamage(e.getContainer().getConfigLine(),e.getConfig()));
			break;
		}
	}
	
	@EventHandler
	public void onMythicMobsLoadConditions(MythicConditionLoadEvent e) {
		String s1=e.getConditionName().toLowerCase();
		switch(s1) {
		case "lastsapidamagecause":
			SkillCondition c=new LastSapiDamageCause(e.getConfig().getLine(),e.getConfig());
			e.register(c);
			break;
		}
	}
	
	@EventHandler
	public void onMythicMobsSpawnEvent(MythicMobSpawnEvent e) {
		String s1=e.getMobType().getConfig().getString("SapiDamageModifier");
		if (s1!=null) {
			e.getEntity().setMetadata(MythicSkills.str_DamageModifierTag,new FixedMetadataValue(MythicSkills.getPlugin(),s1));
		}
	}
}
