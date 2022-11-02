package rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

class NewsServiceSubscriber implements Subscriber<NewsLetter> {
    private Queue<NewsLetter> mailbox = new ConcurrentLinkedQueue<>();
    private int take;
    private AtomicInteger remaining = new AtomicInteger();
    private Subscription subscription;

    public NewsServiceSubscriber(int take) {
        this.take = take;
    }

    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("NewsServiceSubscriber#subscription.request(take)");
        subscription = s;
        subscription.request(take);

    }

    @Override
    public void onNext(NewsLetter newsLetter) {
        System.out.println("NewsServiceSubscriber#mailbox.offer(newsLetter): " + newsLetter);
        mailbox.offer(newsLetter);
    }

    @Override
    public void onError(Throwable t) {
    }

    @Override
    public void onComplete() {
    }

    public Optional<NewsLetter> eventuallyReadDigest() {
        NewsLetter letter = mailbox.poll();

        if (letter != null) {
            if (remaining.decrementAndGet() == 0) {
                subscription.request(take);
                remaining.set(take);
            }

            return Optional.of(letter);
        }

        return Optional.empty();
    }

}