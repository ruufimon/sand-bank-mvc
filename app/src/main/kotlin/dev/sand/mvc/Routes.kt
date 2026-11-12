package dev.sand.mvc

import dev.sand.mvc.controllers.HomeController
import dev.sand.mvc.controllers.TransferController
import dev.sand.mvc.services.BankService

/** Every page and action in the app. Add new pages and actions here. */
class Routes(bank: BankService) {
    private val home = HomeController(bank)
    private val transfer = TransferController(bank)

    /** Page name → renderer, in the order pages are shown. */
    val pages: Map<String, () -> String> = linkedMapOf(
        "home" to home::index,
        "transfer" to transfer::index,
    )

    /** Action name → handler. */
    val actions: Map<String, () -> Unit> = mapOf(
        "transfer.confirm" to transfer::confirm,
    )
}
