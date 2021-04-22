import java.lang.System.`in`
import java.util.*

//Функции ответа на определенный язык
fun programLanguage(language:String){
    when(language.reverseCaseOfString()){ //Сделали вид по умолчанию
        "Kotlin","Prolog"->println("Ну ты и подлиза")

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

fun main(args: Array<String>) {
    val name:String = args[0].reverseCaseOfString()
    println("Привет ${name}!\nКакой у тебя любимый язык программирования?")
    val scanner = Scanner(`in`)
    val s:String = scanner.next()
    programLanguage(s)
}