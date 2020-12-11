package suan.chan.pzpi_17_8;

public class DataPart {
    private String data;
    private DataType type;
    private boolean floatNum;

    public DataPart(){

    }

    public DataPart(String data, DataType type, boolean floatNum){

        this.data = data;
        this.type = type;
        this.floatNum = floatNum;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isFloatNum() {
        return floatNum;
    }

    public void setFloatNum(boolean floatNum) {
        this.floatNum = floatNum;
    }
}
