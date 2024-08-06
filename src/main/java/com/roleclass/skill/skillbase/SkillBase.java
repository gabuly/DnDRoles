package com.roleclass.skill.skillbase;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.player.Player;

public abstract class SkillBase {
    protected final String name;
    protected final String description;
    protected final int cooldown;
    protected final int castingTime; // Casting time in ticks (20 ticks = 1 second)
    protected final int damage; // Damage value, 0 means non-damage skill
    protected final boolean isAoE; // Indicates if the skill is AoE

    public SkillBase(String name, String description, int cooldown, int castingTime, int damage, boolean isAoE) {
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
        this.castingTime = castingTime;
        this.damage = damage;
        this.isAoE = isAoE;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCastingTime() {
        return castingTime;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAoE() {
        return isAoE;
    }

    public abstract void activate(Player player);

    public abstract void selectTargetOrArea(Player player);

    public boolean requiresTargetSelection() {
        return false; // Default to false for non-active skills
    }
}