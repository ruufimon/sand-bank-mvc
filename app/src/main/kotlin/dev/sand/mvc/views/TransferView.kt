package dev.sand.mvc.views

import dev.sand.mvc.models.Transfer
import dev.sand.mvc.models.baht

data class TransferViewModel(
    val accountLabel: String,
    val balance: Long,
    val draft: Transfer?,
    val showFraudWarning: Boolean,
)

object TransferView {
    fun render(model: TransferViewModel): String = buildString {
        appendLine("Sand Bank · Transfer")
        appendLine("From:   ${model.accountLabel} (${baht(model.balance)})")
        if (model.draft == null) {
            appendLine("No transfer in progress")
        } else {
            appendLine("To:     ${model.draft.to}")
            appendLine("Amount: ${baht(model.draft.amount)}")
        }
        if (model.showFraudWarning) {
            appendLine("⚠ Check the recipient. This transfer looks unusual for your account.")
        }
        append("[ Confirm transfer ]")
    }
}
