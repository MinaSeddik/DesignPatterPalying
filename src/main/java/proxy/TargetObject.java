package proxy;

public class TargetObject implements SimpleInterface {


    @Override
    public int originalMethod(String string) {
        System.out.println(string);

        return 55;
    }


}
