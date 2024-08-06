package com.roleclass.skill.skills;

import com.roleclass.skill.skillbase.ActiveSkill;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;


    public class Fireball extends ActiveSkill {

        public Fireball() {
            super("fireball", "A powerful fireball", 10, 60, 5, false);
        }

        @Override
        public void activate(Player player) {

        }

        @Override
        public void selectTargetOrArea(Player player) {
            // Logic for selecting a target or area
            if (isAoE) {
                // Show area selection indicator

            } else {
                // Highlight potential targets

            }
        }

        @Override
        protected void startCasting(Player player) {

            // Show casting bar
            // Lock player actions if necessary
            // Start casting timer

        }

        @Override
        protected void executeSkill(Player player) {
            player.hurt(DamageSource.IN_FIRE, 2.0F);
            System.out.println(player.getName().getString() + " activated Fireball with damage: " + damage);
        }


        private void applyAoEDamage(Player player) {
            // Logic to apply AoE damage

            // Actual AoE damage application logic here
        }

        private void applyTargetedDamage(Player player) {
            // Logic to apply targeted damage

            // Actual targeted damage application logic here
        }
    }