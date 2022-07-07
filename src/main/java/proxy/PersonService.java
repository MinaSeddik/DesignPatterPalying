package proxy;

public class PersonService implements Comparable<PersonService> {
    public String sayHello(String name) {
        return "Hello " + name;
    }

    public Integer lengthOfName(String name) {
        return name.length();
    }

    @Override
    public String toString(){
        return "$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$";
    }

    @Override
    public int compareTo(PersonService o) {
        return 0;
    }
}

