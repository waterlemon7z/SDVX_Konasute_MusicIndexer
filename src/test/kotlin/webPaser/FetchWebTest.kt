package webPaser

import org.junit.jupiter.api.Test

class FetchWebTest(
){

    @Test
    fun getMusicPage() {
        val fetchWeb: FetchWeb = FetchWeb()
        fetchWeb.getMusicPage(3)
    }
}