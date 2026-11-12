package dev.sand.mvc.models

data class Transfer(val to: String, val amount: Long)

/** State of the signed-in customer's account, shared by every controller. */
object AccountModel {
    var customerName: String = "Somchai"
    var accountLabel: String = "Savings ••4821"
    var balance: Long = 52_300L
    var draftTransfer: Transfer? = Transfer(to = "Somsri K. ••7730", amount = 25_000L)

    /** Restores the starting values. Used by tests. */
    fun reset() {
        customerName = "Somchai"
        accountLabel = "Savings ••4821"
        balance = 52_300L
        draftTransfer = Transfer(to = "Somsri K. ••7730", amount = 25_000L)
    }
}
