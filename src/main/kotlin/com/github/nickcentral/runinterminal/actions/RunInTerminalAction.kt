package com.github.nickcentral.runinterminal.actions

import com.github.nickcentral.runinterminal.MyUtils
import com.github.nickcentral.runinterminal.enums.NotificationKey
import com.github.nickcentral.runinterminal.services.NotificationService
import com.github.nickcentral.runinterminal.services.TerminalService
import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class RunInTerminalAction : AnAction(), ActionPromoter {

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

    override fun update(e: AnActionEvent) {
        val disablePlugin = { e.presentation.isEnabledAndVisible = false }
        if (!SystemInfo.isMac) return disablePlugin()
        val editor: Editor = e.getData(CommonDataKeys.EDITOR) ?: return disablePlugin()
        when (editor.virtualFile.extension) {
            "sh" -> return
            "md" -> Unit
            else -> return disablePlugin()
        }
        val project: Project = e.project ?: return disablePlugin()
        val psiFile: PsiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return disablePlugin()
        val psiElement: PsiElement = InjectedLanguageManager.getInstance(project)
            .findInjectedElementAt(psiFile, editor.caretModel.offset) ?: return disablePlugin()
        val containingFile: PsiElement = psiElement.containingFile ?: return disablePlugin()
        if (containingFile.toString() != "SHELL_SCRIPT") return disablePlugin()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor: Editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val extractedText: String = MyUtils.extractText(editor)
        val notify = { notificationKey: NotificationKey ->
            service<NotificationService>().notify(
                e.project,
                NotificationType.INFORMATION,
                notificationKey,
            )
        }
        if (extractedText.isEmpty()) {
            notify(NotificationKey.NO_TEXT_SELECTED)
            return
        }
        val refinedText: String = MyUtils.refineText(extractedText)
        if (refinedText.isEmpty()) {
            notify(NotificationKey.COMMENTED_TEXT_SELECTED)
            return
        }
        service<TerminalService>().execute(refinedText)
    }

    /**
     * Reorders actions that share the same shortcut so that RunInTerminalAction
     * instances are executed first.
     *
     * When multiple AnAction implementations are bound to the same keyboard
     * shortcut and are applicable in the current DataContext, the platform
     * collects them into a list and calls ActionPromoter.promote().
     *
     * If this method returns a non-null list, the returned order defines
     * execution priority. The first action in the list is chosen and invoked.
     *
     * Here, all RunInTerminalAction instances are moved to the beginning,
     * ensuring they take precedence over other actions bound to the same shortcut.
     *
     * Return null to keep the default ordering.
     */
    override fun promote(actions: List<AnAction>, context: DataContext): List<AnAction>? {
        val mine: List<RunInTerminalAction> = actions.filterIsInstance<RunInTerminalAction>()
        if (mine.isEmpty()) return null
        val rest: List<AnAction> = actions.filterNot { it is RunInTerminalAction }
        return mine + rest
    }
}
