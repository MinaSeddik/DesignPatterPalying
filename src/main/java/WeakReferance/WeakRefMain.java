package WeakReferance;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakRefMain {


    public static void main(String[] args) {

    }

    Object bigObject;
    public void dispose() {
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        WeakReference<Object> ref = new WeakReference<Object>(bigObject, queue);
        bigObject = null;
        ref.enqueue();
    }
}
