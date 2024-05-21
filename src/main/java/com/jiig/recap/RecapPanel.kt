package com.jiig.recap

import com.jiig.recap.tools.Icon
import lombok.extern.slf4j.Slf4j
import net.runelite.client.config.ConfigManager
import net.runelite.client.ui.PluginPanel
import net.runelite.client.util.ImageUtil
import net.runelite.client.util.SwingUtil
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

@Slf4j
class RecapPanel(private val configManager: ConfigManager) : PluginPanel(false) {
    init {

        layout = BorderLayout()

        val titlePanel = JPanel()
        titlePanel.border = EmptyBorder(10, 10, 10, 10)
        titlePanel.layout = BorderLayout()

        val title = JLabel()
        title.text = "Daily Recap"
        title.foreground = Color.WHITE
        titlePanel.add(title, BorderLayout.WEST)

        val status = JButton()
        SwingUtil.removeButtonDecorations(status)
        status.icon = Icon.CONNECTION_OK.getIcon { ImageUtil.resizeImage(it, 16, 16) }
        status.toolTipText = "Connection status"
        titlePanel.add(status, BorderLayout.EAST)

        val main = JPanel()
        main.layout = BorderLayout()
        main.add(titlePanel, BorderLayout.NORTH)

        super.add(main)
    }
}
