package com.roleclass.events;

import com.roleclass.PlayerRoleMod;
import com.roleclass.keys.KeyBindings;
import com.roleclass.network.EndCastingOnlyPacket;
import com.roleclass.network.ModMessages;
//import com.roleclass.network.SkillUsePacket;
import com.roleclass.network.StartCastingPacket;
import com.roleclass.skill.skillbase.SkillRegistry;
import com.roleclass.skill.skills.Fireball;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.roleclass.events.PlayerEvents.ANSI_GREEN;


public class ClientEvents {

    @Mod.EventBusSubscriber(modid = "roleclass",value= Dist.CLIENT)
    public static class ClientForgeEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KeyBindings.FIREBALL_KEY.consumeClick()) {
            ModMessages.CHANNEL.sendToServer(new StartCastingPacket("fireball"));
            LOGGER.info(ANSI_GREEN +"Client   Keyyyyyyyyyyyyyyyyyyyyy   pressed: " );
        }
    }


    @SubscribeEvent

    public static void onMouseInput(InputEvent.MouseButton event) {
        Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    int button = event.getButton();
                    if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                        // Send packet to server to interrupt casting
                        ModMessages.CHANNEL.sendToServer(new EndCastingOnlyPacket());
                        // Client-side logic to stop casting bar

                    }
                }
            }
        }




    }

    @Mod.EventBusSubscriber(modid =  PlayerRoleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(KeyBindings.FIREBALL_KEY);
            LOGGER.info(ANSI_GREEN +"Keymappppppppppppppppppppp registered: " );
        }

    }
}
