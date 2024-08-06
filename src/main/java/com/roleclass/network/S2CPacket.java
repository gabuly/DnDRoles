package com.roleclass.network;

import com.roleclass.capabilities.IPlayerRole;
import com.roleclass.capabilities.PlayerRoleProvider;
import com.roleclass.roles.Role;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CPacket {
    private final Role mainRole;
    private final int fighterLv;
    private final int magicianLv;
    private final int assassinLv;
    private final int clericLv;

    public S2CPacket(IPlayerRole role) {
        this.mainRole = role.getMainRole();
        this.fighterLv = role.getLevel(Role.FIGHTER);
        this.magicianLv = role.getLevel(Role.MAGICIAN);
        this.assassinLv = role.getLevel(Role.ASSASSIN);
        this.clericLv = role.getLevel(Role.CLERIC);
    }

    public static void encode(S2CPacket packet, FriendlyByteBuf buffer) {
        buffer.writeEnum(packet.mainRole);
        buffer.writeInt(packet.fighterLv);
        buffer.writeInt(packet.magicianLv);
        buffer.writeInt(packet.assassinLv);
        buffer.writeInt(packet.clericLv);
    }

    public static S2CPacket decode(FriendlyByteBuf buffer) {
        Role mainRole = buffer.readEnum(Role.class);
        int fighterLv = buffer.readInt();
        int magicianLv = buffer.readInt();
        int assassinLv = buffer.readInt();
        int clericLv = buffer.readInt();
        return new S2CPacket(mainRole, fighterLv, magicianLv, assassinLv, clericLv);
    }

    public static void handle(S2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(PlayerRoleProvider.PLAYER_ROLE_CAPABILITY).ifPresent(role -> {
                    role.setMainRole(packet.mainRole);
                    role.setLevel(Role.FIGHTER, packet.fighterLv);
                    role.setLevel(Role.MAGICIAN, packet.magicianLv);
                    role.setLevel(Role.ASSASSIN, packet.assassinLv);
                    role.setLevel(Role.CLERIC, packet.clericLv);
                });
            }
        });

        context.setPacketHandled(true);
    }

    private S2CPacket(Role mainRole, int fighterLv, int magicianLv, int assassinLv, int clericLv) {
        this.mainRole = mainRole;
        this.fighterLv = fighterLv;
        this.magicianLv = magicianLv;
        this.assassinLv = assassinLv;
        this.clericLv = clericLv;
    }
}
