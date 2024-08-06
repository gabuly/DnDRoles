package com.roleclass.skill.skillbase;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class SkillManager {
    private static final Map<UUID, CastingInfo> castingPlayers = new HashMap<>();

    private static class CastingInfo {
        final ActiveSkill skill;
        int remainingCastTime;

        CastingInfo(ActiveSkill skill, int castingTime) {
            this.skill = skill;
            this.remainingCastTime = castingTime;
        }
    }


//
//    public static void activate(ServerPlayer player, String skillName) {
//        SkillBase skill = SkillRegistry.getSkill(skillName);
//        //startCasting(player,(ActiveSkill) skill);
//        System.out.println("mANAGER USINGGGGGGGGGG:      " + skill);
//    }


    public static void startCasting(ServerPlayer player, String skillName) {
        UUID playerId = player.getUUID();
        ActiveSkill skill = SkillRegistry.getSkill(skillName);
        castingPlayers.put(playerId, new CastingInfo(skill, skill.getCastingTime()));

        if (skill.getCastingTime() > 0) {
            MinecraftForge.EVENT_BUS.register(SkillManager.class);
        // Notify clients about the casting state (e.g., show casting bar)
            casting(player, skill);
        } else {

            startExecution(player, skill);

        }
    }


    public static void interruptCasting(ServerPlayer player) {
        UUID playerId = player.getUUID();
        if (castingPlayers.containsKey(playerId)) {
            castingPlayers.remove(playerId);
            System.out.println("!!!!!!!!!!!!interruppppppppppppted!!!!!!!! "  );
            // Notify clients to hide the casting bar if needed
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.player;
            UUID playerId = player.getUUID();

            if (castingPlayers.containsKey(playerId)) {
                CastingInfo info = castingPlayers.get(playerId);

                if (info.remainingCastTime== 0) {

                    startExecution(player, info.skill);

                }else {

                    info.remainingCastTime--;
                    System.out.println("CATING..............   " + info.remainingCastTime );
                }

            }
        }
    }

    public static void startExecution(ServerPlayer player, ActiveSkill skill) {
        UUID playerId = player.getUUID();
        castingPlayers.remove(playerId);
        skill.executeSkill(player);

    }

    private static void casting(ServerPlayer player, ActiveSkill skill){

    }

    private static void completeCasting(ServerPlayer player, ActiveSkill skill) {
        // Hide casting bar
        // Unlock player actions
        startExecution(player, skill);
    }

    private static void selectTarget(ServerPlayer player, ActiveSkill skill) {
//        if (skill.requiresTargetSelection()) {
//            skill.selectTargetOrArea(player);
//        } else {

//        }
    }


    private static void applyPassiveSkill(ServerPlayer player, SkillBase skill) {
        // Logic to apply passive skill effects, e.g., periodic buffs
    }
}