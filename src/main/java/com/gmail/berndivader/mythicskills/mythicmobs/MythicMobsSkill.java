package com.gmail.berndivader.mythicskills.mythicmobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.gmail.berndivader.mythicskills.mythicmobs.mechanics.CastSkillAPI;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;

public class MythicMobsSkill
implements
Listener {
	
	public MythicMobsSkill() {
		MythicSkills.pluginmanager.registerEvents(this,MythicSkills.getPlugin());
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
		}
	}
}
