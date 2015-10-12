package jfxtras.labs.samples.repeatagenda;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Agenda;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Agenda.Appointment;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Agenda.AppointmentGroup;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.AppointmentFactory;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Repeat;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Repeat.EndCriteria;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Repeat.IntervalUnit;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.Repeat.MonthlyRepeat;
import jfxtras.labs.samples.repeatagenda.scene.control.agenda.RepeatFactory;

public abstract class RepeatTestAbstract {

//    private static ObservableList<AppointmentGroup> appointmentGroups = Agenda.DEFAULT_APPOINTMENT_GROUPS;

    public final static ObservableList<AppointmentGroup> DEFAULT_APPOINTMENT_GROUPS
    = javafx.collections.FXCollections.observableArrayList(
            IntStream
            .range(0, 23)
            .mapToObj(i -> new Agenda.AppointmentGroupImpl()
                   .withStyleClass("group" + i)
                   .withKey(i)
                   .withDescription("group" + (i < 10 ? "0" : "") + i))
            .collect(Collectors.toList()));
    ObservableList<AppointmentGroup> appointmentGroups = DEFAULT_APPOINTMENT_GROUPS;
    
    public Repeat getRepeatWeekly()
    {
        Appointment a1 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(5))
                .withSummary("Weekly Appointment Variable");
        return new MyRepeat()
                .withStartLocalDate(LocalDate.now())
                .withStartLocalTime(LocalTime.now().plusHours(3))
                .withEndLocalTime(LocalTime.now().plusHours(5))
                .withEndCriteria(EndCriteria.NEVER)
                .withIntervalUnit(IntervalUnit.WEEKLY)
                .withDayOfWeek(LocalDate.now().getDayOfWeek(), true)
                .withDayOfWeek(LocalDate.now().plusDays(2).getDayOfWeek(), true)
                .withAppointmentData(a1);
    }

    public Repeat getRepeatMonthly()
    {
        Appointment a2 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(9))
                .withSummary("Monthly Appointment Variable");
        return new MyRepeat()
                .withStartLocalDate(LocalDate.now().minusDays(1))
                .withStartLocalTime(LocalTime.now().minusHours(5))
                .withEndLocalTime(LocalTime.now().minusHours(3))
                .withEndCriteria(EndCriteria.ON)
                .withEndOnDate(LocalDate.now().minusDays(1).plusMonths(3))
                .withIntervalUnit(IntervalUnit.MONTHLY)
                .withMonthlyRepeat(MonthlyRepeat.DAY_OF_MONTH)
                .withAppointmentData(a2);
    }
    
    public Repeat getRepeatDaily()
    {
        Appointment a3 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(15))
                .withSummary("Daily Appointment Variable");
        return new MyRepeat()
                .withStartLocalDate(LocalDate.now().minusDays(2))
                .withStartLocalTime(LocalTime.now().plusHours(4))
                .withEndLocalTime(LocalTime.now().plusHours(7))
                .withEndCriteria(EndCriteria.AFTER)
                .withIntervalUnit(IntervalUnit.DAILY)
                .withRepeatFrequency(2)
                .withEndAfterEvents(5)
                .withAppointmentData(a3);
    }

    public Repeat getRepeatDailyFixed()
    {
        Appointment a = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(15))
                .withSummary("Daily Appointment Fixed");
//                .withStartLocalDateTime(LocalDate.of(2015, 10, 7).atTime(8, 45))
//                .withEndLocalDateTime(LocalDate.of(2015, 10, 7).atTime(10, 15));
        return RepeatFactory.newRepeat()
                .withStartLocalDate(LocalDate.of(2015, 10, 7))
                .withStartLocalTime(LocalTime.of(8, 45))
                .withEndLocalTime(LocalTime.of(10, 15))
                .withIntervalUnit(IntervalUnit.DAILY)
                .withRepeatFrequency(3)
                .withEndCriteria(EndCriteria.AFTER)
                .withEndAfterEvents(11)
                .withAppointmentData(a);
    }
    
    public Repeat getRepeatWeeklyFixed()
    {
        Appointment a1 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(3))
                .withSummary("Weekly Appointment Fixed");
        return RepeatFactory.newRepeat()
                .withStartLocalDate(LocalDate.of(2015, 10, 7))
                .withStartLocalTime(LocalTime.of(18, 0))
                .withEndLocalTime(LocalTime.of(18, 45))
                .withEndCriteria(EndCriteria.NEVER)
                .withIntervalUnit(IntervalUnit.WEEKLY)
                .withDayOfWeek(DayOfWeek.WEDNESDAY, true)
                .withDayOfWeek(DayOfWeek.FRIDAY, true)
                .withAppointmentData(a1);
    }
    
    public Repeat getRepeatWeeklyFixed2()
    {
        Appointment a1 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(3))
                .withSummary("Weekly Appointment Fixed2");
        return RepeatFactory.newRepeat()
                .withStartLocalDate(LocalDate.of(2015, 10, 5))
                .withStartLocalTime(LocalTime.of(8, 45))
                .withEndLocalTime(LocalTime.of(10, 15))
                .withIntervalUnit(IntervalUnit.WEEKLY)
                .withDayOfWeek(DayOfWeek.MONDAY, true)
                .withDayOfWeek(DayOfWeek.WEDNESDAY, true)
                .withDayOfWeek(DayOfWeek.FRIDAY, true)
                .withRepeatFrequency(2)
                .withEndCriteria(EndCriteria.AFTER)
                .withEndAfterEvents(50)
                .withAppointmentData(a1);
    }
    
    public Repeat getRepeatMonthlyFixed()
    {
        Appointment a2 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(9))
                .withSummary("Monthly Appointment Fixed");
        return RepeatFactory.newRepeat()
                .withStartLocalDate(LocalDate.of(2015, 10, 7))
                .withStartLocalTime(LocalTime.of(8, 45))
                .withEndLocalTime(LocalTime.of(10, 15))
                .withEndCriteria(EndCriteria.ON)
                .withEndOnDate(LocalDate.of(2016, 10, 7))
                .withIntervalUnit(IntervalUnit.MONTHLY)
                .withMonthlyRepeat(MonthlyRepeat.DAY_OF_MONTH)
                .withAppointmentData(a2);
    }

    public Repeat getRepeatMonthlyFixed2()
    {
        Appointment a2 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(9))
                .withSummary("Monthly Appointment Fixed2");
        return RepeatFactory.newRepeat()
                .withStartLocalDate(LocalDate.of(2015, 10, 15))
                .withStartLocalTime(LocalTime.of(8, 45))
                .withEndLocalTime(LocalTime.of(10, 15))
                .withEndCriteria(EndCriteria.ON)
                .withEndOnDate(LocalDate.of(2016, 10, 20))
                .withIntervalUnit(IntervalUnit.MONTHLY)
                .withMonthlyRepeat(MonthlyRepeat.DAY_OF_WEEK)
                .withAppointmentData(a2);
    }

    public Repeat getRepeatYearlyFixed()
    {
        Appointment a2 = AppointmentFactory.newAppointment()
                .withAppointmentGroup(appointmentGroups.get(22))
                .withSummary("Yearly Appointment Fixed");
        return RepeatFactory.newRepeat()
                .withStartLocalDate(LocalDate.of(2015, 10, 7))
                .withStartLocalTime(LocalTime.of(8, 45))
                .withEndLocalTime(LocalTime.of(10, 15))
                .withEndCriteria(EndCriteria.NEVER)
                .withIntervalUnit(IntervalUnit.YEARLY)
                .withAppointmentData(a2);
    }
}
