package com.jiig.recap

import com.google.inject.Provides
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.extern.slf4j.Slf4j
import net.runelite.api.ChatMessageType
import net.runelite.api.Client
import net.runelite.api.GameState
import net.runelite.api.IconID
import net.runelite.api.events.GameStateChanged
import net.runelite.client.RuneLite
import net.runelite.client.callback.ClientThread
import net.runelite.client.config.ConfigManager
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.plugins.Plugin
import net.runelite.client.plugins.PluginDescriptor
import net.runelite.client.plugins.loottracker.LootReceived
import net.runelite.client.plugins.loottracker.PluginLootReceived
import net.runelite.client.ui.ClientToolbar
import net.runelite.client.ui.NavigationButton
import net.runelite.client.util.ImageUtil
import javax.inject.Inject
import javax.xml.crypto.Data

private val log = KotlinLogging.logger {}

@Slf4j
@PluginDescriptor(name = "Example")
class Recap : Plugin() {

    @Inject
    private var client: Client? = null

    @Inject
    private var config: RecapConfig? = null

    @Inject
    private var clientToolbar: ClientToolbar? = null

    @Inject
    private var configManager: ConfigManager? = null

    @Inject
    private var clientThread: ClientThread? = null

    private var navButton: NavigationButton? = null

    private var recapPanel: RecapPanel? = null

    private var dataHoarder: DataHoarder = DataHoarder()

    @Throws(Exception::class)
    override fun startUp() {
        log.info { " --- Recap started! --- " }
        log.info { RuneLite.RUNELITE_DIR.path }

        recapPanel = configManager?.let { RecapPanel(it) }

        navButton = NavigationButton.builder()
            .tooltip("Recap")
            .icon(ImageUtil.loadImageResource(IconID::class.java, "/calendar.png"))
            .panel(recapPanel)
            .build()

        clientToolbar?.addNavigation(navButton);

        clientThread?.invokeLater(Runnable {
            if(client?.gameState == GameState.LOGGED_IN) {
            }
        })
    }

    @Throws(Exception::class)
    override fun shutDown() {
    }

    @Subscribe
    fun onLootReceived(lootReceived: LootReceived) {
        log.info { " I got loot! " }
        log.info { lootReceived.toString() }
    }

    @Subscribe
    fun onGameStateChanged(gameStateChanged: GameStateChanged) {
        if (gameStateChanged.gameState == GameState.LOGGED_IN) {
            log.info { "Logged in!" }
            client!!.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config!!.greeting(), null)
            dataHoarder.init(client?.accountHash)
        }
    }

    @Provides
    fun provideConfig(configManager: ConfigManager): RecapConfig {
        return configManager.getConfig(RecapConfig::class.java)
    }
}
