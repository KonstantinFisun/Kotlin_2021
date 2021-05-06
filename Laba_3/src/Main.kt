import java.io.BufferedReader
import java.io.File
import java.lang.System.`in`
import java.nio.file.Files
import java.util.*
import java.nio.file.Paths

fun readArray(): Array<Int> {
    val scanner = Scanner(`in`)
    println("Введите количество элементов массива\n")
    val kolElem:Int = scanner.nextInt()

    val numbers:Array<Int> = Array(kolElem){0}
    var i=0
    println("Введите элементы массива\n")
    while(i<numbers.size){
        val s:Int = scanner.nextInt()
        numbers[i] = s
        i++
    }
    return numbers
}

fun writeArray(numbers:Array<Int>){
    var i=0
    while(i<numbers.size) {
        print(numbers[i])
        print(" ")
        i++
    }
    println("\n")
}

//Сумма и умножение
tailrec fun digitsDown(numbers: Array<Int>,index:Int, iter: Int, f : (Int , Int) -> Int): Int =
    if(index<=0) iter else
    {
        digitsDown(numbers,index-1,f(iter,numbers[index-1]),f)
    }

fun sumDigitsArray(numbers: Array<Int>,index:Int): Int = digitsDown(numbers,index,0) { a, b -> (a + b) }
fun mulDigitsArray(numbers: Array<Int>,index:Int): Int = digitsDown(numbers,index,1) { a, b -> (a * b) }

//Максимальный и минимальный
tailrec fun minmaxDigitsDown(numbers: Array<Int>,index:Int,iter:Int,f : (Int,Int)->Boolean):Int =
    if(index<=0) iter else
    {
        if(f(iter,numbers[index-1]))
            minmaxDigitsDown(numbers,index-1,iter,f)
        else
            minmaxDigitsDown(numbers,index-1,numbers[index-1],f)
    }

fun maxDigitsArray(numbers: Array<Int>,index:Int): Int = minmaxDigitsDown(numbers,index,numbers[index-1]) { a, b -> a > b }
fun minDigitsArray(numbers: Array<Int>,index:Int): Int = minmaxDigitsDown(numbers,index,numbers[index-1]) { a, b -> a < b }

//функция выбора
fun arrayOp(numbers: Array<Int>){
    val scanner = Scanner(`in`)
    do{
        println("Введите операцию : \n" +
                "+. Сумма цифр массива.\n" +
                "*. Произведение цифр массива. \n" +
                "Min. Минимальная цифра массива. \n" +
                "Max. Максимальная цифра массива. \n")
        when(scanner.next()){
            "+" -> {
                println(sumDigitsArray(numbers,numbers.size))
                return
            }
            "*" -> {
                println(mulDigitsArray(numbers,numbers.size))
                return
            }
            "Min" -> {
                println(minDigitsArray(numbers,numbers.size))
                return
            }
            "Max" -> {
                println(maxDigitsArray(numbers,numbers.size))
                return
            }
        }

    }while(true)
}

//Ввод без циклов
fun arrayInput(array : Array<Int>, size : Int) : Array<Int> =
    arrayInput(array, 0, size)

tailrec fun arrayInput(array : Array<Int>, counter : Int, size : Int) : Array<Int> =
    if (counter == size) array else {
        array[counter] = readLine()!!.toInt()
        arrayInput(array, counter + 1, size)
    }

//Ввод из файла
fun arrayInputFile(input : Map<Int, Int>) : Array<Int> {
    val numbers:Array<Int> = Array(input.size){0}
    return arrayInputFile(numbers, 0, input.size, input)
    }

fun arrayInputFile(array : Array<Int>, counter : Int, size : Int, input : Map<Int, Int>) : Array<Int> =
    if (counter == size) array else {
        array[counter] = input[counter]!!
        arrayInputFile(array, counter + 1, size, input)
    }

//Организация чтения из файла
fun inputFile(fileName:String) : Array<Int> {
    val input = File(fileName).readLines()
        .withIndex()
        .map{ indexedValue -> indexedValue.index to indexedValue.value.toInt() }
        .toMap()


    return arrayInputFile(input)
}

//Функция выбора источника считывания(Клавиатура или файл)
fun selectInput() : Array<Int>{
    println("Откуда считывать массив?\n" +
            "Клавиатура\n" +
            "Файл")
    val scanner = Scanner(`in`)
    do {

        when (scanner.next()) {
            "Файл" -> {
                println("Введите имя файла: ")
                return inputFile(scanner.next())
            }
            "Клавиатура" -> return readArray()
            else -> { println("Введите заново") }
        }

    }while(true)
}



fun main() {

    val numbers = selectInput()

    writeArray(numbers)





}