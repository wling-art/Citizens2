package net.citizensnpcs.npc.entity;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.CitizensMobNPC;
import net.citizensnpcs.npc.CitizensNPC;
import net.citizensnpcs.npc.ai.NPCHolder;
import net.minecraft.server.EntityOcelot;
import net.minecraft.server.PathfinderGoalSelector;
import net.minecraft.server.World;

import org.bukkit.entity.Ocelot;

public class CitizensOcelotNPC extends CitizensMobNPC {

    public CitizensOcelotNPC(int id, String name) {
        super(id, name, EntityOcelotNPC.class);
    }

    @Override
    public Ocelot getBukkitEntity() {
        return (Ocelot) getHandle().getBukkitEntity();
    }

    public static class EntityOcelotNPC extends EntityOcelot implements NPCHolder {
        private final CitizensNPC npc;

        public EntityOcelotNPC(World world) {
            this(world, null);
        }

        public EntityOcelotNPC(World world, NPC npc) {
            super(world);
            this.npc = (CitizensNPC) npc;
            goalSelector = new PathfinderGoalSelector();
            targetSelector = new PathfinderGoalSelector();
        }

        @Override
        public NPC getNPC() {
            return npc;
        }

        @Override
        public void z_() {
            super.z_();
            if (npc != null)
                npc.update();
        }
    }
}