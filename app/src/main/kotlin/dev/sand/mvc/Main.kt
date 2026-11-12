package dev.sand.mvc

import dev.sand.mvc.models.AccountModel
import dev.sand.mvc.services.FakeBankService

fun main() {
    val routes = Routes(FakeBankService())

    routes.pages.forEach { (name, render) -> show(name, render()) }

    if (AccountModel.draftTransfer == null) return
    println("Customer taps Confirm…")
    routes.actions.getValue("transfer.confirm")()
    println()

    show("home", routes.pages.getValue("home")())
}

private fun show(page: String, text: String) {
    println("── $page ──────────────────────────")
    println(text)
    println()
}
