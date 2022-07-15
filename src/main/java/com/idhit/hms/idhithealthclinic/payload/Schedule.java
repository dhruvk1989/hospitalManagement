package com.idhit.hms.idhithealthclinic.payload;

import java.util.Date;

public interface Schedule {
    public Long getDocId();
    public Long getApptId();
    public String getDocName();
    public String getPatientName();
    public Date getApptDate();
    public Date getDocTime();
}
