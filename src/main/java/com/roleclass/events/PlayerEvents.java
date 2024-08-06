package com.roleclass.events;

import com.roleclass.capabilities.IPlayerRole;
import com.roleclass.capabilities.PlayerRoleProvider;
import com.roleclass.roles.Role;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod.EventBusSubscriber(modid = "roleclass")
public class PlayerEvents {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";




    @SubscribeEvent
    public static void onPlayerXPChange(PlayerXpEvent.XpChange event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            int xpAmount = event.getAmount();
            LazyOptional<IPlayerRole> roleCap = player.getCapability(PlayerRoleProvider.PLAYER_ROLE_CAPABILITY);
            roleCap.ifPresent(role -> {
                role.addXP(role.getMainRole(), xpAmount);
                System.out.println("XP added to " + role.getMainRole() + ": " + xpAmount + ", Current XP: " + role.getXP(role.getMainRole()));
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            LazyOptional<IPlayerRole> roleCap = player.getCapability(PlayerRoleProvider.PLAYER_ROLE_CAPABILITY);
            roleCap.ifPresent(role -> {
                role.setMainRole(Role.ASSASSIN);
                System.out.println("Role switched to: " + role.getMainRole());
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerPlaceBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            LazyOptional<IPlayerRole> roleCap = player.getCapability(PlayerRoleProvider.PLAYER_ROLE_CAPABILITY);
            roleCap.ifPresent(role -> {
                role.setMainRole(Role.MAGICIAN);
                System.out.println("Role switched to: " + role.getMainRole());
            });
        }
    }
}
