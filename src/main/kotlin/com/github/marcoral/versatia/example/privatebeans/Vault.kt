package com.github.marcoral.versatia.example.privatebeans

import com.github.marcoral.versatia.Phase
import com.github.marcoral.versatia.di.LocalBean
import com.github.marcoral.versatia.lifecycle.AutoInvoke
import java.util.logging.Logger

/** Private to this file: the generated code cannot name it, so the descriptor instantiates it reflectively. */
@LocalBean
private class Key(logger: Logger) {
    init {
        logger.info("Key created, a private class")
    }
}

@LocalBean
private class Vault(key: Key, logger: Logger) {
    init {
        logger.info("Vault created with the private ${key.javaClass.simpleName}")
    }
}

@LocalBean
private class Auditor(vault: Vault, private val logger: Logger) {
    init {
        logger.info("Auditor created with the ${vault.javaClass.simpleName}")
    }

    /** Private function: called through a reflective invoker, parameters injected all the same. */
    @AutoInvoke(Phase.PLUGIN_START_ONLY)
    private fun audit(key: Key) = logger.info("Auditor.audit() called through reflection with the ${key.javaClass.simpleName}")
}
