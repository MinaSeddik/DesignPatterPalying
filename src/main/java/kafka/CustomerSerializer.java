//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kafka;

import java.nio.ByteBuffer;
import java.util.Map;
import kafka.model.Client;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class CustomerSerializer implements Serializer<Client> {
    public CustomerSerializer() {
    }

    public void configure(Map configs, boolean isKey) {
    }

    public byte[] serialize(String topic, Client customer) {
        try {
            if (customer == null) {
                return null;
            } else {
                byte[] serializedName;
                int stringSize;
                if (customer.getName() != null) {
                    serializedName = customer.getName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }

                ByteBuffer buffer = ByteBuffer.allocate(8 + stringSize);
                buffer.putInt(customer.getID());
                buffer.putInt(stringSize);
                buffer.put(serializedName);
                return buffer.array();
            }
        } catch (Exception var6) {
            throw new SerializationException("Error when serializing Customer to byte[] " + var6);
        }
    }

    public void close() {
    }
}
