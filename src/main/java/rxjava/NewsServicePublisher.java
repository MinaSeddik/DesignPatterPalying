package rxjava;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.List;

public class NewsServicePublisher implements Publisher<NewsLetter> {

    private List<NewsLetter> list;

    public NewsServicePublisher(List<NewsLetter> list) {
        this.list = list;
    }

    @Override
    public void subscribe(Subscriber<? super NewsLetter> subscriber) {

        NewsLetterSubscription subscription = new NewsLetterSubscription(subscriber, list);
        subscriber.onSubscribe(subscription);
//        subscription.doOnSubscribed();

    }

}
