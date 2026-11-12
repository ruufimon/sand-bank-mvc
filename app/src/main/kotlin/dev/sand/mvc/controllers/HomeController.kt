package dev.sand.mvc.controllers

import dev.sand.mvc.Menu
import dev.sand.mvc.models.AccountModel
import dev.sand.mvc.services.BankService
import dev.sand.mvc.views.HomeView
import dev.sand.mvc.views.HomeViewModel

class HomeController(private val bank: BankService) {
    fun index(): String = HomeView.render(
        HomeViewModel(
            customerName = AccountModel.customerName,
            balance = AccountModel.balance,
            showLoanOffer = bank.isEligibleForLoan(),
            menu = Menu.items,
        ),
    )
}
