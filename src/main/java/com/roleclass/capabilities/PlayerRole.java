package com.roleclass.capabilities;

import com.roleclass.roles.Role;
import net.minecraft.nbt.CompoundTag;

public class PlayerRole implements IPlayerRole {
    private Role mainRole;
    private int fighterLv;
    private int magicianLv;
    private int assassinLv;
    private int clericLv;
    private int fighterXP;
    private int magicianXP;
    private int assassinXP;
    private int clericXP;

    private static final int XP_PER_LEVEL = 100; // Example XP required per level

    public PlayerRole() {
        this.mainRole = Role.FIGHTER;  // Default role, can be changed later
        this.fighterLv = 0;
        this.magicianLv = 0;
        this.assassinLv = 0;
        this.clericLv = 0;
        this.fighterXP = 0;
        this.magicianXP = 0;
        this.assassinXP = 0;
        this.clericXP = 0;
    }

    @Override
    public Role getMainRole() {
        return mainRole;
    }

    @Override
    public void setMainRole(Role role) {
        this.mainRole = role;
    }

    @Override
    public int getLevel(Role role) {
        return switch (role) {
            case FIGHTER -> fighterLv;
            case MAGICIAN -> magicianLv;
            case ASSASSIN -> assassinLv;
            case CLERIC -> clericLv;
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }

    @Override
    public void setLevel(Role role, int level) {
        switch (role) {
            case FIGHTER:
                this.fighterLv = level;
                break;
            case MAGICIAN:
                this.magicianLv = level;
                break;
            case ASSASSIN:
                this.assassinLv = level;
                break;
            case CLERIC:
                this.clericLv = level;
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    @Override
    public int getXP(Role role) {
        return getXPForRole(role);
    }

    @Override
    public void addXP(Role role, int amount) {
        int xp = getXPForRole(role);
        xp += amount;
        setXPForRole(role, xp);
        checkLevelUp(role);
    }

    private int getXPForRole(Role role) {
        switch (role) {
            case FIGHTER:
                return fighterXP;
            case MAGICIAN:
                return magicianXP;
            case ASSASSIN:
                return assassinXP;
            case CLERIC:
                return clericXP;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    private void setXPForRole(Role role, int xp) {
        switch (role) {
            case FIGHTER:
                fighterXP = xp;
                break;
            case MAGICIAN:
                magicianXP = xp;
                break;
            case ASSASSIN:
                assassinXP = xp;
                break;
            case CLERIC:
                clericXP = xp;
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    private void checkLevelUp(Role role) {
        int xp = getXPForRole(role);

        while (xp >= XP_PER_LEVEL) {
            xp -= XP_PER_LEVEL;
            levelUp(role);
        }

        setXPForRole(role, xp);
    }

    private void levelUp(Role role) {
        switch (role) {
            case FIGHTER:
                fighterLv++;
                break;
            case MAGICIAN:
                magicianLv++;
                break;
            case ASSASSIN:
                assassinLv++;
                break;
            case CLERIC:
                clericLv++;
                break;
        }
        System.out.println("Level up! New level: " + getLevel(role) + " in role: " + role);
    }

    @Override
    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("MainRole", mainRole.name());
        nbt.putInt("FighterLevel", fighterLv);
        nbt.putInt("MagicianLevel", magicianLv);
        nbt.putInt("AssassinLevel", assassinLv);
        nbt.putInt("ClericLevel", clericLv);
        nbt.putInt("FighterXP", fighterXP);
        nbt.putInt("MagicianXP", magicianXP);
        nbt.putInt("AssassinXP", assassinXP);
        nbt.putInt("ClericXP", clericXP);
    }

    @Override
    public void loadNBTData(CompoundTag nbt) {
        this.mainRole = Role.valueOf(nbt.getString("MainRole"));
        this.fighterLv = nbt.getInt("FighterLevel");
        this.magicianLv = nbt.getInt("MagicianLevel");
        this.assassinLv = nbt.getInt("AssassinLevel");
        this.clericLv = nbt.getInt("ClericLevel");
        this.fighterXP = nbt.getInt("FighterXP");
        this.magicianXP = nbt.getInt("MagicianXP");
        this.assassinXP = nbt.getInt("AssassinXP");
        this.clericXP = nbt.getInt("ClericXP");
    }
}
