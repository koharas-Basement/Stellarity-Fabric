package xyz.kohara.stellarity.utils;

public class ModifiableArg<T> {
    private T arg;
    
    public ModifiableArg(T arg) {
        this.arg = arg;
    }
    
    public T getArg() {
        return arg;
    }
    
    public void setArg(T val) {
        this.arg = val;
    }
    
    @Override
    public String toString() {
        return "ModifiableArg<" + typeGetter(arg) + ">{" + arg + "}";
    }
    
    @SafeVarargs
    private static <T> Class<?> typeGetter(T... ts) {
        return ts.getClass().getComponentType();
    }
}