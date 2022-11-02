package nullhandling;

import java.util.Objects;

public class Foo {

    private final Bar bar;

    public Foo(Bar bar) {
//        this.bar = Objects.requireNonNull(bar);

        // OR

        this.bar = Objects.requireNonNull(bar, "Bar should NOT be Null");
    }

}
