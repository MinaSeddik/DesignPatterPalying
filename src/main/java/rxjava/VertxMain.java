package rxjava;

import io.reactivex.Flowable;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;
import io.vertx.reactivex.core.http.HttpClient;

public class VertxMain {

    public static void main(String[] args) {

        Vertx vertx = io.vertx.reactivex.core.Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        HttpClient httpClient = vertx.createHttpClient();

//        fileSystem.rxReadFile("cities.txt")
//                .toFlowable()
//                .flatMap(buffer -> Flowable.fromArray(buffer.toString()
//                        .split("\\r?\\n")))
//                .flatMap(city -> searchByCityName(httpClient, city))
//                .flatMap(HttpClientResponse::toFlowable)
//                .map(extractingWoeid())
//                .flatMap(cityId -> getDataByPlaceId(httpClient, cityId))
//                .flatMap(toBufferFlowable())
//                .map(Buffer::toJsonObject)
//                .map(toCityAndDayLength())
//                .subscribe(System.out::println, Throwable::printStackTrace);

    }
}
