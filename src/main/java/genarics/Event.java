package genarics;



public class Event<T extends Comparable<T>> {
    protected String name;
    protected T event;

    public String getName() {
        return name;
    }

    public T getData() {

        System.out.println("*** " + event.getClass().getName());
        return event;
    }

    public Event(String name, T data) {
        this.name = name;
        event = data;
    }
}