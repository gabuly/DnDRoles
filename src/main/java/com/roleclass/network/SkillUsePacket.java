//package com.roleclass.network;
//
//import com.roleclass.skill.skillbase.SkillManager;
//
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraftforge.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
//public class SkillUsePacket {
//    private final String skillName;
//
//    public SkillUsePacket(String skillName) {
//        this.skillName = skillName;
//    }
//
//    public static void encode(SkillUsePacket packet, FriendlyByteBuf buffer) {
//        buffer.writeUtf(packet.skillName);
//    }
//
//    public static SkillUsePacket decode(FriendlyByteBuf buffer) {
//        return new SkillUsePacket(buffer.readUtf(32767));
//    }
//
//    public static void handle(SkillUsePacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
//        NetworkEvent.Context context = contextSupplier.get();
//        context.enqueueWork(() -> {
//            ServerPlayer player = context.getSender();
//            if (player != null) {
//                SkillManager.activate(player, packet.skillName); // Example casting time
//            }
//        });
//        context.setPacketHandled(true);
//    }
//}