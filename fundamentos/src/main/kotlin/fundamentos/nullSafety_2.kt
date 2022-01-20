package fundamentos

fun main() {
    val pessoa: Pessoa? = Pessoa("Tarcísio", age = 28)
    println(pessoa!!.name) // aqui eu digo que tenho certeza que pessoa.name não é null
}