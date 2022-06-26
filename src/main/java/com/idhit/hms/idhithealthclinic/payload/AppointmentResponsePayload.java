package com.idhit.hms.idhithealthclinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentResponsePayload {
    private String doctorName;
    private Long appointmentNumber;
}
