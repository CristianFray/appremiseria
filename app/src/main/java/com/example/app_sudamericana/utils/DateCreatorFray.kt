package com.example.app_sudamericana.utils

class DateCreatorFray {

    val dia : Int = 0
    val mes : Int = 0
    val año : Int = 0
    var hora : Int = 0
    val minuto : Int = 0
    val segundo : Int = 0

    private fun getFecha():String{

        return "${año}-${mes}-${dia}T${hora}-${minuto}-${segundo}"

    }
}

