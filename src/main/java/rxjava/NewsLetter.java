package rxjava;

public class NewsLetter {

    private final int id;

    public NewsLetter(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return ((Number)id).toString();
    }
}
