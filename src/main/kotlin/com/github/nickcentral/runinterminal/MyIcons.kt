package com.github.nickcentral.runinterminal

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

@Suppress("unused")
object MyIcons {
    @JvmField
    val RUN_IN_TERMINAL: Icon = IconLoader.getIcon(
        "/icons/run_in_terminal.svg", MyIcons::class.java
    )
}
