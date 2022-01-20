package fundamentos

fun main() {
//    whatsNumber(12)
//    println(comecaComOi("oi tio Ali"))
    utils()
}

fun whatsNumber(x: Int) {
    when(x) {
        5 -> println("x é 5")
        8 -> println("x é 8")
        10 -> {
            println("Número premiado")
            println("Aeee esse é o 10")
        }
        !in 16 .. 20 -> println("não está de 16 a 20")
        in 11 .. 15 -> println("este é um intervalo")
        else -> println("número não cadastrado")
    }
}

fun comecaComOi(x: Any): Boolean {
    return when(x) {
        is String -> x.startsWith(prefix = "oi")
        else -> false
    }
}

// when procura sempre pela expressão verdadeira
// como a linha 35 retorna false, ele não printa a mensagem 5
fun utils() {
    when {
        comecaComOi("5") -> println("5")
        comecaComOi("oi, buenos dias") -> println("bingoo")
    }
}
