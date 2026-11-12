package dev.sand.mvc.views

import dev.sand.mvc.models.baht

data class HomeViewModel(
    val customerName: String,
    val balance: Long,
    val showLoanOffer: Boolean,
    val menu: List<String>,
)

object HomeView {
    fun render(model: HomeViewModel): String = buildString {
        appendLine("Sand Bank · Home")
        appendLine("Hi, ${model.customerName}")
        appendLine("Balance: ${baht(model.balance)}")
        if (model.showLoanOffer) appendLine("Personal loan offer → [ View offer ]")
        append(model.menu.joinToString(" · "))
    }
}
