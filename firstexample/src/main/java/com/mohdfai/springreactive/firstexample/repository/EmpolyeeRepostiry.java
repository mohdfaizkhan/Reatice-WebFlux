package com.mohdfai.springreactive.firstexample.repository;

import com.mohdfai.springreactive.firstexample.resource.Empolyee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmpolyeeRepostiry extends ReactiveMongoRepository<Empolyee, String> {
}
