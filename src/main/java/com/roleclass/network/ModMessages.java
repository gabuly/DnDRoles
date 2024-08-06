package com.roleclass.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("roleclass", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        CHANNEL.messageBuilder(C2SPacket.class, id++)
                .encoder(C2SPacket::encode)
                .decoder(C2SPacket::decode)
                .consumerMainThread(C2SPacket::handle)
                .add();

        CHANNEL.messageBuilder(StartCastingPacket.class, id++)
                .encoder(StartCastingPacket::encode)
                .decoder(StartCastingPacket::decode)
                .consumerMainThread(StartCastingPacket::handle)
                .add();

        CHANNEL.registerMessage(id++, EndCastingOnlyPacket.class, EndCastingOnlyPacket::encode, EndCastingOnlyPacket::decode, EndCastingOnlyPacket::handle);

    }


    public static void sendToServer(StartCastingPacket packet) {
        CHANNEL.sendToServer(packet);
    }

    public static void sendToClient(S2CPacket packet, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
}
