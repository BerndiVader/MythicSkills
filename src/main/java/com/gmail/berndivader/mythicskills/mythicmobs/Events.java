package com.gmail.berndivader.mythicskills.mythicmobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.gmail.berndivader.mythicskills.mythicmobs.conditions.LastSapiDamageCause;
import com.gmail.berndivader.mythicskills.mythicmobs.conditions.SapiClassCondition;
import com.gmail.berndivader.mythicskills.mythicmobs.conditions.SapiPlayerConditions;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.CastSkillAPI;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.DamageSkillAPI;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.SapiDamage;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;

public class Events 
implements
Listener {
	
	public Events(MythicSkills plugin) {
		MythicSkills.pluginmanager.registerEvents(this,plugin);
	}

	@EventHandler
	public void onMythicMobsLoadMechanics(MythicMechanicLoadEvent e) {
		String s1=e.getMechanicName().toLowerCase();
		switch(s1) {
		case "castskillapi":
		case "sapiskill":
			e.register(new CastSkillAPI(e.getContainer().getConfigLine(),e.getConfig()));
			break;
		case "damageskillapi":
		case "sapiskilldamage":
			e.register(new DamageSkillAPI(e.getContainer().getConfigLine(),e.getConfig()));
			break;
		case "sapidamage":
			e.register(new SapiDamage(e.getContainer().getConfigLine(),e.getConfig()));
			break;
		}
	}
	
	@EventHandler
	public void onMythicMobsLoadConditions(MythicConditionLoadEvent e) {
		String s1=e.getConditionName().toUpperCase();
		switch(s1) {
			case "LASTSAPIDAMAGECAUSE":
				e.register(new LastSapiDamageCause(e.getConfig().getLine(),e.getConfig()));
				break;
			case "SAPICLASS":
				e.register(new SapiClassCondition(e.getConfig().getLine(), e.getConfig()));
				break;
			case "SAPICANPROFESS":
			case "SAPISKILL":
			case "SAPISKILLLEVEL":
			case "SAPIATTRIBUTE":
			case "SAPIATTRIBUTEPOINTS":
			case "SAPIMANA":
				e.register(new SapiPlayerConditions(e.getConfig().getLine(),e.getConfig()));
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
