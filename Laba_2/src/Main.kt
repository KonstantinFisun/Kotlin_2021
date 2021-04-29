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

//Метод 3. Найти делитель числа, являющийся взаимно простым с
//наибольшим количеством цифр данного числа.

//количество взаимно простых с данной цифрой

fun countMutuallySimpleDigit(digit : Int, num : Int) : Int = countMutuallySimpleDigit(digit , num , 0)

tailrec fun countMutuallySimpleDigit(digit : Int, num : Int,kolvo : Int) : Int =
    if(num == 0) kolvo
    else {
        if(nod(num % 10, digit) == 1) countMutuallySimpleDigit(digit , num / 10 , kolvo+1)
        else {
            countMutuallySimpleDigit(digit , num / 10, kolvo)
        }
    }

fun threeMetod(num : Int) :Int = threeMetod(num , 2 , 0 , 0)

tailrec fun threeMetod(num : Int , index : Int , maxMutuallySimple : Int , divisor : Int) : Int =
    if(index >= num) divisor
    else {
        if(num % index == 0) {
            if(maxMutuallySimple<countMutuallySimpleDigit(index , num)) threeMetod(num , index + 1 , countMutuallySimpleDigit(index , num) , index)
            else {
                threeMetod(num , index + 1, maxMutuallySimple , divisor)
            }
        } else {
            threeMetod(num , index + 1, maxMutuallySimple , divisor)
        }
    }

/*
Задача 12. Последовательность треугольных чисел создается путем сложения натуральных чисел.
Таким образом, 7- й номер треугольника будет 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. Первые десять членов будут такими:
1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
Перечислим множители первых семи чисел треугольника:
1 : 1
 3 : 1,3
 6 : 1,2,3,6
10 : 1,2,5,10
15 : 1,3,5,15
21 : 1,3,7,21
28 : 1,2, 4,7,14,28
Какое значение имеет первое число треугольника, которое имеет более пятисот делителей?
*/

//Количество делителей числа
fun countDivisors(digit : Int) = countDivisors(digit, 1, 0) //Вызов

tailrec fun countDivisors(digit : Int, index : Int,count : Int) : Int =
    if (index >= digit) count
    else {
        if (digit % index == 0) countDivisors(digit, index + 1, count + 1)
        else countDivisors(digit, index + 1, count)
    }

fun task12() : Int = task12(0, 1) //Вызов

tailrec fun task12(triangle : Int, index : Int) : Int =

    if (countDivisors(triangle) > 500)  triangle
    else{
        task12(triangle + index, index + 1)
    }
//----------------------------------------------------------

//Задача 32. Дано a*b=c. Найти сумму всех таких c, что в результате объединения символов abc
// получим последовательность из 1..9 причем по одному разу

fun task32(): Int = task32(0,1)

fun task32(sum : Int,index : Int): Int =
    if (index > 9999) sum
    else {
        if (pandigitalProduct_1_9(index)) task32(sum + index, index + 1)
        else {
            task32(sum, index + 1)
        }
    }


//Определяем, что из заданного числа можно получить пандигитал
fun pandigitalProduct_1_9(n: Int) : Boolean = pandigitalProduct_1_9(n, 1)

tailrec fun pandigitalProduct_1_9(n: Int, index : Int): Boolean =
    if(index * index > n) false
    else {
        if(n % index == 0 && isPandigital("" + n + index + n / index)) true
        else {
            pandigitalProduct_1_9(n, index + 1)
        }
    }


//Проверка, что числа образуют "123456789"
fun isPandigital(str: String): Boolean {
    if (str.length != 9) return false
    val ch = str.toCharArray()
    Arrays.sort(ch)
    return String(ch) == "123456789"
}

//-------------------------------------------------------


/*Задача 52 с ресурса
Условие: Найти наименьшее положительное число х,такое,что 2х,3х, 4х, 5х и 6х  содержат те же цифры.
 */
//Проверка на то что цифра есть в числе
tailrec fun equalsDigit(num : Int, digit : Int) : Boolean =
    if(num <= 0) false
    else {
        if(num % 10 == digit) true
        else {
            equalsDigit(num / 10, digit)
        }
    }

fun dop52(digit : Int, multiplier : Int) : Boolean =
    if((multiplier*digit).toString().length!=digit.toString().length)  false //Проверка что разряды совпадают
    else {
        dopEquals(digit, multiplier*digit)
    }

tailrec fun dopEquals(digit : Int, newDigit : Int) : Boolean =
    if(newDigit <= 0) true
    else{
        if(!equalsDigit(digit,newDigit % 10)) false
        else {
            dopEquals(digit, newDigit / 10)
        }
    }



fun task52() : Int = task52(1)

tailrec fun task52(iter : Int): Int =
    if(dop52(iter, 2) && dop52(iter, 3) && dop52(iter, 4) && dop52(iter, 5) && dop52(iter, 6)) iter
    else {
        task52(iter + 1)
    }

//-----------------------------------------------
/*
Если n<d и NOD(n,d)=1, это называется уменьшенной надлежащей фракцией.
Если мы перечислим набор уменьшенных надлежащих фракций для d ≤ 8 в порядке возрастания размера, мы получаем:
1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
Видно, что в этом наборе 21 элемент.
Сколько элементов будет содержаться в наборе сокращенных надлежащих фракций для d ≤ 1 000 000?
 */
//Воспользуемся первым методом
fun task72() : Int = task72(2 , 0)

tailrec fun task72(iter : Int, count : Int) : Int =
    if(iter > 1000000) count
    else {
        println(iter)  
        task72(iter + 1, count + firstMetod(iter))
    }

fun main() {
    //val scanner = Scanner(`in`)
    //val s:Int = scanner.nextInt()
    println("Метод  : ${task72()}")
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

