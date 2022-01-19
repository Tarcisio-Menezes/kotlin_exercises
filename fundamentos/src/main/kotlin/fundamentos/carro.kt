package fundamentos

class Carro(val color: String, val year: Int, val proprietary: Proprietary) {
}

class Proprietary(var name: String, var age: Int) {
    override fun toString(): String {
        return "Dono: $name, $age"
    }
}

fun main() {
    var carro = Carro(color = "Branco", year = 2021, Proprietary(name = "Cisão", age = 28))
    println(carro.proprietary)
}