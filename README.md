# MythicSkills v0.3

## MythicMobs mechanics:

```yaml 
- castskillapi{skill="[NAME]";uml=[BOOL]} @notargetskill
```
+ skill: name of the skillapi skill. Possible types: dynamic and target skills.
+ uml: (u)use(m)mythiymobs(l)level true or false(default)

```yaml 
- damageskillapi{skill="[NAME]";damage=[VALUE]} @ENTITY_TARGET
```
+ skill: name of the skillapi skill.
+ damage: amount of damage.

```yaml 
- sapidamage{classification=[STRING];damage=[VALUE];noattacker=[BOOL]} @ENTITY_TARGET
```
+ classification=class=c: name of the sapi classification.
+ damage: amount of damage.
+ noattacker: if no attacker entity should be used. default is false.


## MythicMobs conditions:

```yaml 
- lastsapidamagecause{cause=[SAPICLASSIFICATION]||[CLASSIFICATIONLIST];action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ cause: the classification of the skill
+ action: what to do?

```yaml 
- sapiclass{classes=[SAPICLASSNAMNE];action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ classes=class=c: the classname (casesensitive) an array like: "1stclass,2ndclass,3rdclass" or "ANY" if player just requires to be in class.
+ action: what to do?

```yaml 
- sapiskill{skill="[SINGLE]||[LIST]";action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ skill=skills: a SkillAPI skill as single entry or as list.
+ action: what to do?

```yaml 
- sapiskilllevel{skill="[SINGLE]||[LIST]";level=[RANGEDNUMBER];action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ skill=skills: a SkillAPI skill as single entry or as list.
+ level: a ranged number like "1to2" or "<5" etc..
+ action: what to do?

```yaml 
- sapicanprofess{class="[SINGLE]||[LIST]";action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ class=classes: a SkillAPI class as single entry or as list.
+ action: what to do?

```yaml 
- sapiattribute{attribute="[SINGLE]||[LIST]";action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ attribute=attributes: a SkillAPI attribute as single entry or as list
+ action: what to do?

```yaml 
- sapiattributepoints{points=[RANGEDVALUE];action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ points: a ranged value like "2to10" or ">0"
+ action: what to do?

```yaml 
- sapimana{value=[RANGEDNUMBER];action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ value: a ranged number like "1to2" or "<5" etc..
+ action: what to do?


## Sapi CustomEffectMechanics:

### soundeffect

+ sound: the sound name to play
+ volume-base: volume
+ pitch-base: pitch
+ debug: true/false(default) if true message posted in console at execution of mechanic.

```yaml
soundeffectba: 
  type: 'mechanic'
  indicator: '3D'
  data: 
    icon-key: ''
    counts: 'True'
    sound: 'entity.wither.shoot'
    volume-base: '1.5'
    volume-scale: '0'
    pitch-base: '2'
    pitch-scale: '0'
    debug: 'false'
    indicator: '2D'
  children:  {}
```

### mythicskill

+ skill: the mythicmobs skill name
+ debug: true/false(default) for debug messages.

```yaml
mythicskill-b: 
  type: 'mechanic'
  indicator: '3D'
  data: 
    icon-key: ''
    counts: 'True'
    skill: 'some_skill_blubb'
    debug: 'false'
    indicator: '2D'
  children:  {}
```
