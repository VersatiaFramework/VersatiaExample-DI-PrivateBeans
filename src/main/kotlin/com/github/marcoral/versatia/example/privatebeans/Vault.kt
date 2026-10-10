package com.github.marcoral.versatia.example.privatebeans

import com.github.marcoral.versatia.EventType
import com.github.marcoral.versatia.di.LocalBean
import com.github.marcoral.versatia.lifecycle.AutoInvoke

/** Private to this file: the generated code cannot name it, so the descriptor instantiates it reflectively. */
@LocalBean
private class Key {
    init {
        log("created, a private class")
    }
}

@LocalBean
private class Vault(key: Key) {
    init {
        log("created with a private Key")
    }
}

@LocalBean
private class Auditor(vault: Vault) {
    init {
        log("created")
    }

    /** Private function: called through a reflective invoker, parameters injected all the same. */
    @AutoInvoke(EventType.PLUGIN_START_ONLY)
    private fun audit(key: Key) = log("audit() called through reflection with the ${key.javaClass.simpleName}")
}
