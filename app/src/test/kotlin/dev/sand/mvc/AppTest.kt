package dev.sand.mvc

import dev.sand.mvc.models.AccountModel
import dev.sand.mvc.services.FakeBankService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AppTest {
    private lateinit var routes: Routes

    @BeforeTest
    fun setUp() {
        AccountModel.reset()
        routes = Routes(FakeBankService())
    }

    private fun page(name: String) = routes.pages.getValue(name)()

    @Test
    fun `routes list every page`() {
        assertEquals(listOf("home", "transfer"), routes.pages.keys.toList())
    }

    @Test
    fun `fraud warning and loan offer appear on their pages`() {
        assertTrue("⚠" in page("transfer"))
        assertTrue("Personal loan offer" in page("home"))
    }

    @Test
    fun `confirming a transfer updates the balance from the server`() {
        routes.actions.getValue("transfer.confirm")()
        assertEquals(27_300L, AccountModel.balance)
        assertFalse("⚠" in page("transfer"))
        assertFalse("Personal loan offer" in page("home"))
    }
}
