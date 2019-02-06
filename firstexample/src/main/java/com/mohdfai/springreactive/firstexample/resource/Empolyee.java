package com.mohdfai.springreactive.firstexample.resource;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Empolyee
{

    @Id
    private String id;
    private String name;
    private Long salary;

    public Empolyee(String id, String name, Long salary) {

        this.id = id;
        this.name = name;
        this.salary= salary;

    }
}
