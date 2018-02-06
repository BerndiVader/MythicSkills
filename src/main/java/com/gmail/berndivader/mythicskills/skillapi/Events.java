package com.gmail.berndivader.mythicskills.skillapi;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.event.PhysicalDamageEvent;
import com.sucy.skill.api.event.SkillDamageEvent;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.manager.AttributeManager;

public class Events 
implements
Listener {
	
	public Events(MythicSkills plugin) {
		MythicSkills.pluginmanager.registerEvents(this,plugin);
	}

	
    @EventHandler
	public void onSkillAPIfinalDamage(PhysicalDamageEvent e) {
    	System.err.println("pyhsical damage!");
	}
	
    @EventHandler
	public void onSkillAPIdamage(SkillDamageEvent e) {
        if ((e.getDamager() instanceof Player)
        		&&!e.getDamager().hasMetadata("NPC")
        		&&MythicSkills.mythicmobs.getAPIHelper().isMythicMob(e.getTarget())) {
        	Player p=(Player)e.getDamager();
            LivingEntity e1=e.getTarget();
			PlayerData pd=SkillAPI.getPlayerData((OfflinePlayer)p);
			for (String key:pd.getInvestedAttributes().keySet()) {
				System.err.println(key);
			}
            AttributeManager manager = SkillAPI.getAttributeManager();
            if (manager==null) return;
            for (String key : manager.getKeys()) {
            	int i1=pd.getAttribute(key);
                if (i1<=0) continue;
                System.err.println(e.getDamage()+":"+manager.getAttribute(key).modifyStat("skill-damage",e.getDamage(),i1));
                System.err.println(manager.getAttribute(key).getName());
            }
        }
	}
}
