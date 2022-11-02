package rxjava;

public class Temperature {

    private double t;
    public Temperature(double v) {
        t=v;
    }

    @Override
    public String toString(){
        return ((Number)t).toString();
    }
}
