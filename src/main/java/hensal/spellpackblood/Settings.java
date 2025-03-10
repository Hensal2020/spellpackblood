package hensal.spellpackblood;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MySpellPack.MODID, name = "MySpellPack") // No fancy configs here so we can use the annotation, hurrah!
public class Settings
{


    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = MySpellPack.MODID)
    private static class EventHandler
    {
        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(MySpellPack.MODID))
            {
                ConfigManager.sync(MySpellPack.MODID, Config.Type.INSTANCE);
            }
        }
    }

    @Config.Name("General Settings")
    public static GeneralSettings generalSettings = new GeneralSettings();

    public static class GeneralSettings
    {
        @Config.Name("Some Boolean")
        @Config.Comment("Some random setting")
        public boolean some_boolean = true;
    }

}
