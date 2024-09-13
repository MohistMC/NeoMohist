package com.mohistmc.youer.plugins;

import com.mohistmc.youer.Youer;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

/**
 * @author Mgazul by MohistMC
 * @date 2023/6/26 13:56:32
 */
public class PluginHooks {

    public static boolean hook(Plugin plugin) {
        if (plugin.getName().equals("PlaceholderAPI")){
            return true;
        }
        return plugin.callForge();
    }

    public static void register(PluginEnableEvent e) {
        Plugin plugin = e.getPlugin();
        if (plugin.getName().equals("PlaceholderAPI")){
            MohistPapiHook.init();
            Youer.LOGGER.info("Hook PlaceholderAPI!");
        }
    }
}
