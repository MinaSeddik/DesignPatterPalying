package nullhandling;

import java.util.Objects;
import java.util.Optional;

public class NullMain {

    public void main(String[] args) {

        // 1
        Bar bar = null;
        Foo foo = new Foo(bar);


        // 2
        method(null);

        // 3 factory doesn't find a valid class
        String animalType = "Dog";
        Animal animal;
        if (animalType.equalsIgnoreCase("Dog")) {
            animal = new Dog();
        }else{
            animal = new NullAnimal();
        }

        // finally call action on animal
        animal.makeSound();


//        avoid checking for null values in method chaining

        // DON'T DO THAT
//        if(country != null && country.getCity() != null && country.getCity().getSchool() != null && country.getCity().getSchool().getStudent() != null .....) {
//            isValid = true;
//        }

        // and

        // DON'T DO THAT
//        try{
//            if(country.getCity().getSchool().getStudent().getInfo().... != null)
//        } catch(NullPointerException ex){
//            //dont do anything.
//        }

        // Good
//        boolean isValid = Optional.ofNullable(country)
//                .map(Country::getCity)
//                .map(City::getSchool)
//                .map(School::getStudent)
//                .isPresent();

    }

    public static void method(Object object) {

        // Objects.isNull(object))
        // Objects.nonNull(object)
        // Objects.requireNonNull(object)   will raise exception
        // Objects.requireNonNull(object, "error message ...)   will raise exception with error message


        if (object == null) {
            throw new IllegalArgumentException("...");
        }

        // or

        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("...");
        }
    }


    public interface Animal {
        void makeSound() ;
    }

    public class Dog implements Animal {
        public void makeSound() {
            System.out.println("woof!");
        }
    }

    public class NullAnimal implements Animal {
        public void makeSound() {
            // silence...
            // or
            // raise exception
            // depends on the use case
        }
    }

}
