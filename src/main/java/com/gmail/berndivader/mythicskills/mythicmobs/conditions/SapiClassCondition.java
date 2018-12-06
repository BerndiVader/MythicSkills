package com.gmail.berndivader.mythicskills.mythicmobs.conditions;

import java.util.Collection;

import org.bukkit.entity.Player;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerClass;
import com.sucy.skill.api.player.PlayerData;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillString;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;

public 
class 
SapiClassCondition 
extends
AbstractCustomCondition 
implements
IEntityCondition
{
	String classes[];
	boolean any;
	int length;

	public SapiClassCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		String s1=mlc.getString(new String[] {"class","classes"},"ANY");
		if(s1.length()>0&&s1.charAt(0)=='"') s1=s1.substring(1,s1.length()-1);
		classes=SkillString.parseMessageSpecialChars(s1).split(",");
		any=classes[0].equalsIgnoreCase("ANY");
		length=classes.length;
	}

	@Override
	public boolean check(AbstractEntity abstract_entity) {
		if(abstract_entity.isPlayer()) {
			PlayerData player_data=SkillAPI.getPlayerData((Player)abstract_entity.getBukkitEntity());
			if(any&&player_data.hasClass()) return true;
			Collection<PlayerClass>player_classes=player_data.getClasses();
			for(int i1=0;i1<length;i1++) {
				for(PlayerClass player_class:player_classes) {
					if (player_class.getData().getName().equals(classes[i1])) return true;
				}
			}
		}
		return false;
	}
}
