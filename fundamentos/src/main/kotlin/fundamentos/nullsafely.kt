package fundamentos

fun main() {
    var nome: String? = "ciso" // ou null
    println(nome?.length)

    var tamanho: Int = nome?.length ?: 0
    println(tamanho)

    var lista: List<Int?> = listOf(1, 2, 3, null, null, 7) // lista composta por numeros inteiros e elementos nulos
    var listaNula: List<Int>? = null // lista composta por numeros inteiros e A LISTA pode ser nula
}