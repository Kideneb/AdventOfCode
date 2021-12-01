import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    var content = File("C:\\Users\\Benedikt Hahn\\Desktop\\Ordnerstruktur\\Code\\Git\\Github\\AdventOfCode\\2015\\src\\main\\resources\\day1.txt").readLines()[0]
    var x = 0
    var n = 0
    content.forEach { c -> n++
                            if (c.equals('(')) {
                                x++
                            } else {
                                x--
                            }
                            if (x == -1) {
                                println(n)
                            }
                        }
    println(x)
}