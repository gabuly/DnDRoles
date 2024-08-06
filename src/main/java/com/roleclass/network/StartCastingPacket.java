package com.roleclass.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import com.roleclass.skill.skillbase.SkillManager;

import java.util.function.Supplier;

public class StartCastingPacket {
    private final String skillName;

    public StartCastingPacket(String skillName) {
        this.skillName = skillName;
    }

    public static void encode(StartCastingPacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.skillName);
    }

    public static StartCastingPacket decode(FriendlyByteBuf buffer) {
        return new StartCastingPacket(buffer.readUtf(32767));
    }

    public static void handle(StartCastingPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                SkillManager.startCasting(player, packet.skillName);
            }
        });
        context.setPacketHandled(true);
    }
}
