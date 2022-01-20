package fundamentos

class MinhaClasse(
    var nome: String,
    var endereco: String,
    var idade: Int,
) {
    companion object {
        fun criarComValoresPadrao(): MinhaClasse {
            return MinhaClasse(nome = "Tarcísio", endereco = "Rua X", idade = 28)
        }
    }
}

class SegundaClasse(
    var nome: String,
    var endereco: String,
    var idade: Int,
) {
    fun criarComValoresPadrao(): SegundaClasse {
        return SegundaClasse(nome = "Tarcísio", endereco = "Rua X", idade = 28)
    }
}

fun main() {
    var segundaClasse = SegundaClasse(nome = "Tarcísio", endereco = "Rua X", idade = 28).criarComValoresPadrao()
    println(segundaClasse)
    var minhaClasse = MinhaClasse.criarComValoresPadrao()
    println(minhaClasse)
}