package hensal.spellpackblood.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import hensal.spellpackblood.MySpellPack;


@GameRegistry.ObjectHolder(MySpellPack.MODID)
@Mod.EventBusSubscriber(modid = MySpellPack.MODID)
public class MySpellPackSounds
{

    private MySpellPackSounds()
    {
    } // no instances!

//	public static final SoundEvent EXAMPLE_SOUND = createSound("example_sound");

    public static SoundEvent createSound(String name)
    {
        return createSound(MySpellPack.MODID, name);
    }

    /**
     * Creates a sound with the given name, to be read from {@code assets/[modID]/sounds.json}.
     */
    public static SoundEvent createSound(String modID, String name)
    {
        // All the setRegistryName methods delegate to this one, it doesn't matter which you use.
        return new SoundEvent(new ResourceLocation(modID, name)).setRegistryName(name);
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<SoundEvent> event)
    {
        // event.getRegistry().register(EXAMPLE_SOUND);
    }
}