package com.github.nickcentral.runinterminal.enums

import com.github.nickcentral.runinterminal.MyBundle

enum class NotificationKey(val titleKey: String, val bodyKey: String) {
    NO_TEXT_SELECTED(
        "notification.noTextSelected.title",
        "notification.noTextSelected.body"
    ),
    COMMENTED_TEXT_SELECTED(
        "notification.commentedTextSelected.title",
        "notification.commentedTextSelected.body"
    );

    fun buildTitle(): String = MyBundle.message(this.titleKey)

    fun buildBody(): String = MyBundle.message(this.bodyKey)
}
