package ru.hh.school.checkup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hh.school.checkup.entity.Contest;
import ru.hh.school.checkup.entity.Participation;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement
public class ContestDto {
    public Integer contestId;
    public String name;
    public Timestamp contestStart;
    public Timestamp contestEnd;
    public Integer durationForPersonInDays;
    public Boolean isActive;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean userParticipationIsStarted;


    public ContestDto() {
    }

    public ContestDto(Contest contest) {
        contestId = contest.getContestId();
        name = contest.getName();
        contestStart = contest.getContestStart();
        contestEnd = contest.getContestEnd();
        durationForPersonInDays = contest.getDurationForPersonInDays();
        isActive = contest.getActive();
    }

    public ContestDto(Participation participation) {
        this(participation.getContest());
        userParticipationIsStarted = participation.getParticipationStart() != null;
    }

}
