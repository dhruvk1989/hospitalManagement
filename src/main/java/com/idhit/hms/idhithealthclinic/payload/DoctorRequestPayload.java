package com.idhit.hms.idhithealthclinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorRequestPayload {

    private String name;
    private String age;
    private String dept;

}
