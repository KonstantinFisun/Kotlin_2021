//Паспорт траспортного средства
class TransportVehicle(var vin : String, var markaModel : String, var type : String,
                       var yearOfManufacture : Int, var series : String,
                       var fio : String)  : Comparable<TransportVehicle>
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


}