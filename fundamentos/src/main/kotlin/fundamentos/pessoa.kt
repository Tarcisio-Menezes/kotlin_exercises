package fundamentos

class Pessoa(var name: String, var age: Int) {
    override fun toString(): String {
        return "Classe: Pessoa. Nome: $name, idade: $age"
    }
}

fun main() {
    var tarcisio = Pessoa( "Tarcisio", age = 28 )
    println(tarcisio)
}
