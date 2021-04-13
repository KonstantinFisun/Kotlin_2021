import java.lang.System.`in`
import java.util.*

//Суммы элементов рекурсия вверх
fun sumElemUp(num:Int):Int = if(num==0) 0 else (num%10)+sumElemUp(num/10)

//Сумма элементов рекурсия вниз
//Для пользователя
fun sumElemDown(num:Int) = sumElemDown(num,0)
tailrec fun sumElemDown(num:Int,sum:Int):Int = if(num>0) sumElemDown(num/10,sum+(num%10)) else sum

//Произведение элементов рекурсия вверх
fun mulElemUp(num:Int):Int = if(num==0) 1 else (num%10)*mulElemUp(num/10)


//Произведение элементов рекурсия вниз
//Для пользователя

fun mulElemDown(num:Int) = mulElemDown(num,1)

tailrec fun mulElemDown(num:Int,mul:Int):Int = if(num>0) mulElemDown(num/10,mul*(num%10)) else mul


//Максимальное цифра в числе рекурсия вниз

fun maxElemDown(num:Int)=maxElemDown(num/10,num%10)

tailrec fun maxElemDown(num:Int,max:Int):Int = if(num==0) max else
{
    if (num % 10 > max) maxElemDown(num / 10, num % 10)
    else maxElemDown(num / 10, max)
}


//Максимальное цифра в числе рекурсия вверх

fun max(a : Int, b : Int) = if (a > b) a else b
fun maxElemUp(num : Int) : Int = if (num == 0) -1 else max(maxElemUp(num / 10), num % 10)


//Минимальная цифра в числе рекурсия вниз

fun minElemDown(num:Int)=minElemDown(num/10,num%10)

tailrec fun minElemDown(num:Int,min:Int):Int = if(num==0) min else
{
    if (num % 10 < min) minElemDown(num / 10, num % 10)
    else minElemDown(num / 10, min)
}


//Минимальная цифра в числе рекурсия вверх

fun min(a : Int, b : Int) = if (a < b) a else b
fun minElemUp(num : Int) : Int = if (num < 10) num else min(minElemUp(num / 10), num % 10)


fun main() {


    val scanner = Scanner(`in`)
    val s:Int = scanner.nextInt()

    println("Сумма элементов рекурсия вверх ${sumElemUp(s)}")
    println("Сумма элементов рекурсия вниз ${sumElemDown(s)}")

    println("Произведение элементов рекурсия вверх ${mulElemUp(s)}")
    println("Произведение элементов рекурсия вниз ${mulElemDown(s)}")

    println("Максимальный элемент рекурсия вверх ${maxElemUp(s)}")
    println("Максимальный элемент рекурсия вниз ${maxElemDown(s)}")

    println("Минимальный элемент рекурсия вверх ${minElemUp(s)}")
    println("Минимальный элемент рекурсия вниз ${minElemDown(s)}")

}

