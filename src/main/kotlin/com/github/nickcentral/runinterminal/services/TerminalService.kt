package com.github.nickcentral.runinterminal.services

import com.github.nickcentral.runinterminal.MyBundle
import com.intellij.openapi.components.Service
import org.jetbrains.annotations.NonNls

@NonNls
private const val RESOURCE_NAME = "run-in-terminal.js"

@NonNls
private const val RESOURCE_NOT_FOUND_KEY = "error.resourceNotFound"

@Service(Service.Level.APP)
class TerminalService {

    val scriptText: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        val errorMessage = {
            MyBundle.message(RESOURCE_NOT_FOUND_KEY, RESOURCE_NAME)
        }
        requireNotNull(
            javaClass.classLoader.getResource(RESOURCE_NAME)
        ) { errorMessage.invoke() }.readText()
    }

    fun execute(command: String) {
        val processBuilder = ProcessBuilder(
            "osascript", "-l", "JavaScript", "-e", scriptText, command
        )
        val errorMessage = { exitCode: Int, output: String ->
            arrayOf(
                "Failed to execute command: ${processBuilder.command().joinToString(" ")}",
                "Exit code: $exitCode",
                "Message: $output"
            ).joinToString("\n")
        }
        val process: Process = processBuilder.redirectErrorStream(true).start()
        val output = process.inputStream.bufferedReader().readText().trim()
        val exitCode: Int = process.waitFor()
        if (exitCode != 0) {
            throw RuntimeException(errorMessage.invoke(exitCode, output))
        }
    }
}
