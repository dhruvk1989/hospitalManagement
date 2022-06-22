package com.idhit.hms.idhithealthclinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentRequestPayload {

    private String name;
    private Integer age;
    private String gender;
    private String symptoms;

}
