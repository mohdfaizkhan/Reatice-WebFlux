package com.mohdfai.reactiverouterFucation.routerFucationExample.resource;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class EmpolyeeEvent
{
    public Empolyee employee;
    private Date date;

    public EmpolyeeEvent(Empolyee employee, Date date) {
        this.employee = employee;
        this.date = date;
    }
}
