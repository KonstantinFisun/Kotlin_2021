import java.io.File
import java.lang.System.`in`
import java.util.*
import java.util.function.BinaryOperator.minBy
import java.util.HashSet
import java.util.stream.Collectors
import java.util.ArrayList
import java.util.Arrays














//Ввод массива с клавиатуры с использованием цикла
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

//Ввод без циклов
fun arrayInput(array : Array<Int>, size : Int) : Array<Int> = arrayInput(array, 0, size)

tailrec fun arrayInput(array : Array<Int>, counter : Int, size : Int) : Array<Int> =  if (counter == size) array else {
        array[counter] = readLine()!!.toInt()
        arrayInput(array, counter + 1, size)
    }

//Вывод массива с использованием цикла
fun <T>writeArray(numbers:Array<T>){
    var i=0
    while(i<numbers.size) {
        print(numbers[i])
        print(" ")
        i++
    }
    println("\n")
}

//Вывод массива без цикла
fun arrayOutput(array : Array<Int>, size : Int) = arrayInput(array, 0, size)

fun arrayOutput(array : Array<Int>, counter : Int, size : Int) {
    if (counter == size) return else {
        println(array[counter])
        arrayInput(array, counter + 1, size)
    }
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
tailrec fun minmaxDigitsDown(numbers: Array<Int>,index:Int,iter:Int,f : (Int,Int)->Boolean):Int = if(index<=0) iter else {
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


/*
1.1 Дан целочисленный массив. Необходимо найти количество
элементов, расположенных после последнего максимального.
*/
//Функция получения индекса максимального или минимального
tailrec fun minmaxDigitsDownIndex(numbers: Array<Int>,index:Int,index_of_iter : Int, iter:Int,f : (Int,Int)->Boolean) : Int = if(index<=0) index_of_iter else {
    if(f(iter,numbers[index-1]))
        minmaxDigitsDownIndex(numbers,index-1,index_of_iter, iter,f)
    else
        minmaxDigitsDownIndex(numbers,index-1,index-1,numbers[index-1],f)
}

fun task1_1(array : Array<Int>) : Int {
    return (-1 + array.size - minmaxDigitsDownIndex(array,array.size,array.size-1,array[array.size-1]) { a, b -> a >= b }) //размер массива - индекс последнего максимального
}

/*
1.2 Дан целочисленный массив. Необходимо найти индекс минимального элемента.
 */
fun task1_2(array : Array<Int>) : Int {
    return (minmaxDigitsDownIndex(array,array.size,array.size-1,array[array.size-1]) { a, b -> a < b })
}

/*
1.14 Дан целочисленный массив и интервал a..b. Необходимо найти
количество элементов в этом интервале.
 */
//Функция возврата интервала
fun inter() : Array<Int> {
    println("Введите первый элемент интервала")
    val number1 = readLine()!!.toInt()
    println("Введите второй элемент интервала")
    val number2 = readLine()!!.toInt()
    return arrayOf(number1, number2)
}

fun task1_14() : Int {
    val inter = inter()
    return inter[1]-inter[0]-1
}

/*
1.16 Дан целочисленный массив. Необходимо найти элементы,
расположенные между первым и вторым максимальным.
 */
//Нахождение индексов второго и первого максимального
data class IndexOfMax(val indexMaxFirst : Int, val indexMaxSecond: Int)

tailrec fun minmaxDigitsDown2(numbers: Array<Int>, index : Int, maxFirst : Int, indexMaxFirst : Int, maxSecond : Int, indexMaxSecond : Int, f : (Int,Int)->Boolean) : IndexOfMax =
    if(index<=0) IndexOfMax(indexMaxFirst-1, indexMaxSecond-1) else {
    if(f(maxFirst,numbers[index-1]))

        if(f(maxSecond,numbers[index-1]))
            minmaxDigitsDown2(numbers,index-1, maxFirst, indexMaxFirst, maxSecond, indexMaxSecond,f)
        else {
            minmaxDigitsDown2(numbers,index-1, maxFirst, indexMaxFirst, numbers[index-1],index,f)
        }
    else {
        minmaxDigitsDown2(numbers,index-1,  numbers[index-1], index,maxFirst, indexMaxFirst, f)
    }

}

//Определяем первый и второй макс
fun plTask1_16(array : Array<Int>,f : (Int,Int)->Boolean) : IndexOfMax =
    if(f(array[array.size-1],array[array.size-2])){
        minmaxDigitsDown2(array, array.size, array[array.size-1],array.size-1, array[array.size-2], array.size-2, f)
    }
    else {
        minmaxDigitsDown2(array, array.size, array[array.size-2], array.size-2, array[array.size-1],array.size-1, f)
    }


fun task1_16(array : Array<Int>) : List<Int> {
    val (indexMaxFirst, indexMaxSecond) = plTask1_16(array) { a, b -> a >= b }
    println("$indexMaxFirst $indexMaxSecond")
    return if(indexMaxFirst>indexMaxSecond) {
        array.filterIndexed { index, _ ->(index < indexMaxFirst) && (index > indexMaxSecond) }
    }
    else {
        array.filterIndexed { index, _ ->(index > indexMaxSecond) && (index < indexMaxFirst) }
    }
}

/*
1.26 Дан целочисленный массив. Необходимо найти количество
элементов между первым и последним минимальным.
 */
fun task1_26(array : Array<Int>) : List<Int> {
    val indexMinLast = minmaxDigitsDownIndex(array,array.size,array.size-1,array[array.size-1]) { a, b -> a <= b }
    return  array.filterIndexed { index, _ ->(index < indexMinLast) }
}

/*
1.29 Дан целочисленный массив и интервал a..b. Необходимо проверить
наличие максимального элемента массива в этом интервале.
 */
fun task1_29(array : Array<Int>) : Boolean {
    val inter = inter()
    return array.max()!! > inter[0] && array.max()!! < inter[1]
}

/*
1.38 Дан целочисленный массив и отрезок a..b. Необходимо найти
количество элементов, значение которых принадлежит этому отрезку.
 */
tailrec fun task1_38(array : Array<Int>,index : Int, count : Int = 0, inter : Array<Int>) : Int {
    return if (index <= 0) return count
    else {
        if (array[index] in inter[0]..inter[1]) task1_38(array, index - 1, count + 1, inter)
        else {
            task1_38(array, index - 1, count, inter)
        }
    }
}

/*
1.44 Дан массив чисел. Необходимо проверить, чередуются ли в нем
целые и вещественные числа.
 */
tailrec fun task1_44(array : Array<Any>,index : Int) : Boolean {
    return if (index <= 0) true
    else {
        if (array[index-1] is Int && array[index] is Int) {
            false
        }
        else {
            if (array[index - 1] is Double && array[index] is Double) false
            else {
                task1_44(array, index - 1)
            }
        }
    }
}

//Задание 5. Реализовать задание 1 для коллекции — список

//Ввод
fun listInput(): List<Int> {
    print("Bведите размер списка: ")
    val size = readLine()!!.toInt()
    return listInput( size)
}

fun listInput(size: Int) : MutableList<Int> { val list: MutableList<Int> = mutableListOf<Int>()   // ввод массива с клавиатуры
    return listInput(list, 0, size)
}

tailrec fun listInput(list : MutableList<Int>, counter : Int, size : Int) : MutableList<Int> =

    if (counter == size) list else {
        val x = readLine()!!.toInt()
        list.add(x)
        listInput(list, counter + 1, size)
    }

fun listInputFile(input : Map<Int, Int>) : MutableList<Int> {
    var list: MutableList<Int> = mutableListOf<Int>()
    list.remove(0)
    return listInputFile(list, 0, input.size, input)
}

tailrec fun listInputFile(list : MutableList<Int>, counter : Int, size : Int, input : Map<Int, Int>) : MutableList<Int> =
    if (counter == size) list else {
        list.add(input[counter]!!)
        listInputFile(list, counter + 1, size, input)
    }

//Задание 5. Реализовать задание 1 для коллекции — список
tailrec fun digitsDown(numbers: MutableList<Int>,index:Int, iter: Int, f : (Int , Int) -> Int): Int =
    if(index<=0) iter else
    {
        digitsDown(numbers,index-1,f(iter,numbers[index-1]),f)
    }

fun sumDigitsArray(numbers: MutableList<Int>,index:Int): Int = digitsDown(numbers,index,0) { a, b -> (a + b) }
fun mulDigitsArray(numbers: MutableList<Int>,index:Int): Int = digitsDown(numbers,index,1) { a, b -> (a * b) }

//Максимальный и минимальный
tailrec fun minmaxDigitsDown(numbers: MutableList<Int>,index:Int,iter:Int,f : (Int,Int)->Boolean):Int = if(index<=0) iter else {
    if(f(iter,numbers[index-1]))
        minmaxDigitsDown(numbers,index-1,iter,f)
    else
        minmaxDigitsDown(numbers,index-1,numbers[index-1],f)
}

fun maxDigitsArray(numbers: MutableList<Int>,index:Int): Int = minmaxDigitsDown(numbers,index,numbers[index-1]) { a, b -> a > b }
fun minDigitsArray(numbers: MutableList<Int>,index:Int): Int = minmaxDigitsDown(numbers,index,numbers[index-1]) { a, b -> a < b }

//функция выбора
fun arrayOp(numbers: MutableList<Int>){
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

//Задание 6. Переписать задание 5 с использование встроенных методов
fun sumDigitsList(numbers: MutableList<Int>) : Int {
    return numbers.sum()
}

fun maxDigitsList(numbers: MutableList<Int>) : Int? {
    return numbers.max()
}

fun minDigitsList(numbers: MutableList<Int>) : Int? {
    return numbers.min()
}

//-----------------------------------------------------------

//Задание 8. Выполнить задание 4 с использованием структуры — список

/*
1.1 Дан целочисленный массив. Необходимо найти количество
элементов, расположенных после последнего максимального.
*/
//Функция получения индекса максимального или минимального
tailrec fun minmaxDigitsDownIndex(numbers: MutableList<Int>,index:Int,index_of_iter : Int, iter:Int,f : (Int,Int)->Boolean) : Int = if(index<=0) index_of_iter else {
    if(f(iter,numbers[index-1]))
        minmaxDigitsDownIndex(numbers,index-1,index_of_iter, iter,f)
    else
        minmaxDigitsDownIndex(numbers,index-1,index-1,numbers[index-1],f)
}

fun task1_1(list : MutableList<Int>) : Int {
    return (-1 + list.size - minmaxDigitsDownIndex(list,list.size,list.size-1,list[list.size-1]) { a, b -> a >= b }) //размер массива - индекс последнего максимального
}

/*
1.2 Дан целочисленный массив. Необходимо найти индекс минимального элемента.
 */
fun task1_2List(list : MutableList<Int>) : Int {
    return list.indices.maxBy { list[it] } ?: -1
}

/*
1.14 Дан целочисленный массив и интервал a..b. Необходимо найти
количество элементов в этом интервале.
 */
//Функция возврата интервала
fun interList() : MutableList<Int> {
    println("Введите первый элемент интервала")
    val number1 = readLine()!!.toInt()
    println("Введите второй элемент интервала")
    val number2 = readLine()!!.toInt()
    return mutableListOf(number1, number2)
}

fun task1_14List() : Int {
    val inter = interList()
    return inter[1]-inter[0]-1
}

/*
1.16 Дан целочисленный массив. Необходимо найти элементы,
расположенные между первым и вторым максимальным.
 */
//Нахождение индексов второго и первого максимального
tailrec fun minmaxDigitsDown2(numbers: MutableList<Int>, index : Int, maxFirst : Int, indexMaxFirst : Int, maxSecond : Int, indexMaxSecond : Int, f : (Int,Int)->Boolean) : IndexOfMax =
    if(index<=0) IndexOfMax(indexMaxFirst-1, indexMaxSecond-1) else {
        if(f(maxFirst,numbers[index-1]))

            if(f(maxSecond,numbers[index-1]))
                minmaxDigitsDown2(numbers,index-1, maxFirst, indexMaxFirst, maxSecond, indexMaxSecond,f)
            else {
                minmaxDigitsDown2(numbers,index-1, maxFirst, indexMaxFirst, numbers[index-1],index,f)
            }
        else {
            minmaxDigitsDown2(numbers,index-1,  numbers[index-1], index,maxFirst, indexMaxFirst, f)
        }

    }

//Определяем первый и второй макс
fun plTask1_16List(list : MutableList<Int>,f : (Int,Int)->Boolean) : IndexOfMax =
    if(f(list[list.size-1],list[list.size-2])){
        minmaxDigitsDown2(list, list.size, list[list.size-1],list.size-1, list[list.size-2], list.size-2, f)
    }
    else {
        minmaxDigitsDown2(list, list.size, list[list.size-2], list.size-2, list[list.size-1],list.size-1, f)
    }


fun task1_16List(list : MutableList<Int>) : List<Int> {
    val (indexMaxFirst, indexMaxSecond) = plTask1_16List(list) { a, b -> a >= b }
    println("$indexMaxFirst $indexMaxSecond")
    return if(indexMaxFirst>indexMaxSecond) {
        list.filterIndexed { index, _ ->(index < indexMaxFirst) && (index > indexMaxSecond) }
    }
    else {
        list.filterIndexed { index, _ ->(index > indexMaxSecond) && (index < indexMaxFirst) }
    }
}

/*
1.26 Дан целочисленный массив. Необходимо найти количество
элементов между первым и последним минимальным.
 */
fun task1_26List(list : MutableList<Int>) : List<Int> {
    val indexMinLast = minmaxDigitsDownIndex(list,list.size,list.size-1,list[list.size-1]) { a, b -> a <= b }
    return  list.filterIndexed { index, _ ->(index < indexMinLast) }
}

/*
1.29 Дан целочисленный массив и интервал a..b. Необходимо проверить
наличие максимального элемента массива в этом интервале.
 */
fun task1_29List(array : MutableList<Int>) : Boolean {
    val inter = interList()
    return array.max()!! > inter[0] && array.max()!! < inter[1]
}

/*
1.38 Дан целочисленный массив и отрезок a..b. Необходимо найти
количество элементов, значение которых принадлежит этому отрезку.
 */
tailrec fun task1_38List(list : MutableList<Int>,index : Int, count : Int = 0, interList : MutableList<Int>) : Int {
    return if (index <= 0) return count
    else {
        if (list[index] in interList[0]..interList[1]) task1_38List(list, index - 1, count + 1, interList)
        else {
            task1_38List(list, index - 1, count, interList)
        }
    }
}

/*
1.44 Дан массив чисел. Необходимо проверить, чередуются ли в нем
целые и вещественные числа.
 */
tailrec fun task1_44List(list : MutableList<Any>,index : Int) : Boolean {
    return if (index <= 0) true
    else {
        if (list[index-1] is Int && list[index] is Int) {
            false
        }
        else {
            if (list[index - 1] is Double && list[index] is Double) false
            else {
                task1_44List(list, index - 1)
            }
        }
    }
}

//1.50. Для двух введенных списков L1 и L2 построить новый список, состоящий
//из элементов, встречающихся только в одном из этих списков и не
//повторяющихся в них.
fun task1_50List(list1 : MutableList<Int>,list2 : MutableList<Int>) : MutableList<Int> {

    val list1WithoutDuplicates: MutableList<Int> = list1.stream().distinct().collect(Collectors.toList())

    val list2WithoutDuplicates: MutableList<Int> = list2.stream().distinct().collect(Collectors.toList())

    val list3 : List<Int> = list1WithoutDuplicates+list2WithoutDuplicates

    return list3.stream().distinct().collect(Collectors.toList())
}





fun main() {
    val list1 : MutableList<Int> = mutableListOf(1,2,3,4,5,6,7,2,3,5)
    val list2 : MutableList<Int> = mutableListOf(3,3,3,4,8,6,9,2,3,5)
    println(task1_50List(list1,list2))
}