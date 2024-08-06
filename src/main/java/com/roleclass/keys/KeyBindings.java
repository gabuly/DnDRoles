package com.roleclass.keys;

import com.mojang.blaze3d.platform.InputConstants;
import com.roleclass.network.ModMessages;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.roleclass.events.PlayerEvents.ANSI_GREEN;

@Mod.EventBusSubscriber(modid = "roleclass", value = Dist.CLIENT)
public class KeyBindings {
    public static final String FIREBALL_KEY_NAME = "火球术";


    public static final KeyMapping FIREBALL_KEY = new KeyMapping(FIREBALL_KEY_NAME,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R,
            "key.categories.roleclass");

}
