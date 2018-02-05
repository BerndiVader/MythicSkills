package com.gmail.berndivader.mythicskills;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mythicskills.mythicmobs.MythicMobsSkill;
import com.gmail.berndivader.mythicskills.skillapi.SkillAPISkills;

public class MythicSkills 
extends
JavaPlugin {
	static MythicSkills plugin;
	public static PluginManager pluginmanager;
	public static Logger logger;
	
	@Override
	public void onEnable() {
		plugin=this;
		pluginmanager=plugin.getServer().getPluginManager();
		logger=plugin.getLogger();
		
		boolean bl1;
		if ((bl1=pluginmanager.getPlugin("MythicMobs")==null)||(bl1=pluginmanager.getPlugin("SkillAPI")==null)) {
			this.onDisable();
		} else {
			new MythicMobsSkill();
			new SkillAPISkills();
		}
	}
	
	@Override
	public void onDisable() {
		pluginmanager.disablePlugin(this);
	}

	public static MythicSkills getPlugin() {
		return plugin;
	}	
	
}
