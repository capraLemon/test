package ru.hh.school.checkup.entity;


import ru.hh.school.checkup.dto.ContestDto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contest_id")
    private Integer contestId;

    @Column(name="name")
    private String name;

    @Column(name="contest_start")
    private Timestamp contestStart;

    @Column(name="contest_end")
    private Timestamp contestEnd;

    @Column(name="duration_for_person_in_days")
    private Integer durationForPersonInDays;

    @Column(name="is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contest")
    private List<Task> tasks = new ArrayList<>();

    public Contest(ContestDto contestDTO) {
        contestId = contestDTO.contestId;
        name = contestDTO.name;
        contestStart = contestDTO.contestStart;
        contestEnd = contestDTO.contestEnd;
        durationForPersonInDays = contestDTO.durationForPersonInDays;
        isActive = contestDTO.isActive;

    }

    public Contest() {
    }


    public void updateFields(ContestDto contestDTO) {
        if (contestDTO.name != null) {
            name = contestDTO.name;
        }
        if (contestDTO.contestStart != null) {
            contestStart = contestDTO.contestStart;
        }
        if (contestDTO.contestEnd != null) {
            contestEnd = contestDTO.contestEnd;
        }
        if (contestDTO.durationForPersonInDays != null) {
            durationForPersonInDays = contestDTO.durationForPersonInDays;
        }
        if (contestDTO.isActive != null) {
            isActive = contestDTO.isActive;
        }
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getContestStart() {
        return contestStart;
    }

    public void setContestStart(Timestamp contestStart) {
        this.contestStart = contestStart;
    }

    public Timestamp getContestEnd() {
        return contestEnd;
    }

    public void setContestEnd(Timestamp contestEnd) {
        this.contestEnd = contestEnd;
    }

    public Integer getDurationForPersonInDays() {
        return durationForPersonInDays;
    }

    public void setDurationForPersonInDays(Integer durationForPersonInDays) {
        this.durationForPersonInDays = durationForPersonInDays;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        return Objects.equals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contestId, name, contestStart, contestEnd, isActive);
    }
}
