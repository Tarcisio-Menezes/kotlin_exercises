package fundamentos

fun main() {
    var lista = listOf(1, 2, 3, 4, 5, 5) // lista imutável
    val pares = lista.filter { it % 2 == 0 }.first() // pega o primeiro dos numeros pares da lista
    println(pares)

//    mutable()
    setNumeros()
    mapIdade()
}

fun mutable() {
    var lista = mutableListOf(1, 2, 3, 6, 8, 9) // lista mutável
    lista.add(10)
    lista.removeAt(index = 0)
    lista.remove(2)
    lista[0] = 20 // sobrescreve último valor (pelo indice)
    println(lista)
    lista.sort() // ordena crescente
    println(lista)
    lista.shuffle() // embaralha
    println(lista)
}

fun setNumeros() {
    var numeros = setOf(1, 2, 3) // set é uma lista que não recebe valores duplicados
    println(numeros)

    var mutableNumbers = mutableSetOf(1, 2, 3)
    mutableNumbers.add(4)
    println(mutableNumbers)
}

fun mapIdade() {
    var nomeIdade = mapOf("Tarcisio" to 28, "Gregório" to 36) // associa dois valores, semelhante a objetos em js
    // não podem ter chaves duplicadas ele sobrescreve
    println(nomeIdade)

    val mutableNomeIdade = mutableMapOf("Tarcisio" to 28, "Gregório" to 36)
    mutableNomeIdade.put("Tarsila", 33)
    println(mutableNomeIdade)
    mutableNomeIdade.remove("Tarcisio")
    println(mutableNomeIdade)
    mutableNomeIdade.putIfAbsent("Tarsila", 39) // só adiciona caso a chava não exista
    println(mutableNomeIdade)
}