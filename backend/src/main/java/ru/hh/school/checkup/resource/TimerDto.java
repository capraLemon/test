package ru.hh.school.checkup.resource;

public class TimerDto {
    public Long timeLeftMilliSecs;

    public TimerDto(Long timeLeft) {
        this.timeLeftMilliSecs = timeLeft;
    }
}
