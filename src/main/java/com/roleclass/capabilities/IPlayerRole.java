package com.roleclass.capabilities;
import com.roleclass.roles.Role;
import net.minecraft.nbt.CompoundTag;

public interface IPlayerRole {
    Role getMainRole();
    void setMainRole(Role role);

    int getLevel(Role role);
    void setLevel(Role role, int level);

    int getXP(Role role);
    void addXP(Role role, int amount);

    void saveNBTData(CompoundTag nbt);
    void loadNBTData(CompoundTag nbt);
}