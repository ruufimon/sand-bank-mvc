package dev.sand.mvc.services

import dev.sand.mvc.models.baht

/** Stands in for :host-server, with the same rules. Swap it for an HTTP implementation of BankService. */
class FakeBankService(private var balance: Long = 52_300L) : BankService {
    override fun fraudScore(to: String, amount: Long): Int = if (amount >= 20_000L) 85 else 25

    override fun isEligibleForLoan(): Boolean = balance >= 30_000L

    override fun transfer(to: String, amount: Long): Long {
        require(amount <= balance) { "Insufficient funds" }
        balance -= amount
        println("  (host) sent ${baht(amount)} to $to")
        return balance
    }
}
