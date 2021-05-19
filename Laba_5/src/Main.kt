fun main() {
    val passTS = TransportVehicle("XTZ131HA00D024175","ЗИЛ 131-HA",
        "БОРТОВОЙ ГРУЗОВОЙ",1991,"1012605003256263613", "Павленко Лилия Станисловна")
    val passTS1 = TransportVehicle("XTZ131HA00D024175","ЗИЛ 131-HA",
        "БОРТОВОЙ ГРУЗОВОЙ",1991,"1012615003256263613", "Павленко Лилия Станисловна")
    if(passTS<passTS1)
        passTS.writePas()
    else
        passTS1.writePas()
}