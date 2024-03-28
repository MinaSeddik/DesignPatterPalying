//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kafka;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

public class PurchaseKeyPartitioner extends DefaultPartitioner {
    public PurchaseKeyPartitioner() {
    }

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Object newKey = null;
        if (key != null) {
            newKey = "some data used to partition";
            keyBytes = ((String)newKey).getBytes();
        }

        return super.partition(topic, newKey, keyBytes, value, valueBytes, cluster);
    }
}
