import java.lang.System.`in`
import java.util.*
import kotlin.math.pow
import kotlin.test.assertEquals

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

//10. Дана строка. Необходимо подсчитать количество букв "А" в этой
//строке.

tailrec fun countA(str : String, iter : Int, index : Int) : Int {
    return if(index <= 0) iter
    else{
        if(str[index] == 'A') countA(str, iter + 1, index - 1)
        else{
            countA(str, iter, index - 1)
        }
    }
}

//---------------------------------------------------------------------------------------------------------


//17. Дана строка в которой записан путь к файлу. Необходимо найти имя
//файла без расширения.


fun pathFile(str : String) : String {
    return str
        .drop(str.length-str.reversed().indexOf('/'))
        .dropLast(str.drop(str.length-str.reversed().indexOf('/')).indexOf(".")+1)
}

//---------------------------------------------------------------------------------------------------------


//Задание 3. Дана строка. Необходимо найти все даты, которые описаны в
//виде "31/02/2007"

//Для пользователя
//Идея : проверяем первое вхождение
//fun date(str : String, index : Int){
//    if()
//}
//
//fun validDate(str : String){
//
//
//    val reg = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d".toRegex()
//    val matchResult = reg.findAll(str, 0)
//    assertEquals(IntRange(0, 3), matchResult.range())
//
//}

//Задание 4:
//1.2 Дана строка. Необходимо найти все строчные символы латиницы,
//которые в ней используются.

//Для пользователя
fun allLowerSimbolLatin(str : String) : MutableList<Char> = run { allLowerSimbolLatin(str, str.length-1, mutableListOf<Char>()) }

tailrec fun allLowerSimbolLatin(str : String, index : Int, useSimbol : MutableList<Char>) : MutableList<Char> {
    return if(index <= 0) useSimbol
    else if(str[index] in 'a'..'z' && useSimbol.any{it == str[index]}) allLowerSimbolLatin(str, index - 1, useSimbol)
    else{
        if(str[index] in 'a'..'z'){
            useSimbol.add(str[index])
            allLowerSimbolLatin(str, index - 1, useSimbol)
        } else{
            allLowerSimbolLatin(str, index - 1, useSimbol)
        }
    }
}
//------------------------------------------------------------------------------------------------------------------------------------

//1.10 Дана строка. Необходимо найти количество задействованных
//символов латиницы в этой строке (без дубликатов).
fun countAllLowerSimbolLatin(str : String) : Int = countAllLowerSimbolLatin(str, str.length-1, mutableListOf<Char>()).size

tailrec fun countAllLowerSimbolLatin(str : String, index : Int, useSimbol : MutableList<Char>) : MutableList<Char> {
    return if(index <= 0) useSimbol
    else if(str[index] in 'A'..'z' && useSimbol.any{it == str[index]}) countAllLowerSimbolLatin(str, index - 1, useSimbol)
    else{
        if(str[index] in 'A'..'z'){
            useSimbol.add(str[index])
            countAllLowerSimbolLatin(str, index - 1, useSimbol)
        } else{
            countAllLowerSimbolLatin(str, index - 1, useSimbol)
        }
    }
}
//-------------------------------------------------------------------------------------------------------------------------------------

//1.17 Дана строка в которой записан путь к файлу. Необходимо найти имя
//файла без расширения.
//Смотреть выше


fun selectUser(){
    val scanner = Scanner(System.`in`)
    println("Добрый день!")
    do{
        println("Введите операцию : \n" +
                "1. Максимальное число в строке.\n" +
                "2. Упорядочены ли латинские символы.\n" +
                "3. Количество букв А.\n" +
                "4. Имя файла.\n" +
                "5. Найти дату в строке.\n" +
                "6. Все строчные символы латиницы.\n" +
                "7. Найти количество задействованных символов латиницы.\n"
                )

        val select = scanner.next()
        if(select == "Exit") { return }
        when(select){
            "1" -> maxDigitInString("521 63 4 2 99")
            "2" -> println(orderedCharacters("zvvs sv s vs fg",'z',13))
            "3" -> println(countA("zvAs sv s As fg",0,13))
            "4" -> println(pathFile("c:/sf/cs/vot.txt"))
            //"5" -> validDate("fs v 31/02/2007 fs vl 32/02/2003")
            "6" -> println(allLowerSimbolLatin("fs A 31F2g/2007 fh vl 32/02/2003"))
            "7" -> println(countAllLowerSimbolLatin("fs A 31F2g/2007 fh vl 32/02/2003"))

        }
        println("Введите любой символ для продолжения... : ")
        val ok : String = scanner.next()

    }while(select != "Exit")

}


fun main(){
    selectUser()
}
