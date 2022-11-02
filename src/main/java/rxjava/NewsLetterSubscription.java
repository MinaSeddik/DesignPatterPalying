package rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewsLetterSubscription implements Subscription {

    private final Subscriber<? super NewsLetter> subscriber;
    private final Iterator<NewsLetter> iterator;
    private final AtomicBoolean isTerminated = new AtomicBoolean(false);

    public NewsLetterSubscription(Subscriber<? super NewsLetter> subscriber, List<NewsLetter> list) {
        this.subscriber = subscriber;
        this.iterator = list.iterator();
    }

    @Override
    public void request(long n) {

        if (n <= 0 && !terminate()) {
            subscriber.onError(new IllegalArgumentException("negative subscription request"));
            return;
        }

        while (n > 0 && iterator.hasNext()) {
            subscriber.onNext(iterator.next());
            n--;
        }

        if (!iterator.hasNext())
            subscriber.onComplete();
    }

    @Override
    public void cancel() {
        terminate();
    }

    private boolean terminate() {
        return isTerminated.getAndSet(true);
    }

    private boolean isTerminated() {
        return isTerminated.get();
    }


}
