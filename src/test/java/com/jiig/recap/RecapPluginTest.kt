package com.jiig.recap

import net.runelite.client.RuneLite
import net.runelite.client.externalplugins.ExternalPluginManager

object RecapPluginTest {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        ExternalPluginManager.loadBuiltin(Recap::class.java)
        RuneLite.main(args)
    }
}