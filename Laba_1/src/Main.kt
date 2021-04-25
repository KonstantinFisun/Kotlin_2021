import java.lang.System.`in`
import java.util.*


//Функции ответа на определенный язык
fun programLanguage(language : String){
    when(language.reverseCaseOfString()){ //Сделали вид по умолчанию
        "Kotlin","Prolog" -> println("Ну ты и подлиза")
        "C" -> println("Это винтовка Мосина, старое, но надежное.")
        "C++" -> println("Это нунчаки,мощные и впечатляющие в действии, но требуют годы боли, чтобы овладеть ими мастерски.")
        "Perl" -> println("Это коктейль Молотова, однажды он был полезен, но теперь мало кто им пользуется.")
        "Java" -> println("Это пулемет 240G с ленточным заряжением.")
        "Scala" -> println("Это просто вариант 240G Java, но с мануалом, написанном на непонятном диалекте")
        "JavaScript" -> println("Это меч без рукоятки.")
        "Go" -> println("Это пистолет, после каждого выстрела которого нужно проверить, а правда ли он выстрелил.")
        "Rust" -> println("Это пистолет, напечатанный на 3D принтере. ")
        "C#" -> println("Это мощная лазерная пушка, примотанная к спине осла")
        "Lisp" -> println("Это заточка, которая может принимать разные формы.")
        else -> println("Ваще такого не знаю")
    }
}

//Функция форматирования строки
fun String.reverseCaseOfString() : String {

    val inputCharArr = toCharArray() // Конвертируем поступающую строку в массив символов
    var output = ""

    for (i in 0 until inputCharArr.size) { //until необходим что не выйти за пределы массива
        output += if (inputCharArr[i].isUpperCase()) { // Проверяем если символ находится в верхем регистре
            inputCharArr[i].toLowerCase() // то переводим его в нижний
        } else {
            inputCharArr[i]// иначе просто возвращаем
        }
    }
    return output.capitalize() //одновременно переводим в верхний регистр первый символ
}

//Сумма цифр в числе
fun sumDigits(num : Int) : Int{
    var number = num
    var sum = 0
    while(number != 0){
        sum += number % 10
        number /= 10
    }

    return sum
}
//Произведение цифр в числе
fun proDigits(num : Int) : Int{
    var number = num
    var pro = 1
    while(number != 0){
        pro *= number % 10
        number /= 10
    }

    return pro
}
//Минимальный элемент в числе
fun minDigits(num : Int) : Int{
    var number = num / 10
    var min = num % 10
    while(number != 0){
        if(number % 10 < min)
            min = number % 10
        number /= 10
    }
    return min
}
//Максимальный элемент в числе
fun maxDigits(num : Int) : Int{
    var number = num / 10
    var max = num % 10
    while(number != 0){
        if(number % 10 > max)
            max = number % 10
        number /= 10
    }
    return max
}
//Метод 1. Найти количество чисел, взаимно простых с заданным.
tailrec fun nod(a : Int, b : Int) : Int = if (b == 0) a else nod(b, a % b)  //НОД

fun firstMetod(num : Int) : Int{
    var kolvo=0
    for(i in 1..num)
        if(nod(num,i) == 1) kolvo++
    return kolvo
}


//Метод 2. Найти сумму цифр числа, делящихся на 3.
fun secondMetod(num : Int) : Int{
    var number = num
    var sum = 0
    while(number != 0){
        if(number % 10 % 3 == 0)
            sum += number % 10
        number /= 10
    }

    return sum
}

//Метод 3. Найти делитель числа, являющийся взаимно простым с
//наибольшим количеством цифр данного числа.

//количество взаимно простых с данным числом
fun countMutuallySimpleDigit(digit : Int, num : Int) : Int {
    var number : Int = num
    var kolvo : Int = 0
    while(number != 0){
        if(nod(number % 10, digit) == 1) kolvo++
        number /= 10
    }
    return kolvo
}

fun threeMetod(num : Int) : Int {
    var divisor = 0
    var maxMutuallySimple = 0
     for(i in 2 until num)
         if(num % i == 0)
             if(maxMutuallySimple<countMutuallySimpleDigit(i , num)){
                 divisor = i
                 maxMutuallySimple = countMutuallySimpleDigit(i , num)
             }

    return divisor

}


