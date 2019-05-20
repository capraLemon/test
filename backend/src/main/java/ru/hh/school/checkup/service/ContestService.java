package ru.hh.school.checkup.service;

import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.ContestDao;
import ru.hh.school.checkup.dto.ContestDto;
import ru.hh.school.checkup.dto.TaskDto;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Contest;
import ru.hh.school.checkup.entity.Participation;
import ru.hh.school.checkup.entity.Task;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;
import ru.hh.school.checkup.resource.TimerDto;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class ContestService {

    private ContestDao contestDAO;
    private AccountService accountService;

    @Inject
    public ContestService(ContestDao contestDAO, AccountService accountService) {
        this.contestDAO = contestDAO;
        this.accountService = accountService;
    }

    @Transactional
    public void createContest(ContestDto contestDTO) {
        Contest contestEntity = new Contest(contestDTO);
        validateFields(contestEntity);
        try {
            contestDAO.save(contestEntity);
        } catch (Exception e) {
            throw new CheckupException(Response.Status.BAD_REQUEST,
                    ErrorMessages.ENTITY_CANNT_BE_SAVED.getErrorMessage());
        }
    }

    @Transactional
    public ContestDto getContestById(Integer id) throws CheckupException {
        Contest contest = getContestEntityById(id);
        return new ContestDto(contest);
    }

    @Transactional
    public List<ContestDto> getContests() {
        List<Contest> contests = contestDAO.findAll();
        return contests.stream()
                .map(contest -> new ContestDto(contest))
                .collect(Collectors.toList());
    }


    @Transactional
    public void updateContest(Integer id, ContestDto contestDTO) throws CheckupException{
        Contest storedContest = getContestEntityById(id);
        storedContest.updateFields(contestDTO);
        validateFields(storedContest);
    }

    @Transactional
    public void deleteContestById(Integer id) {
        Contest contestStored = getContestEntityById(id);
        contestDAO.delete(contestStored);
    }

    private Contest getContestEntityById(Integer id) {
        Contest contestEntity = contestDAO.findById(id);
        if (contestEntity == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return contestEntity;
    }

    private void validateFields(Contest contestEntity) throws CheckupException {
        if (contestEntity.getContestStart().compareTo(contestEntity.getContestEnd()) > 0
                || contestEntity.getDurationForPersonInDays() < 0) {
            throw new CheckupException(Response.Status.BAD_REQUEST,
                    ErrorMessages.ILLEGAL_FIELD_VALUE.getErrorMessage());
        }
    }

    @Transactional
    public List<TaskDto> getTasksForContest(Integer id) {
        Contest storedContest = getContestEntityById(id);
        List<Task> tasks = storedContest.getTasks();
        return tasks.stream()
                .map(task -> new TaskDto(task))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createTask(Integer id, TaskDto taskDTO) {
        Contest storedContest = getContestEntityById(id);
        Task task = new Task(taskDTO);
        task.setCreationDate(new Timestamp(new Date().getTime()));
        task.setContest(storedContest);
        storedContest.getTasks().add(task);
    }

    @Transactional
    public ContestDto getContestForUser() {
        Participation activeParticipation = getUserActiveParticipation();
        return new ContestDto(activeParticipation);
    }

    @Transactional
    public void startParticipation() {
        Participation participation = getUserActiveParticipation();
        if (participation.getParticipationStart() != null
                || !contestIsStarted(participation.getContest())
            ) {
            throw new CheckupException(Response.Status.FORBIDDEN,
                    ErrorMessages.ILLEGAL_CONTEST_SUBSCRIPTION.getErrorMessage());
        }
        participation.setParticipationStart(new Timestamp(new Date().getTime()));
    }

    public Participation getUserActiveParticipation() {
        Account userAccount = accountService.getCurrentUserAccount();
        List<Participation> participations = userAccount.getParticipations();
        Optional<Participation> activeParticipation = participations.stream()
                .filter(p -> p.getContest().getActive())
                .findFirst();
        if (activeParticipation.isEmpty()) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NOT_REGISTRED_FOR_ACTIVE_CONTEST.getErrorMessage());
        }
        return activeParticipation.get();
    }


    private boolean contestIsStarted(Contest contest) {
        Timestamp currentTime =new Timestamp(new Date().getTime());
        return currentTime.compareTo(contest.getContestStart()) >= 0 &&
                currentTime.compareTo(contest.getContestEnd()) < 0;
    }

    @Transactional
    public TimerDto getTimeLeft() {
        Participation participation = getUserActiveParticipation();
        if (participation.getParticipationStart() == null) {
            throw new CheckupException(Response.Status.FORBIDDEN, ErrorMessages.NOT_REGISTRED_FOR_ACTIVE_CONTEST.getErrorMessage());
        }
        Long timeLeft = Math.max(getContestEndTimeForUser(participation) - new Date().getTime(), 0);
        return new TimerDto(timeLeft);
    }

    private Long getContestEndTimeForUser(Participation participation) {
        Timestamp participationStartTime = participation.getParticipationStart();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(participationStartTime.getTime());
        cal.add(Calendar.DAY_OF_WEEK, participation.getContest().getDurationForPersonInDays());
        Timestamp todayPlusAllowedParticationTime = new Timestamp(cal.getTime().getTime());

        Timestamp contestEndTime = participation.getContest().getContestEnd();
        return Math.min(todayPlusAllowedParticationTime.getTime(), contestEndTime.getTime());
    }
}
