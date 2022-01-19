package fundamentos

fun main() {
    parOuImpar(5)
}

fun parOuImpar(numero: Int) {
    val resto = numero % 2
    if(resto == 0) {
        println("este é par")
    } else {
        println("este é ímpar")
    }
}