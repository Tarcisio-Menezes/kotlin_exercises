package fundamentos

fun main() {
//    print1a10()
//    print10a1()
    whileMenorQue10()
}

fun print1a10() {
    for (numero in 1 .. 10) {
        println(numero)
    }
}

fun print10a1() {
    for (numero in 10 downTo 1) {
        println(numero)
    }
}

fun whileMenorQue10() {
    var x = 0
    while(x <= 10) {
        println(x)
        x++
    }
}
// diferença com o while é que ele roda uma primeira vez independente da condição imposta
fun doWhileMenorQue10() {
    var x = 0;
    do {
        println(x)
        x++
    } while (x <= 10)
}