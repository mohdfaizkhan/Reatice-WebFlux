package com.mohdfai.springreactive.firstexample.resource;


import com.mohdfai.springreactive.firstexample.repository.EmpolyeeRepostiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.jvm.hotspot.utilities.Interval;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource
{
   @Autowired
    private EmpolyeeRepostiry empolyeeRepostiry;

    @GetMapping("/all")
    public Flux<Empolyee> getAll()
    {
        return empolyeeRepostiry.findAll();

    }

    @GetMapping("/{id}")
    public Mono<Empolyee> getEmplyoee(@PathVariable("id") final String empId)
    {

        return empolyeeRepostiry.findById(empId);
    }

    @GetMapping(value = "/{id}/events" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmpolyeeEvent> getEmplyoeeEvent(@PathVariable("id") final String empId)
    {

        return empolyeeRepostiry.findById(empId)
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


                              );
    }
}
