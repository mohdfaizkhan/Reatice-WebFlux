package com.mohdfai.reactiverouterFucation.routerFucationExample.repository;


import com.mohdfai.reactiverouterFucation.routerFucationExample.resource.Empolyee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmpolyeeRepostiry extends ReactiveMongoRepository<Empolyee, String> {
}
