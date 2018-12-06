package com.gmail.berndivader.mythicskills.mythicmobs.conditions;

import org.bukkit.entity.Player;

import com.gmail.berndivader.mythicskills.utils.RangedDouble;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.classes.RPGClass;
import com.sucy.skill.api.player.PlayerData;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillString;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;

public 
class 
SapiPlayerConditions 
extends
AbstractCustomCondition 
implements
IEntityCondition
{
	String arr1[],type;
	RangedDouble range;
	int length;
	
	public SapiPlayerConditions(String line, MythicLineConfig mlc) {
		super(line,mlc);
		String s1=mlc.getString(new String[] {"skill","skills","attribute","attributes"},new String());
		if(s1.length()>0&&s1.charAt(0)=='"') s1=s1.substring(1,s1.length()-1);
		arr1=SkillString.parseMessageSpecialChars(s1).split(",");
		range=new RangedDouble(mlc.getString(new String[] {"value","level","points"},">0"));
		length=arr1.length;
		type=line.toUpperCase().split("\\{")[0];
	}

	@Override
	public boolean check(AbstractEntity abstract_entity) {
		if(abstract_entity.isPlayer()) {
			PlayerData player_data=SkillAPI.getPlayerData((Player)abstract_entity.getBukkitEntity());
			for(int i1=0;i1<length;i1++) {
				switch(type) {
					case "SAPISKILL":
						if(player_data.hasSkill(arr1[i1])) return true;
						break;
					case "SAPISKILLLEVEL":
						return range.equals(player_data.getSkillLevel(arr1[i1]));
					case "SAPICANPROFESS":
						RPGClass rpg_class=SkillAPI.getClass(arr1[i1]);
						if(rpg_class!=null) return player_data.canProfess(rpg_class);
						break;
					case "SAPIATTRIBUTE":
						if(player_data.hasAttribute(arr1[i1])) return true;
						break;
					case "SAPIATTRIBUTEPOINTS":
						return range.equals(player_data.getAttributePoints());
					case "SAPIMANA":
						return range.equals(player_data.getMana());
				}
			}
		}
		return false;
	}
}
