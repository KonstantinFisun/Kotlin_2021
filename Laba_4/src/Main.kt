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

//2. Дана строка, состоящая из символов латиницы. Необходимо проверить,
//упорядочены ли строчные символы этой строки по возрастанию.

tailrec fun orderedCharacters(str : String, pred_sim : Char, index : Int) : Boolean {
    return if(index <= 0) true
    else{
        if(str[index] in 'a'..'z') {
            if(str[index]<pred_sim) false
            else{
                orderedCharacters(str,str[index],index-1)
            }
        }
        else {
            orderedCharacters(str, pred_sim, index - 1)
        }
    }
}

//----------------------------------------------------------------------------------------------------------


fun selectUser(){
    val scanner = Scanner(System.`in`)
    println("Добрый день!")
    do{
        println("Введите операцию : \n" +
                "1. Максимальное число в строке.\n" +
                "2. Упорядочены ли латинские символы")

        val select = scanner.next()
        if(select == "Exit") { return }
        when(select){
            "1" -> maxDigitInString("521 63 4 2 99")
            "2" -> println(orderedCharacters("amvs sv s vs fg",'a',13))

        }
        println("Введите любой символ для продолжения... : ")
        val ok : String = scanner.next()

    }while(select != "Exit")

}


fun main(){
    selectUser()
}