package dev.zenith.pearlplus;

import com.zenith.plugin.api.PluginAPI;
import com.zenith.plugin.api.Plugin;
import com.zenith.plugin.api.ZenithProxyPlugin;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import dev.zenith.pearlplus.command.*;
import dev.zenith.pearlplus.module.*;

@Plugin(
    id = BuildConstants.PLUGIN_ID,
    version = BuildConstants.VERSION,
    description = "SyntaxPearl: a PearlPlus-based stasis loader fork by Syntaxia Development. Credits to the original PearlPlus developers.",
    url = "https://github.com/duccss/pearlplus/",
    authors = {"duccss", "steve2b2t", "Leonetic", "Sleepy", "Syntaxia Development"},
    mcVersions = "*" // mark every version compatible
)

public class PearlPlusPlugin implements ZenithProxyPlugin {
    public static PluginAPI API;
    public static PearlPlusConfig PLUGIN_CONFIG;
    public static ComponentLogger LOG;

    @Override
    public void onLoad(PluginAPI pluginAPI) {
        API = pluginAPI;
        LOG = pluginAPI.getLogger();
        LOG.info("SyntaxPearl Plugin loading...");
        PLUGIN_CONFIG = API.registerConfig(BuildConstants.PLUGIN_ID, PearlPlusConfig.class);
        ChamberLookup.load();
        PearlManager.backfillRelativeCoords();
        API.registerCommand(new PearlPlusCommand());
        API.registerModule(new AutoLoadModule());
        API.registerModule(new AutoDetectModule());

        LOG.info("SyntaxPearl Plugin loaded!");
    }
}
