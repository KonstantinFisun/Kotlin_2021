//Паспорт траспортного средства
class TransportVehicle(var vin : String, var markaModel : String, var type : String,
                       var yearOfManufacture : Int, var series : String,
                       var fio : String)
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



}