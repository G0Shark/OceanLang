public class VariableClass {
    public enum VariableType{
        VARIABLE_TYPE_INT,
        VARIABLE_TYPE_STRING,
        VARIABLE_TYPE_UNKNOWN
    }
    public VariableType type;
    public String variable;
    public VariableClass(VariableType type, String variable){
        this.type = type;
        this.variable = variable;
    }
}
