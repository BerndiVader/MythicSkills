package com.gmail.berndivader.mythicskills.skillapi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.sucy.skill.api.event.SkillDamageEvent;

public class Events 
implements
Listener {
	
	public Events(MythicSkills plugin) {
		MythicSkills.pluginmanager.registerEvents(this,plugin);
	}
	
    @EventHandler
	public void onSkillAPIdamage(SkillDamageEvent e) {
    	e.getTarget().setMetadata(MythicSkills.str_LastSapiDamageCause,new FixedMetadataValue(MythicSkills.getPlugin(),e.getClassification()));
	}
}
