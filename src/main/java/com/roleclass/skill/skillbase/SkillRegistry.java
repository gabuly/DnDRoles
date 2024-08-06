
package com.roleclass.skill.skillbase;

import com.roleclass.skill.skills.Fireball;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class SkillRegistry {
    private static final Map<String, ActiveSkill> SKILLS = new HashMap<>();

    public static void registerSkill(ActiveSkill skill) {
        SKILLS.put(skill.getName(), skill);
    }

    public static ActiveSkill getSkill(String name) {
        return SKILLS.get(name);
    }

    public static void init() {
        // Register all skills here
        registerSkill(new Fireball());
        // Add other skills as needed
    }
}