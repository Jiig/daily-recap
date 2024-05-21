package com.jiig.recap.tools

import com.jiig.recap.Recap
import net.runelite.client.util.ImageUtil
import java.awt.image.BufferedImage
import javax.swing.ImageIcon

// Shamelessly copied from quest-helper
enum class Icon(private val path: String) {
    CONNECTION_OK("/connection_ok.png"),
    CONNECTION_BAD("/connection_bad.png")
    ;

    fun getImage(): BufferedImage {
        return ImageUtil.loadImageResource(Recap::class.java, path)
    }

    fun getIcon(func: (BufferedImage) -> BufferedImage): ImageIcon {
        val bi = func(getImage())
        return ImageIcon(bi)
    }

    fun getIcon(): ImageIcon {
        return ImageIcon(getImage())
    }
}