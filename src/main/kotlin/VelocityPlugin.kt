package com.github.arcticaquila.aquamultiinstance

import com.google.inject.Inject
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import java.nio.file.Path

@Plugin(
    id = "aquamultiinstance",
    name = "Aqua Multi Instance",
    version = "1.0.0-SNAPSHOT",
    url = "https://github.com/ArcticAquila/Aqua-Multi-Instance",
    description = "A velocity plugin for management multiple instance!",
    authors = ["ArcticAquila"]
)

class VelocityPlugin @Inject constructor(
    private val server: ProxyServer,
    private val logger: Logger,
    @DataDirectory dataDirectory: Path
)
