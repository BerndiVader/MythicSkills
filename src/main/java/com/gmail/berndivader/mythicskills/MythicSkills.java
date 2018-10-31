package com.gmail.berndivader.mythicskills;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mythicskills.mythicmobs.MythicMobsHandler;
import com.gmail.berndivader.mythicskills.skillapi.SkillAPIHandler;
import com.gmail.berndivader.mythicskills.skillapi.mechanics.SoundEffectMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MobManager;

public class MythicSkills 
extends
JavaPlugin
implements
SkillPlugin
{
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
	
	@Override
	public void registerSkills(SkillAPI api) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerClasses(SkillAPI api) {
		// TODO Auto-generated method stub
	}
	
    @Override
    public List<CustomEffectComponent> getComponents() {
        return ImmutableList.of(
        		new SoundEffectMechanic()
        );
    }	
}
