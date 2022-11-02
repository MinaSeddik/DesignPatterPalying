package reactor;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.DisposableChannel;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


public class StandaloneApplication {


    public static void main(String[] args) throws IOException {

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(18);
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routes(bCryptPasswordEncoder));

        ReactorHttpHandlerAdapter reactorHttpHandler = new ReactorHttpHandlerAdapter(httpHandler);


        HttpServer.create()
                .port(8080)
                .handle(reactorHttpHandler)
                .bind()
                .flatMap(DisposableChannel::onDispose)
                .block();
    } //

    static private RouterFunction<ServerResponse> routes(PasswordEncoder passwordEncoder) {
        return route(POST("/check"), // (5)
                request -> request //
                        .bodyToMono(PasswordDTO.class) // (5.1)
                        .map(p -> passwordEncoder.matches(p.getRaw(), p.getSecured())) // (5.2)
                        .flatMap(isMatched -> isMatched ? ServerResponse //
                                .ok() //
                                .build() //
                                : ServerResponse //
                                .status(HttpStatus.EXPECTATION_FAILED) //
                                .build() //
                        ) //
        ); //
    }
}