package com.gmail.berndivader.mythicskills.skillapi.mechanics;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;
import com.rit.sucy.config.parse.DataSection;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;

public 
class 
SoundEffectMechanic 
extends
CustomEffectComponent 
{
	static String TYPE="sound",VOLUME="volume",PITCH="pitch",DEBUG="debug";
	
	String type;
	boolean debug;
	
	@Override
	public String getKey() {
		return "soundeffect";
	}
	
	@Override
	public boolean execute(LivingEntity caster, int power, List<LivingEntity>targets) {
		
        float volume=(float)this.parseValues(caster,VOLUME,power,0.0);
        float pitch=(float)this.parseValues(caster,PITCH,power,0.0);
		if(debug) System.err.println(type+":"+volume+":"+pitch);
		
		int size=targets.size();
		String world_name=new String();
		List<Player>players=new ArrayList<>();
		for(int i2=0;i2<size;i2++) {
			LivingEntity target=targets.get(i2);
			if(target==null) continue;
			if(!target.getWorld().getName().equals(world_name)) {
				world_name=target.getWorld().getName();
				players=target.getWorld().getPlayers();
			}
			if(players.size()==0) continue;
			if(debug) System.err.println(target.getType());
			playSoundAtPlayer(type,volume,pitch,target.getLocation(),players);
		}
		players=null;
		return size>0;
	}

	private static void playSoundAtPlayer(String name,float volume,float pitch,Location target,List<Player>players) {
		int size=players.size();
		for(int i1=0;i1<size;i1++) {
			Player player=players.get(i1);
			if(player!=null&&player.isOnline()) {
	            player.playSound(target,name,volume,pitch);
			}
		}
	}

	@Override
	public String getDescription() {
		return "play soundeffects at players";
	}

	@Override
	public List<EditorOption> getOptions() {
		return ImmutableList.of(
				EditorOption.text(TYPE,"Sound","Name of the sound to play.","entity.enderdragon.growl"),
				EditorOption.number(VOLUME,"Volume","Set the volume/sphere of the sound is playes/heared.",1.0f,0f),
				EditorOption.number(PITCH,"Pitch","Pitch",1.0f,0f),
				EditorOption.text(DEBUG,"Debug","Debug","false")
		);
	}

	@Override
	public ComponentType getType() {
		return ComponentType.MECHANIC;
	}
	
	@Override
	public void load(DynamicSkill dynamicSkill, DataSection dataSection) {
		super.load(dynamicSkill, dataSection);
		type=settings.getString(TYPE,"entity.enderdragon.growl");
        debug=settings.getBool(DEBUG, false);
	}

}
