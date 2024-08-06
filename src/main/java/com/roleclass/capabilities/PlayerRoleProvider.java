package com.roleclass.capabilities;

import com.roleclass.roles.Role;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.roleclass.events.PlayerEvents.ANSI_GREEN;

@Mod.EventBusSubscriber(modid = "roleclass")
public class PlayerRoleProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<IPlayerRole> PLAYER_ROLE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private IPlayerRole playerRole = null;
    private final LazyOptional<IPlayerRole> optional = LazyOptional.of(this::createPlayerRole);

    private IPlayerRole createPlayerRole() {
        if (this.playerRole == null) {
            this.playerRole = new PlayerRole();
        }
        return this.playerRole;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == PLAYER_ROLE_CAPABILITY ? this.optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerRole().saveNBTData(nbt);
        LOGGER.info(ANSI_GREEN +"Serializing NBT: " + nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerRole().loadNBTData(nbt);
        LOGGER.info(ANSI_GREEN +"Deserializing NBT: " + nbt);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation("yourmodid", "playerrole"), new PlayerRoleProvider());
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IPlayerRole.class);
    }
}