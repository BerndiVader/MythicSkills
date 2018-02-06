package com.gmail.berndivader.mythicskills;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mythicskills.mythicmobs.MythicMobsHandler;
import com.gmail.berndivader.mythicskills.skillapi.SkillAPIHandler;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MobManager;

public class MythicSkills 
extends
JavaPlugin {
	static MythicSkills plugin;
	public static PluginManager pluginmanager;
	public static Logger logger;
	public static String str_DamageModifierTag="mm-skillapi-damagemodifier";
	public static MythicMobs mythicmobs;
	public static MobManager mobmanager;
	public static String str_LastSapiDamageCause="mm-skillapi-lastdamagecause";
	
	@Override
	public void onEnable() {
		plugin=this;
		pluginmanager=plugin.getServer().getPluginManager();
		logger=plugin.getLogger();
		
		boolean bl1;
		if ((bl1=pluginmanager.getPlugin("MythicMobs")==null)||(bl1=pluginmanager.getPlugin("SkillAPI")==null)) {
			this.onDisable();
		} else {
			mythicmobs=MythicMobs.inst();
			mobmanager=mythicmobs.getMobManager();
			new MythicMobsHandler();
			new SkillAPIHandler();
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
