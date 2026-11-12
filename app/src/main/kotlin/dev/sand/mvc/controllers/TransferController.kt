package dev.sand.mvc.controllers

import dev.sand.mvc.models.AccountModel
import dev.sand.mvc.services.BankService
import dev.sand.mvc.views.TransferView
import dev.sand.mvc.views.TransferViewModel

class TransferController(private val bank: BankService) {
    fun index(): String {
        val draft = AccountModel.draftTransfer
        val showFraudWarning = draft != null && bank.fraudScore(draft.to, draft.amount) >= FRAUD_THRESHOLD
        return TransferView.render(
            TransferViewModel(
                accountLabel = AccountModel.accountLabel,
                balance = AccountModel.balance,
                draft = draft,
                showFraudWarning = showFraudWarning,
            ),
        )
    }

    /** Sends the draft transfer. The new balance always comes from the server. */
    fun confirm() {
        val draft = AccountModel.draftTransfer ?: return
        AccountModel.balance = bank.transfer(draft.to, draft.amount)
        AccountModel.draftTransfer = null
    }

    private companion object {
        const val FRAUD_THRESHOLD = 70
    }
}
