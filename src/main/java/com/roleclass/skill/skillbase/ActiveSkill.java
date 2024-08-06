package com.roleclass.skill.skillbase;

import net.minecraft.world.entity.player.Player;


public abstract class ActiveSkill extends SkillBase {

    public ActiveSkill(String name, String description, int cooldown, int castingTime, int damage, boolean isAoE) {
        super(name, description, cooldown, castingTime, damage, isAoE);
    }


    protected boolean needsSpecificTarget() {
        return !isAoE; // Assuming non-AoE skills need specific targets
    }

    @Override
    public boolean requiresTargetSelection() {
        return isAoE || needsSpecificTarget();
    }

    protected void startCasting(Player player) {
        // Show casting bar
        // Lock player actions if necessary
        // Start casting timer
    }

    protected void completeCasting(Player player) {
        // Hide casting bar
        // Unlock player actions
        executeSkill(player);
    }

    protected abstract void executeSkill(Player player);

    @Override
    public void selectTargetOrArea(Player player) {
        // Logic for selecting a target or area
        if (isAoE) {
            // Show area selection indicator
        } else {
            // Highlight potential targets
        }
    }
}