import java.io.File

fun main(args: Array<String>) {
    val content = File("C:\\Users\\Benedikt Hahn\\Desktop\\Ordnerstruktur\\Code\\Git\\Github\\AdventOfCode\\2015\\src\\main\\resources\\day1.txt").readLines()[0]
    var x = 0
    var n = 0
    content.forEach { c -> n++
                            if (c.equals('(')) {
                                x++
                            } else {
                                x--
                            }
                            if (x == -1 && n >= x) {
                                println("Part 1: $n")
                                n = Int.MIN_VALUE
                            }
                        }
    println("Part 2: $x")
}