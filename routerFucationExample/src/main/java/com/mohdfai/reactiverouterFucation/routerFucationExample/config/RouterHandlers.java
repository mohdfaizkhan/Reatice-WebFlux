package com.mohdfai.reactiverouterFucation.routerFucationExample.config;

import com.mohdfai.reactiverouterFucation.routerFucationExample.repository.EmpolyeeRepostiry;
import com.mohdfai.reactiverouterFucation.routerFucationExample.resource.Empolyee;
import com.mohdfai.reactiverouterFucation.routerFucationExample.resource.EmpolyeeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Component
public class RouterHandlers
{
    @Autowired
    private EmpolyeeRepostiry empolyeeRepostiry;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {

        return ServerResponse.ok().body(empolyeeRepostiry.findAll(), Empolyee.class);
    }

    public Mono<ServerResponse> getId(ServerRequest serverRequest) {

        return ServerResponse.ok().body(empolyeeRepostiry.findById(
                serverRequest.pathVariable("id")),Empolyee.class);
    }

    public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
                .body(
                empolyeeRepostiry.findById(serverRequest.pathVariable("id"))
                        .flatMapMany(
                                empolyee ->
                                {
                                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));

                                    Flux<EmpolyeeEvent>  empolyeeEventFlux =
                                            Flux.fromStream(Stream.generate( ()->
                                                    new EmpolyeeEvent(empolyee,
                                                            new Date()))
                                            );
                                    return Flux.zip(interval,empolyeeEventFlux)
                                            .map(objects ->
                                            {
                                                return objects.getT2();
                                            });
                                }


                        ),EmpolyeeEvent.class


        );
    }
}
