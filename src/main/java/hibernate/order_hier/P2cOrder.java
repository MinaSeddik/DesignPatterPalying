package hibernate.order_hier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value=OrderType.Values.P2C)
public class P2cOrder extends Order {
}
