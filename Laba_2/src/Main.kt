import java.lang.System.`in`
import java.util.*

//Суммы элементов рекурсия вверх
fun sumElemUp(num:Int):Int = if(num==0) 0 else (num%10)+sumElemUp(num/10)


fun main() {


    val scanner = Scanner(`in`)
    val s:Int = scanner.nextInt()

    println("Сумма элементов рекурсия вверх ${sumElemUp(s)}")

}

