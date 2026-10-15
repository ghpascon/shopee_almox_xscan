package com.smartx.rfidreader.core.settings

enum class OperationType(val value: String) {
    NONE("NONE"),
    ENTRY("ENTRY"),
    EXIT("EXIT"),
    DISCARD("DISCARD");

    companion object {
        fun fromValue(raw: String?): OperationType {
            return entries.firstOrNull { it.value == raw } ?: NONE
        }
    }
}
