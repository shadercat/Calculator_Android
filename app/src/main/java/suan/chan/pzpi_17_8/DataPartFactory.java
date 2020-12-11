package suan.chan.pzpi_17_8;

public class DataPartFactory {
    public static DataPart GetNumberPart(String number){
        return new DataPart(number, DataType.Number, false);
    }

    public static DataPart GetOperationPart(String operation){
        return new DataPart(operation, DataType.Operation, false);
    }
}

