package com.idhit.hms.idhithealthclinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionRequestPayload {

    private String medicines;
    private String doctorRemarks;

}
