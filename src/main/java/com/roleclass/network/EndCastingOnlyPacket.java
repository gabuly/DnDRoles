package com.roleclass.network;

import com.roleclass.skill.skillbase.SkillManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EndCastingOnlyPacket {
    public EndCastingOnlyPacket() {
        // Empty constructor
    }

    public static void encode(EndCastingOnlyPacket msg, FriendlyByteBuf buffer) {
        // No data to encode
    }

    public static EndCastingOnlyPacket decode(FriendlyByteBuf buffer) {
        return new EndCastingOnlyPacket();
    }

    public static void handle(EndCastingOnlyPacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                SkillManager.interruptCasting(player);
            }
        });
        context.setPacketHandled(true);
    }
}
