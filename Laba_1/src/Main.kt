import java.lang.System.`in`
import java.util.*

//Функции ответа на определенный язык
fun programLanguage(language:String){
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
fun String.reverseCaseOfString(): String {

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
fun sumDigits(num:Int):Int{
    var number = num
    var sum = 0
    while(number!=0){
        sum+=number%10
        number/=10
    }

    return sum
}
//Произведение цифр в числе
fun proDigits(num:Int):Int{
    var number = num
    var pro = 1
    while(number!=0){
        pro*=number%10
        number/=10
    }

    return pro
}
//Минимальный элемент в числе
fun minDigits(num:Int):Int{
    var number = num/10
    var min = num%10
    while(number!=0){
        if(number%10<min)
            min=number%10
        number/=10
    }
    return min
}
//Максимальный элемент в числе
fun maxDigits(num:Int):Int{
    var number = num/10
    var max = num%10
    while(number!=0){
        if(number%10>max)
            max=number%10
        number/=10
    }
    return max
}

//Метод 2. Найти сумму цифр числа, делящихся на 3.
fun sumDigitsShared3(num:Int):Int{
    var number = num
    var sum = 0
    while(number!=0){
        if(number%10%3==0)
        sum+=number%10
        number/=10
    }

    return sum
}

fun main() {
    val scanner = Scanner(System.`in`)
    val s:Int = scanner.nextInt()

    println("Сумма цифр числа : $s")
}

/*
fun main(args: Array<String>) {
    val name:String = args[0].reverseCaseOfString()
    println("Привет ${name}!\nКакой у тебя любимый язык программирования?")
    val scanner = Scanner(`in`)
    val s:String = scanner.next()
    programLanguage(s)
}
*/