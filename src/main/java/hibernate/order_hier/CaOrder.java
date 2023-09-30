package hibernate.order_hier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value=OrderType.Values.CA)
public class CaOrder  extends Order{
}
