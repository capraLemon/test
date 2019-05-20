package ru.hh.school.checkup.service;

import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.TaskDao;
import ru.hh.school.checkup.dto.SolutionDto;
import ru.hh.school.checkup.dto.TaskDescriptionDto;
import ru.hh.school.checkup.dto.TaskDto;
import ru.hh.school.checkup.dto.TaskTitleDto;
import ru.hh.school.checkup.dto.TestDto;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.Participation;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.Task;
import ru.hh.school.checkup.entity.Test;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TaskService {

    private TaskDao taskDAO;
    private ContestService contestService;
    private AccountService accountService;

    @Inject
    public TaskService(TaskDao taskDAO,
                       ContestService contestService,
                       AccountService accountService) {
        this.taskDAO = taskDAO;
        this.contestService = contestService;
        this.accountService = accountService;
    }

    @Transactional
    public TaskDto getTaskById(Integer id) throws CheckupException {
        Task task = getTaskEntityById(id);
        return new TaskDto(task);
    }

    @Transactional
    public List<TaskDto> getTasksForAdmin() {
        Account userAccount = accountService.getCurrentUserAccount();
        List<Participation> participations = userAccount.getParticipations();
        List<Task> tasks = taskDAO.findAll();
        return tasks.stream()
                .map(task -> new TaskDto(task))
                .collect(Collectors.toList());
    }


    public Task getTaskEntityById(Integer id) {
        Task taskEntity = taskDAO.findById(id);
        if (taskEntity == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return taskEntity;
    }

    @Transactional
    public void updateTask(Integer id, TaskDto taskDTO) throws CheckupException {
        Task storedTask = getTaskEntityById(id);
        storedTask.updateFields(taskDTO);
    }

    @Transactional
    public void deleteTaskById(Integer id) throws CheckupException {
        Task storedTask = getTaskEntityById(id);
        taskDAO.delete(storedTask);
    }

    @Transactional
    public List<TestDto> getTestsForTask(Integer id) {
        Task storedTask = getTaskEntityById(id);
        List<Test> tests = storedTask.getTests();
        return tests.stream()
                .map(test -> new TestDto(test))
                .collect(Collectors.toList());
    }


    @Transactional
    public void createTest(Integer id, TestDto testDTO) {
        Task storedTask = getTaskEntityById(id);
        Test test = new Test(testDTO);
        test.setTask(storedTask);
        storedTask.getTests().add(test);
    }

    @Transactional
    public Solution createSolution(Integer taskId, SolutionDto solutionDTO) {
        Account account = accountService.getCurrentUserAccount();
        Task storedTask = getTaskEntityById(taskId);
        Solution solution = new Solution(solutionDTO);
        solution.setTask(storedTask);
        solution.setAccount(account);
        storedTask.getSolutions().add(solution);
        return solution;
    }

    @Transactional
    public List<TaskTitleDto> getTasksForUser() {
        Participation userActiveParticipation = contestService.getUserActiveParticipation();
        if (userActiveParticipation.getParticipationStart() == null) {
            throw new CheckupException(Response.Status.FORBIDDEN,
                    ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        List<TaskTitleDto> tasks = userActiveParticipation.getContest().getTasks()
                                    .stream()
                                    .filter(task -> !task.getIsChat())
                                    .map(task -> new TaskTitleDto(task))
                                    .sorted((t1, t2) -> Integer.compare(t1.taskId, t2.taskId))
                                    .collect(Collectors.toList());
        return tasks;
    }

    @Transactional
    public TaskDto getSingleTaskForUser(Integer taskId) {
        Task task = getTaskEntityById(taskId);
        Participation userParticipation;
        try {
            userParticipation = contestService.getUserActiveParticipation();
        } catch (CheckupException ex) {
            throw new CheckupException(Response.Status.FORBIDDEN,
                    ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        if (userParticipation.getParticipationStart() == null
                || !userParticipation.getContest().getTasks().contains(task)) {
            throw new CheckupException(Response.Status.FORBIDDEN,
                    ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        List<TaskDescriptionDto> taskDescriptionDtos = task.getTaskDescriptions()
                                    .stream()
                                    .sorted((t1, t2) -> t1.getTaskDescriptionId() - t2.getTaskDescriptionId())
                                    .map(taskDescription-> new TaskDescriptionDto(taskDescription))
                                    .collect(Collectors.toList());

        return new TaskDto(task, taskDescriptionDtos);
    }
}
