package com.gmail.berndivader.mythicskills.skillapi;

import java.util.List;
import java.util.Optional;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import com.gmail.berndivader.mythicmobsext.utils.Utils;
import com.gmail.berndivader.mythicskills.MythicSkills;
import com.sucy.skill.api.event.SkillDamageEvent;

import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class Events 
implements
Listener {
	
	public Events(MythicSkills plugin) {
		MythicSkills.pluginmanager.registerEvents(this,plugin);
	}
	
    @EventHandler
	public void onSkillAPIdamage(SkillDamageEvent e) {
    	e.getTarget().setMetadata(MythicSkills.str_LastSapiDamageCause,new FixedMetadataValue(MythicSkills.getPlugin(),e.getClassification()));
    	Optional<ActiveMob>oam=Utils.mobmanager.getActiveMob(e.getTarget().getUniqueId());
    	if(oam.isPresent()) {
    		ActiveMob am=oam.get();
        	String s3=e.getClassification().toUpperCase();
    		List<String>ls=am.getType().getConfig().getStringList("DamageModifiers");
    		for(String s1:ls) {
    			if(s1.toUpperCase().startsWith("SAPICLASSIFIER")) {
    				String[]arr1=s1.toUpperCase().split(" ");
    				if(s3.equals(arr1[1])) {
    					int i1=Integer.parseInt(arr1[2]);
    					e.setDamage(e.getDamage()*i1);
    				}
    			}
    		}
    	}
	}
}
