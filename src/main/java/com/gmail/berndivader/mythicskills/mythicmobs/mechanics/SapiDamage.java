package com.gmail.berndivader.mythicskills.mythicmobs.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.sucy.skill.api.event.SkillDamageEvent;
import com.sucy.skill.dynamic.TempEntity;
import com.sucy.skill.hook.NoCheatHook;
import com.sucy.skill.hook.PluginChecker;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillString;

public class SapiDamage 
extends
SkillMechanic 
implements
ITargetedEntitySkill {
	double d1;
	String s1;

	public SapiDamage(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		ASYNC_SAFE=false;
		if ((s1=mlc.getString(new String[] {"classification","class","c"},"default"))==null) return;
		s1=SkillString.parseMessageSpecialChars(s1.substring(1,s1.length()-1));
		d1=mlc.getDouble("damage",1d);
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity e1) {
		if (!e1.isLiving()) return false;
		LivingEntity t=(LivingEntity)e1.getBukkitEntity();
		LivingEntity c=(LivingEntity)data.getCaster().getEntity().getBukkitEntity();
        if (t instanceof TempEntity) return false;
        SkillDamageEvent event = new SkillDamageEvent((LivingEntity)data.getCaster().getEntity().getBukkitEntity(),t,this.d1,this.s1);
        Bukkit.getPluginManager().callEvent((Event)event);
        if (!event.isCancelled()) {
            if (c instanceof Player) {
                Player p=(Player)c;
                if (PluginChecker.isNoCheatActive()) NoCheatHook.exempt(p);
                t.setNoDamageTicks(0);
                t.damage(event.getDamage(),c);
                if (PluginChecker.isNoCheatActive()) NoCheatHook.unexempt(p);
            } else {
            	t.damage(event.getDamage(),c);
            }
        }
        return true;
	}
}
