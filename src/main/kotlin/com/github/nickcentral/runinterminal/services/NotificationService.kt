package com.github.nickcentral.runinterminal.services

import com.github.nickcentral.runinterminal.MyBundle
import com.github.nickcentral.runinterminal.enums.NotificationKey
import com.intellij.ide.actions.RevealFileAction
import com.intellij.notification.*
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NonNls
import java.io.File


@NonNls
private const val NOTIFICATION_GROUP_ID =
    "com-github-nickcentral-runinterminal-services-NotificationService"

@Service(Service.Level.APP)
class NotificationService {

    val notificationGroup: NotificationGroup by lazy(LazyThreadSafetyMode.PUBLICATION) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup(NOTIFICATION_GROUP_ID)
    }

    fun notify(
        project: Project?,
        notificationType: NotificationType,
        notificationKey: NotificationKey
    ) {
        val notification: Notification = notificationGroup.createNotification(
            title = notificationKey.buildTitle(),
            content = notificationKey.buildBody(),
            type = notificationType
        )
        if (notificationType == NotificationType.ERROR) {
            val actionName = MyBundle.message("showLogInFinder")
            val showLogInFinder = NotificationAction.createSimple(actionName) {
                RevealFileAction.openFile(File(PathManager.getLogPath()))
            }
            notification.addAction(showLogInFinder)
        }
        notification.notify(project)
    }
}
