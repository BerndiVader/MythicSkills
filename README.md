# MythicSkills v0.1

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
- sapidamage{classification=[STRING];damage=[VALUE]} @ENTITY_TARGET
```
+ classification=class=c: name of the sapi classification.
+ damage: amount of damage.


## MythicMobs conditions:

```yaml 
- lastsapidamagecause{cause=[SAPICLASSIFICATION]||[CLASSIFICATIONLIST];action=[BOOL]||[CAST]||[CASTINSTEAD]}
```
+ cause: the classification of the skill
+ action: what to do?
