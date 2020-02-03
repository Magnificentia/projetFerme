package app.modules.views.taches;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Appointment;

import app.modules.userType;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.LocalTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
//putain
public class TaskViewController implements Initializable, IController {


    @FXML
    private Agenda agenda;
    @FXML
    private CalendarPicker calendar;
    @FXML
    private LocalTimeTextField startTime;
    @FXML
    private LocalTimeTextField endTime;
    @FXML
    private TextArea description;

    private Appointment selectedAppointment;


    @FXML
    void addAppointment(ActionEvent event) {


        int id;

        System.out.println("calendar "+calendar.getCalendar());
        Date selected = calendar.getCalendar().getTime();
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Agenda.AppointmentImplLocal newAppointment = new Appointment()
                .withStartLocalDateTime(startTime.getLocalTime().atDate(date))
                .withEndLocalDateTime(endTime.getLocalTime().atDate(date))
                .withDescription(description.getText())
                .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));

        id = DbManager.addNewAppointment(newAppointment);

        Appointment a = (Appointment)newAppointment;
        a.setId(id);

        agenda.appointments().add(a);
        agenda.refresh();

        updateAppointment();

    }

    @FXML
    void deleteAppointment(ActionEvent event) {

        if(selectedAppointment!=null)
        {
            System.out.println(selectedAppointment.getId());
            DbManager.deleteAppointment(selectedAppointment.getId());
            updateAgenda();
            agenda.refresh();
        }
    }

    @FXML
    void updateAppointment(ActionEvent event) {
        Date selected;
        if( calendar.getCalendar()==null){
            selected = Date.from(selectedAppointment.getStartLocalDateTime().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        }else {
            selected = calendar.getCalendar().getTime();
        }
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        selectedAppointment.setStartLocalDateTime(startTime.getLocalTime().atDate(date));
        selectedAppointment.setEndLocalDateTime(endTime.getLocalTime().atDate(date));
        selectedAppointment.setDescription(description.getText());
        DbManager.updateAppointment(selectedAppointment);
        updateAgenda();
        agenda.refresh();



    }


    private void updateAppointment() {

        agenda.localDateTimeRangeCallbackProperty().set(param -> {
           
            List<Appointment> list = DbManager.getAppointments(param.getStartLocalDateTime(), param.getEndLocalDateTime());
            System.out.println("after querying...");
            agenda.appointments().clear();
            agenda.appointments().addAll(list);
                    return null;
                }

        );


    }

    private void updateAgenda(){
        System.out.println("actually updating agenda...");
        agenda.localDateTimeRangeCallbackProperty().set(param -> {
           
            List<Appointment> list = DbManager.getAppointments(param.getStartLocalDateTime(), param.getEndLocalDateTime());
            System.out.println("after ");
            agenda.appointments().clear();
            agenda.appointments().addAll(list);
                    return null;
                }

        );


    }
    
    public void updateTasks()
    {
            System.out.println("click event on calendar");
            System.out.println(calendar.getCalendar());
            try
            {
            Date cal = calendar.getCalendar().getTime();
            LocalDate ld = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalTime lt = LocalTime.NOON;

            agenda.setDisplayedLocalDateTime(LocalDateTime.of(ld, lt));
                System.out.println("almost updating agenda....");
            updateAgenda();
                }
            catch(NullPointerException e)
                {
                    System.out.println("erreur  bizzare de null pointer sur le calendar");
                }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateTasks();
        try {
            //updateAgenda();
        }catch (Exception e){
            e.printStackTrace();

        }

        agenda.setAllowDragging(true);
        agenda.setAllowResize(true);
        agenda.newAppointmentCallbackProperty().set((localDateTimeRange) -> {
            Agenda.AppointmentImplLocal appointmentImplLocal = new Appointment()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));


            int id = DbManager.addNewAppointment(appointmentImplLocal);

            Appointment a = (Appointment)appointmentImplLocal;
            a.setId(id);

            return a;

        });


        calendar.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            updateTasks();
        });

        agenda.appointmentChangedCallbackProperty().set(param ->{


                    DbManager.updateAppointment((Appointment)param);
                    return null;
                }
        );

        agenda.actionCallbackProperty().set(param ->
                {
                    selectedAppointment = (Appointment)param;
                    startTime.setLocalTime(selectedAppointment.getStartLocalDateTime().toLocalTime());
                    endTime.setLocalTime(selectedAppointment.getEndLocalDateTime().toLocalTime());
                    description.setText(selectedAppointment.getDescription());
                    return null;
                }
        );
        
        agenda.localDateTimeRangeCallbackProperty().set(param -> {
           
            List<Appointment> list = DbManager.getAppointments(param.getStartLocalDateTime(), param.getEndLocalDateTime());
            System.out.println("after querying...");
            agenda.appointments().clear();
            agenda.appointments().addAll(list);
                    return null;
                }

        );

    }



    @Override
    public Map<Node,List<userType>> getNodeRoles() {
        Map nodeRoles=new HashMap<Node,List<userType>>();

        return nodeRoles;
    }
}
