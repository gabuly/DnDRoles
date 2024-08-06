package com.roleclass.skill.skillbase;

public abstract class PassiveSkill extends SkillBase {

    public PassiveSkill(String name, String description, int cooldown, int castingTime, int damage, boolean isAoE) {
        super(name, description, cooldown, castingTime, damage, isAoE);
    }

    // Implementation for passive skills
}