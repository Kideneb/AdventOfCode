import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

fun main (args: Array<String>) {
    val f = File("C:\\Users\\Benedikt Hahn\\Desktop\\Ordnerstruktur\\Code\\Git\\Github\\AdventOfCode\\2015\\src\\main\\resources\\day2.txt")
    val ls = f.readLines().map { str -> str.split("x") }.map { xs -> xs.map { x -> x.toInt()} }
    val res1 = ls.map { xs -> calcSurface(xs[0], xs[1], xs[2]) }.fold(0){ a, b -> a + b}
    val res2 = ls.map { xs -> calcPerimeter(xs[0], xs[1], xs[2]) }.fold(0){ a, b -> a + b}
    println("Part 1: $res1")
    println("Part 2: $res2")
}

fun calcSurface (x: Int, y: Int, z: Int): Int {
    return 2 * (x * y + x * z + y * z) + min(x * y, min(x*z, y*z))
}

fun calcPerimeter (x: Int, y: Int, z: Int): Int {
    return 2 * (x+y+z - max(x, max(y, z))) + x*y*z
}