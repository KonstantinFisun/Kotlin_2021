import java.lang.System.`in`
import java.util.*

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
tailrec fun digitsDown(numbers: Array<Int>,index:Int, iter: Int, f : (Int , Int) -> Int):Int =
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



fun main() {

    val numbers:Array<Int> = readArray()
    val sizeNumbers:Int=numbers.size
    println(sizeNumbers)

    writeArray(numbers)
    
    println("Сумма элементов массива = ${sumDigitsArray(numbers,sizeNumbers)}")
    println("Произведение элементов массива = ${mulDigitsArray(numbers,sizeNumbers)}")


    println("Максимальный элемент массива = ${maxDigitsArray(numbers,sizeNumbers)}")
    println("Минимальный элемент массива = ${minDigitsArray(numbers,sizeNumbers)}")

    println("Сумма элементов массива через метод = ${numbers.sum()}")
    println("Произведение элементов массива через метод = ${numbers.min()}")
    println("Максимальный элементов массива через метод = ${numbers.max()}")


}