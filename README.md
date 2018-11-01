# MythicSkills v0.2

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
