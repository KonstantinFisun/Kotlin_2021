import java.lang.System.`in`
import java.util.*
import kotlin.math.pow

//Задание 1. Дана строка в которой числа перечислены через пробел.
//Необходимо найти максимальное из этих чисел.

//Максимальная подстрочка
tailrec fun maxminSubStr(digit : List<String>, index : Int, max : Int, f : (Int,Int)->Boolean) : Int {
    return if(index <= 0) max
    else{
        if(f(max,digit[index-1].toInt()))
            maxminSubStr(digit,index-1,max,f)
        else
            maxminSubStr(digit,index-1,digit[index-1].toInt(),f)
    }

}

fun maxDigitInString(str : String) : Int {
    val digit : List<String> = Regex(""" """).split(str)
    return maxminSubStr(digit, digit.size-1, digit.last().toInt()){ a, b -> a > b }
}

//---------------------------------------------------------------------------------------------------------



fun selectUser(){
    val scanner = Scanner(System.`in`)
    println("Добрый день!")
    do{
        println("Введите операцию : \n")

        val select = scanner.next()
        if(select == "Exit") { return }
        when(select){

        }
        println("Введите любой символ для продолжения... : ")
        val ok : String = scanner.next()

    }while(select != "Exit")

}


fun main(){
    println(maxDigitInString("5 63 4 2 99"))
}