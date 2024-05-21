package com.jiig.recap

import io.github.oshai.kotlinlogging.KotlinLogging
import net.runelite.client.RuneLite
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

private val log = KotlinLogging.logger {}
class DataHoarder {
    private var connection: Connection? = null
    private var ready: Boolean = false
    private var file: File? = null

    fun init(player: Long?) {
        if(ready) return // only need to init once

        log.info { "SQlite init for $player" }
        val dir = "${RuneLite.RUNELITE_DIR.path}/recap/${player}"
        val path = File(dir)
        path.mkdirs()
        file = File("${dir}/recap.sqlite")

        connection = DriverManager.getConnection("jdbc:sqlite:${file?.path}")
        ready = true;
    }
}