//Задача 12. Последовательность треугольных чисел создается путем сложения натуральных чисел.
// Таким образом, 7- й номер треугольника будет 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. Первые десять членов будут такими:
//1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
//Перечислим множители первых семи чисел треугольника:
// 1 : 1
// 3 : 1,3
// 6 : 1,2,3,6
//10 : 1,2,5,10
//15 : 1,3,5,15
//21 : 1,3,7,21
//28 : 1,2, 4,7,14,28
//Какое значение имеет первое число треугольника, которое имеет более пятисот делителей?
fun task12() : Int {
    var a: Int = 1
    var b: Int

    var triangle: Int = 0

    while (true) {
        triangle += a
        var count: Int = 0 //Количество делителей
        for (b in 1..triangle)
        {
            if (triangle % b == 0) {
                count += 1
            }
        }
        if (count > 500) {
            return triangle
        }
        a++
    }
}

//Задача 32. Дано a*b=c. Найти сумму всех таких c, что в результате объединения символов abc
// получим последовательность из 1..9 причем по одному разу
fun task32(): Int {
    var sum = 0
    for (i in 1..9999) {
        if (pandigitalProduct_1_9(i)) sum += i
    }
    return sum
}

//Определяем, что из заданного числа можно получить пандигитал
fun pandigitalProduct_1_9(n: Int): Boolean {
    var i = 1
    while (i * i <= n) {
        if (n % i == 0 && isPandigital("" + n + i + n / i)) {
            println("$i * ${n / i} = $n")
            return true
        }
        i++
    }
    return false
}

//Проверка, что числа образуют "123456789"
fun isPandigital(str: String): Boolean {
    if (str.length != 9) return false
    val ch = str.toCharArray()
    Arrays.sort(ch)
    return String(ch) == "123456789"
}

//Задача 52 с ресурса
//Условие: Найти наименьшее положительное число х,такое,что 2х,3х, 4х, 5х и 6х  содержат те же цифры.
//проверка на то что цифра есть в числе
fun equalsDigit(num : Int, digit : Int) : Boolean{
    var number : Int = num
    while(number != 0){
        if(number % 10 == digit)
        return true
        number /= 10
    }
    return false
}

fun dop52(digit : Int, multiplier : Int) : Boolean{
    var newDigit : Int = multiplier*digit //создали число 2х,3х, 4х, 5х и 6х

    //Проверка что разряды совпадают
    if(newDigit.toString().length==digit.toString().length) {
        while(newDigit != 0){
            if(!equalsDigit(digit,newDigit % 10)) return false
            newDigit /= 10
        }
    }
    else { return false }

    return true
}

fun task52(): Int{
    var iter : Int = 1
    while(true){
        if(dop52(iter, 2))
            if(dop52(iter, 3))
                if(dop52(iter, 4))
                    if(dop52(iter, 5))
                        if(dop52(iter, 6))
                            return iter
        iter++
    }
}

//Возможность выбора метода
fun selectUser(){
    println("Добрый день!")
    val scanner = Scanner(`in`)

    do{
        println("Выберите операцию : \n" +
                "0. Выход.\n" +
                "1. Сумма цифр в числе.\n" +
                "2. Произведение цифр в числе.\n" +
                "3. Максимальный элемент. \n" +
                "4. Минимальный элемент. \n" +
                "5. Первый метод. \n" +
                "6. Второй метод. \n" +
                "7. Третий метод. \n" +
                "8. 52 задача. \n" +
                "9. 32 задача. \n")
        val select : Int = scanner.nextInt()
        if(select == 0) { return }
        var digit : Int = 0
        if(select in 1..7) {
            println("Введите число : ")
            digit = scanner.nextInt()
        }
        when(select){
            1 -> {
                println(sumDigits(digit))
            }
            2 -> {
                println(proDigits(digit))
            }
            3 -> {
                println(maxDigits(digit))
            }
            4 -> {
                println(minDigits(digit))
            }
            5 -> {
                println(firstMetod(digit))
            }
            6 -> {
                println(secondMetod(digit))
            }
            7 -> {
                println(threeMetod(digit))
            }
            8 -> {
                println("52 задача : ${task52()}")
            }
            9 -> {
                println("32 задача :${task32()}")
            }
            10 -> {
                println("12 задача :${task12()}")
            }

        }
        println("Введите любой символ для продолжения... : ")
        val ok : String = scanner.next()

    }while(select != 0)
}

//Главная функция
fun main() {
    selectUser()
}

/* Для работы с командной строкой
fun main(args: Array<String>) {
    val name : String = args[0].reverseCaseOfString()
    println("Привет ${name}!\nКакой у тебя любимый язык программирования?")
    val scanner = Scanner(`in`)
    val s : String = scanner.next()
    programLanguage(s)
}
*/