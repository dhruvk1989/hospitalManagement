package com.idhit.hms.idhithealthclinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface DoctorAppointments extends Comparable {

    Integer getAppointmentCount();
    Integer getDoctorId();

    @Override
    public default int compareTo(Object o) {
        return getAppointmentCount().compareTo(((DoctorAppointments) o).getAppointmentCount());
    }
}
