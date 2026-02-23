package com.github.nickcentral.runinterminal

import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange

object MyUtils {

    fun extractText(editor: Editor): String {
        val extractFromLineAtCaretOffset = {
            val caretOffset: Int = editor.caretModel.offset
            val document: Document = editor.document
            val lineNumber: Int = document.getLineNumber(caretOffset)
            val lineStart = document.getLineStartOffset(lineNumber)
            val lineEnd = document.getLineEndOffset(lineNumber)
            document.getText(TextRange(lineStart, lineEnd))
        }
        return (editor.selectionModel.selectedText ?: extractFromLineAtCaretOffset.invoke()).trim()
    }

    fun refineText(input: String): String {
        return input
            .lineSequence()
            .mapNotNull { line ->
                val trimmed = line.trimStart()
                if (trimmed.startsWith("#")) {
                    null
                } else {
                    line.replace(Regex("\\s+#.*$"), "").trimEnd()
                }
            }
            .filter { it.isNotBlank() }
            .joinToString("\n")
    }
}
