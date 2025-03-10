package hensal.spellpackblood.registry;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;
import hensal.spellpackblood.MySpellPack;
import hensal.spellpackblood.entity.living.EntityBloodWolf;
import hensal.spellpackblood.entity.projectile.EntityBloodShard;

@Mod.EventBusSubscriber
public class MySpellPackEntities
{

    private MySpellPackEntities()
    {
    } // No instances!

    /**
     * Most entity trackers fall into one of a few categories, so they are defined here for convenience. This
     * generally follows the values used in vanilla for each entity type.
     */
    enum TrackingType
    {

        LIVING(80, 3, true),
        PROJECTILE(64, 1, true),
        CONSTRUCT(160, 10, false);

        int range;
        int interval;
        boolean trackVelocity;

        TrackingType(int range, int interval, boolean trackVelocity)
        {
            this.range = range;
            this.interval = interval;
            this.trackVelocity = trackVelocity;
        }
    }

    /**
     * Incrementing index for the mod-specific entity network ID.
     */
    private static int id = 0;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<EntityEntry> event)
    {

        IForgeRegistry<EntityEntry> registry = event.getRegistry();

        // -------------------- Entity Registry --------------------
        //registry.register(createEntry(EntityFooBar.class, "foo_bar", TrackingType.LIVING).build());
        registry.register(createEntry(EntityBloodShard.class, "blood_shard", MySpellPackEntities.TrackingType.PROJECTILE).build());
        registry.register(createEntry(EntityBloodWolf.class, "blood_wolf", MySpellPackEntities.TrackingType.LIVING).egg(0xbcc2e8, 0x5464c6).build());
    }

    /**
     * Private helper method that simplifies the parts of an {@link EntityEntry} that are common to all entities.
     * This automatically assigns a network id, and accepts a {@link TrackingType} for automatic tracker assignment.
     *
     * @param entityClass The entity class to use.
     * @param name        The name of the entity. This will form the path of a {@code ResourceLocation} with domain
     *                    {@code }, which in turn will be used as both the registry name and the 'command' name.
     * @param tracking    The {@link TrackingType} to use for this entity.
     * @param <T>         The type of entity.
     * @return The (part-built) builder instance, allowing other builder methods to be added as necessary.
     */
    private static <T extends Entity> EntityEntryBuilder<T> createEntry(Class<T> entityClass, String name, TrackingType tracking)
    {
        return createEntry(entityClass, name).tracker(tracking.range, tracking.interval, tracking.trackVelocity);
    }

    /**
     * Private helper method that simplifies the parts of an {@link EntityEntry} that are common to all entities.
     * This automatically assigns a network id.
     *
     * @param entityClass The entity class to use.
     * @param name        The name of the entity. This will form the path of a {@code ResourceLocation} with domain
     *                    {@code }, which in turn will be used as both the registry name and the 'command' name.
     * @param <T>         The type of entity.
     * @return The (part-built) builder instance, allowing other builder methods to be added as necessary.
     */
    private static <T extends Entity> EntityEntryBuilder<T> createEntry(Class<T> entityClass, String name)
    {
        ResourceLocation registryName = new ResourceLocation(MySpellPack.MODID, name);
        return EntityEntryBuilder.<T>create().entity(entityClass).id(registryName, id++).name(registryName.toString());
    }
}
