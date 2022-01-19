package fundamentos

fun main() {
    println(returnName())
    sayHi(name = "Cisão")
    sayHi(name = returnName())
}

fun returnName(): String {
    return "Cisão"
}

fun sayHi(name: String) {
    println("Hi, $name")
}