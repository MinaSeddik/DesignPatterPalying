package spel;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CarPark {

    private List<Car> cars = new ArrayList<>();
}
