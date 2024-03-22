package com.waterlemon7z.console

object Menu {
    fun mainMenu(): Int {
        println(
            """
              ____  ______     ____  __  ___ _   _ ____  _______  _______ ____   
             / ___||  _ \ \   / /\ \/ / |_ _| \ | |  _ \| ____\ \/ / ____|  _ \  
             \___ \| | | \ \ / /  \  /   | ||  \| | | | |  _|  \  /|  _| | |_) | 
              ___) | |_| |\ V /   /  \   | || |\  | |_| | |___ /  \| |___|  _ <  
             |____/|____/  \_/   /_/\_\ |___|_| \_|____/|_____/_/\_\_____|_| \_\ 
                                                            Made By waterlemon7z.
        """.trimIndent()
        )
        println("1. Get Music list from Japan Konasute (KONAMI)")
        println("2. Get Music list from Korea Konasute (USTA)")
        print("Input >> ")
        return readln().toInt()
    }
}