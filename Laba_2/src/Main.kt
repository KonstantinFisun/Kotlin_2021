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


/*
Задание 4. Написать функцию обход числа, которая выполняет операции
на цифрами числа, принимает три аргумента, число, функция (например,
сумма, произведение, минимум, максимум) и инициализирующее
значение. Функция должна иметь два Int аргумента и возвращать Int.
 */

//Сумма и умножение
tailrec fun digitsDown(num: Int, iter: Int, f : (Int , Int) -> Int):Int =
     if(num==0) iter else
     {
         digitsDown(num/10,f(iter,num%10),f)
     }

fun sumDigits(num:Int): Int = digitsDown(num,0) { a, b -> (a + b) }
fun mulDigits(num:Int): Int = digitsDown(num,1) { a, b -> (a * b) }


//Максимальный и минимальный

tailrec fun minmaxDigitsDown(num:Int,iter:Int,f : (Int,Int)->Boolean):Int =
    if(num==0) iter else
    {
        if(f(iter,num%10))
            minmaxDigitsDown(num/10,iter,f)
        else
            minmaxDigitsDown(num/10,num%10,f)
    }

fun maxDigits(num:Int): Int = minmaxDigitsDown(num/10,num%10) { a, b -> a > b }
fun minDigits(num:Int): Int = minmaxDigitsDown(num/10,num%10) { a, b -> a < b }


/*Задание 5. Реализовать функцию обход числа с условием, которая
выполняет операции над цифрами, если цифры удовлетворяют заданному
условию.
 */

//Сумма и деление с условием pr
tailrec fun digitsDown(num:Int,iter:Int,f:(Int,Int)->Int,pr:(Int)->Boolean):Int =
    if(num==0) iter else
    {
        //val iter1 = if(pr(num%10)) f(iter,(num%10)) else iter
        digitsDown(num/10,if(pr(num%10)) f(iter,(num%10)) else iter,f,pr)
    }

//Максимальный и минимальный с условием pr

tailrec fun minmaxDigitsDown(num:Int,iter:Int,f : (Int,Int)->Boolean,pr:(Int)->Boolean):Int =
    if(num==0) iter else
    {
        if(pr(num%10) && f(num%10,iter))
            minmaxDigitsDown(num/10,num%10,f,pr)
        else
            minmaxDigitsDown(num/10,iter,f,pr)
    }

//Сумма цифр, которые делятся на 3
fun prDigitsShar3(num:Int) = digitsDown(num,0,{ a, b -> (a + b)}, { a ->(a%3==0)})
//максимальный среди четный
fun prMaxDigitsShar2(num:Int) = minmaxDigitsDown(num,-1,{ a, b -> (a > b)}, { a ->(a%2==0)})
//Минимальный среди от 3 до 7
fun prMinDigitsLess7to3(num:Int) = minmaxDigitsDown(num,8,{ a, b -> (a < b)}, { a ->(a in 3..7)})


//Метод 1. Найти количество чисел, взаимно простых с заданным.
tailrec fun nod(a : Int, b : Int) : Int = if (b == 0) a else nod(b, a % b)  //НОД

fun firstMetod(num : Int) : Int = firstMetod(num,1,0)

tailrec fun firstMetod(num : Int,index : Int,kolvo : Int) : Int =
    if(index > num) kolvo
    else {
        if(nod(num , index) == 1) firstMetod(num,index+1,kolvo+1)
        else {
            firstMetod(num,index+1,kolvo)
        }
    }

//Метод 2. Найти сумму цифр числа, делящихся на 3.
fun secondMetod(num:Int) = digitsDown(num,0,{ a, b -> (a + b)}, { a ->(a%3==0)})

fun main() {


    val scanner = Scanner(`in`)
    val s:Int = scanner.nextInt()
    println("Метод 1 : ${firstMetod(s)}")
    /*
    println("Сумма элементов рекурсия вверх ${sumElemUp(s)}")
    println("Сумма элементов рекурсия вниз ${sumElemDown(s)}")

    println("Произведение элементов рекурсия вверх ${mulElemUp(s)}")
    println("Произведение элементов рекурсия вниз ${mulElemDown(s)}")

    println("Максимальный элемент рекурсия вверх ${maxElemUp(s)}")
    println("Максимальный элемент рекурсия вниз ${maxElemDown(s)}")

    println("Минимальный элемент рекурсия вверх ${minElemUp(s)}")
    println("Минимальный элемент рекурсия вниз ${minElemDown(s)}")

    println("Сумма элементов ${sumDigits(s)}")
    println("Произведение элементов ${mulDigits(s)}")
    println("Максимальный элемент ${maxDigits(s)}")
    println("Минимальный элемент ${minDigits(s)}")

    println("Сумма элементов, которые делятся на 3 : ${prDigitsShar3(s)}")
    println("Максимальный элемент, который делится на 2 : ${prMaxDigitsShar2(s)}")
    println("Минимальный элемент, в диапазоне от 3 до 7 : ${prMinDigitsLess7to3(s)}")

     */

}

