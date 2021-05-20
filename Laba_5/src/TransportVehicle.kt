//Паспорт траспортного средства
class TransportVehicle(var vin : String = "", var markaModel : String = "", var type : String = "",
                       var yearOfManufacture : Int = 0, var series : String,
                       var fio : String = "")  : Comparable<TransportVehicle>
{
    //Функция вывода на экран документа
    fun writePas(){
        println("Паспорт транспортного средства.\n" +
                "VIN : $vin\n" +
                "Марка, модель ТС : $markaModel\n" +
                "Тип ТС : $type\n" +
                "Год изготовления ТС : $yearOfManufacture\n" +
                "Серия ТС : $series\n" +
                "ФИО владельца ТС : $fio\n"
        )
    }

    //Переопределение операции сравнения
    override fun equals(other: Any?): Boolean {
        return if (other is TransportVehicle) {
            series == other.series
        } else
            false
    }

    override fun compareTo(other: TransportVehicle ): Int {
        return compareValuesBy(this , other ,
            TransportVehicle :: series, TransportVehicle :: vin) //Сначала сравниваем по серии, потом по вин
    }

    override fun hashCode(): Int {
        var result = vin.hashCode()
        result = 31 * result + markaModel.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + yearOfManufacture
        result = 31 * result + series.hashCode()
        result = 31 * result + fio.hashCode()
        return result
    }

    //VIN - 17 букв или цифр, где последнии 4 являются цифрами
    //Марка модель - цифра в конце
    //Тип - без цифр
    //Год выпуска - 4 цифры
    //Серия - только цифр
    //Фамилия Имя Отчество - 3 слова без цифр

    //Функция проверки правильности ввода данных(валидация)
    fun validation(){
        checkVIN()
        checkmarkaModel()
        checkType()
        checkSeries()
        checkYearOfManufacture()
        checkFIO()
    }

    //Проверка vin
    fun checkVIN() : Boolean{
        if(vin.length != 17) {
            println("Количество букв не совпадает в vin : ${vin.length}")
            return false
        }
        val matchResult = Regex("""(\w{13})([0-9]{4})""").find(vin)
        if(matchResult == null) {
            println("Введен неправильный вин")
            return false
        }
        return true
    }

    fun checkmarkaModel() : Boolean{
        if(markaModel.matches(Regex("""[а-яА-Яa-zA-Z\s0-9\-]{6,25}"""))) {
            return true
        }
        println("Введена неправильная марка и модель авто")
        return false
    }

    fun checkSeries() : Boolean{
        if(series.matches(Regex("""[0-9]+"""))) {
            return true
        }
        println("Введена неправильная серия")
        return false
    }

    fun checkYearOfManufacture() : Boolean{
        if(yearOfManufacture>1900 && yearOfManufacture<2021){
            return true
        }
        println("Введён некорректный год выпуска")
        return false
    }

    fun checkFIO() : Boolean{
        if(fio.matches(Regex("""\s?([А-ЯA-Z])[а-яa-z]+\s+([А-ЯA-Z])[а-яa-z]+\s+([А-ЯA-Z])[а-яa-z]+\s?"""))) {
            return true
        }
        println("Введена неправильная ФИО")
        return false
    }

    fun checkType() : Boolean{
        if(type.matches(Regex("""[а-яА-Яa-zA-Z\s]+"""))) {
            return true
        }
        println("Введён неправильный тип ТС")
        return false
    }


}