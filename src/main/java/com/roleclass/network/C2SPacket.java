package com.roleclass.network;

import com.roleclass.capabilities.IPlayerRole;
import com.roleclass.capabilities.PlayerRoleProvider;
import com.roleclass.roles.Role;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SPacket {
    private final Role mainRole;

    public C2SPacket(Role mainRole) {
        this.mainRole = mainRole;
    }

    public static void encode(C2SPacket packet, FriendlyByteBuf buffer) {
        buffer.writeEnum(packet.mainRole);
    }

    public static C2SPacket decode(FriendlyByteBuf buffer) {
        return new C2SPacket(buffer.readEnum(Role.class));
    }

    public static void handle(C2SPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        ServerPlayer player = context.getSender();

        context.enqueueWork(() -> {
            if (player != null) {
                player.getCapability(PlayerRoleProvider.PLAYER_ROLE_CAPABILITY).ifPresent(role -> {
                    role.setMainRole(packet.mainRole);
                });
            }
        });

        context.setPacketHandled(true);
    }
}
