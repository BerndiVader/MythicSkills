package com.gmail.berndivader.mythicskills.skillapi.mechanics;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.berndivader.mythicskills.MythicSkills;
import com.google.common.collect.ImmutableList;
import com.rit.sucy.config.parse.DataSection;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.custom.EditorOption;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
import io.lumine.xikage.mythicmobs.skills.Skill;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;

public 
class 
CastMythicMechanicMechanic 
extends
CustomEffectComponent 
{
	static String SKILLNAME="skill",DEBUG="debug";
	Optional<Skill>skill=Optional.empty();
	String str_skill;
	boolean debug;
	
	@Override
	public String getKey() {
		return "mythicskill";
	}
	
	@Override
	public boolean execute(LivingEntity caster, int power, List<LivingEntity>targets) {
	
        if(!skill.isPresent()) {
   			System.err.println("Skill "+str_skill+" is not present!");
   			return false;
        }
		
		if(debug) System.err.println("Executing... "+SKILLNAME+":"+str_skill);
		
		int size=targets.size();
		if(targets.size()>0) {
			AbstractEntity abstract_caster=BukkitAdapter.adapt(caster);
			HashSet<AbstractEntity>abstract_targets=new HashSet<>();
			HashSet<AbstractLocation>abstract_locations=new HashSet<>();
			for(int i1=0;i1<size;i1++) {
				LivingEntity target=targets.get(i1);
				if(target.getName().equals("Location")) {
					abstract_locations.add(BukkitAdapter.adapt(target.getLocation()));
				} else {
					abstract_targets.add(BukkitAdapter.adapt(targets.get(i1)));
				}
			}
			SkillMetadata data=new SkillMetadata(SkillTrigger.API,new GenericCaster(abstract_caster),abstract_caster,abstract_caster.getLocation(),abstract_targets,abstract_locations,power);
			Skill s=skill.get();
			if (s.isUsable(data)) s.execute(data);
		}
		return size>0;
		
	}
	
	@Override
	public String getDescription() {
		return "play a mythicmobs skill";
	}

	@Override
	public List<EditorOption> getOptions() {
		return ImmutableList.of(
				EditorOption.text(SKILLNAME,"Skill","A valid mythicmobs skill.", ""),
				EditorOption.text(DEBUG, "Debug", "Show debug messages", "false")
		);
	}

	@Override
	public ComponentType getType() {
		return ComponentType.MECHANIC;
	}
	
	@Override
	public void load(DynamicSkill dynamicSkill, DataSection dataSection) {
		super.load(dynamicSkill, dataSection);
		new BukkitRunnable() {
			@Override
			public void run() {
				skill=MythicSkills.mythicmobs.getSkillManager().getSkill(settings.getString(SKILLNAME));
		        debug=getSettings().getBool(DEBUG, false);
	    		str_skill=getSettings().getString(SKILLNAME);
			}
		}.runTask(MythicSkills.getPlugin());
	}

}
