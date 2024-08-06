package com.roleclass;


import com.roleclass.capabilities.PlayerRoleProvider;
import com.roleclass.keys.KeyBindings;
import com.roleclass.network.ModMessages;
import com.roleclass.skill.skillbase.SkillRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.roleclass.events.PlayerEvents.ANSI_GREEN;

@Mod("roleclass")
@Mod.EventBusSubscriber
public class PlayerRoleMod {
    public static final String MOD_ID = "roleclass";
    public PlayerRoleMod(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);


    }
    private void commonSetup(final FMLCommonSetupEvent event){
                ModMessages.register();
        SkillRegistry.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(KeyBindings.class);
        LOGGER.info(ANSI_GREEN +"Clientttttttttttt setuped: " );
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        PlayerRoleProvider.registerCapabilities(event);
    }





//    @Mod.EventBusSubscriber(modid = "roleclass", bus = Mod.EventBusSubscriber.Bus.MOD)
//    public static class ClientModEvents {
//        @SubscribeEvent
//        public static void onClientSetup(FMLClientSetupEvent event) {
//
//        }
//    }
}
//
////    @SubscribeEvent
////    public static void onRegisterCapability(RegisterCapabilitiesEvent event) {
////    event.register(PlayerMira.class);
////}

//    @SubscribeEvent
//    public static void onXpGain(PlayerXpEvent.PickupXp event) {
//        Player player = (Player) event.getEntity();
//
//        player.getCapability(PlayerMiraProvider.PLAYER_Mira).ifPresent(mira ->{
//            player.sendSystemMessage(Component.literal("mira = "+ mira.getMira()));
////            if(mira.getMira()>=10) {
////            return;
////            }
//        mira.addMira(event.getOrb().getValue());
//        //LOGGER.info(ANSI_GREEN + "MIRA Gained: " + mira.getMira() + ANSI_RESET);
//
//        });
//    }    }

//@Override
//public CompoundTag getUpdateTag(){
//    CompoundTag nbt = super.getUpdateTag();
//    saveNBTData();
//    }
//}


//    @SubscribeEvent
//    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
//        Player player = event.getEntity();
//        CompoundTag playerData = player.getPersistentData();
//
//        // Check if the mod's player data compound exists, if not create it
//        if (!playerData.contains("mirapose:player_data",TAG_COMPOUND)) {
//            LOGGER.info(ANSI_GREEN + "No tag!!! " + ANSI_RESET);
//            playerData.put("mirapose:player_data", new CompoundTag());
//
//        }
//        CompoundTag modData = playerData.getCompound("mirapose:player_data");
//
//        // Initialize miraSwitch if it doesn't exist
//        if (!modData.contains("miraSwitch")) {
//            modData.putBoolean("miraSwitch", false); // Default value is false
//        }
//
//        // Initialize Mira if it doesn't exist
//        if (!modData.contains("mira")) {
//            modData.putInt("mira", 0); // Default value is 0
//        }
//
//        // No need to put modData back into playerData, as it is already a reference to the nested tag
//    }

//    @SubscribeEvent
//    public static void onXpGain(PlayerXpEvent.PickupXp event) {
//        Player player = (Player) event.getEntity();
//        int miraGain = event.getOrb().getValue();
//        CompoundTag playerData = player.getPersistentData();
//        // Assuming "mira" is already initialized somewhere
//        playerData.putInt("mira", playerData.getInt("mira")+ miraGain);
//        int currentMira = playerData.getInt("mira");
//        player.sendSystemMessage(Component.literal("mira = "+ currentMira));
//            if(currentMira>=10){
//               // switchMira();
//                LOGGER.info(ANSI_GREEN + "CANMira!!! " + ANSI_RESET);
//                playerData.putInt("mira", 0);
//            } }



//            miraSwitch = true;
//
//        }
//
//        }
//
////        public static void switchMira(){
////         if (!miraSwitch){
////             miraSwitch=true;
////             return;
////         }
////         miraSwitch=false;
////        }
////    public boolean getCanSwitch(){
////        return miraSwitch;
////
////    }
//
//    }
//
//
//        // Now 'mira' is guaranteed to exist, either we just created it or it was already there

