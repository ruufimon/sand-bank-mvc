package dev.sand.mvc.services

/** Calls to the bank server. HTTP form: api/openapi.yaml, served by :host-server. */
interface BankService {
    /** POST /fraud-score → score (0–100). */
    fun fraudScore(to: String, amount: Long): Int

    /** GET /loan-eligibility → eligible. The server reads the balance itself. */
    fun isEligibleForLoan(): Boolean

    /** POST /transfers → newBalance. The server is the source of truth for the balance. */
    fun transfer(to: String, amount: Long): Long
}
