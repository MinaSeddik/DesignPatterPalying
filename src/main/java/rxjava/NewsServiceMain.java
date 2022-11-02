package rxjava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NewsServiceMain {

    public static void main(String[] args) {

        List<NewsLetter> list = IntStream.range(1, 10)
                .boxed()
                .map(NewsLetter::new)
                .collect(Collectors.toList());

        NewsServicePublisher newsService = new NewsServicePublisher(list);

        NewsServiceSubscriber subscriber = new NewsServiceSubscriber(5);

        newsService.subscribe(subscriber);

        subscriber.eventuallyReadDigest();
    }
}
