/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

import java.sql.Timestamp;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;

/**
 *
 * @author _Nprime496_
 */
public class Appointment extends AppointmentImplLocal {
    
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;

    String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Timestamp getmyStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getmyEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }


    
}
