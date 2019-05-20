package ru.hh.school.checkup.service;


import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;
import ru.hh.school.checkup.dao.BrowserCodeDao;
import ru.hh.school.checkup.dao.SolutionDao;
import ru.hh.school.checkup.dto.SolutionDto;
import ru.hh.school.checkup.dto.SolutionStatusDto;
import ru.hh.school.checkup.entity.Account;
import ru.hh.school.checkup.entity.BrowserCode;
import ru.hh.school.checkup.entity.ProgLanguage;
import ru.hh.school.checkup.entity.Solution;
import ru.hh.school.checkup.entity.SolutionStatus;
import ru.hh.school.checkup.entity.Task;
import ru.hh.school.checkup.exception.CheckupException;
import ru.hh.school.checkup.exception.ErrorMessages;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SolutionService {

    private TaskService taskService;
    private SolutionDao solutionDAO;
    private AccountService accountService;
    private BrowserCodeDao browserCodeDAO;

    @Inject
    public SolutionService(TaskService taskService, SolutionDao solutionDAO, AccountService accountService,
                           BrowserCodeDao browserCodeDAO) {
        this.taskService = taskService;
        this.solutionDAO = solutionDAO;
        this.accountService = accountService;
        this.browserCodeDAO = browserCodeDAO;

    }
    public SolutionStatusDto processSolution(SolutionDto solutionDTO, Integer taskId) {
        validateSolution(solutionDTO);
        Solution solution = taskService.createSolution(taskId, solutionDTO);
        return new SolutionStatusDto(solution);
    }

    public void validateSolution(SolutionDto solutionDTO) {
        if (solutionDTO.solutionText == null || solutionDTO.solutionText.trim().isEmpty()) {
            throw new CheckupException(Response.Status.BAD_REQUEST,
                    ErrorMessages.SOLUTION_CANT_BE_BLANK.getErrorMessage());
        }
        if (solutionDTO.progLanguage == null
                || !EnumUtils.isValidEnum(ProgLanguage.class, solutionDTO.progLanguage.toString())) {
            throw new CheckupException(Response.Status.BAD_REQUEST,
                    ErrorMessages.ILLEGAL_PROGRAMMING_LANGUAGE.getErrorMessage());
        }
    }

    @Transactional
    public Optional<Solution> getUncheckedSolution() {
        Optional<Solution> solution =  solutionDAO.getUncheckedSolution();
        if (solution.isPresent()) {
            //TODO only contest tests of admin should be fetched (separate table for smoke tests)
            solution.get().getTask().getTests().size();
        }
        return solution;
    }

    @Transactional
    public List<SolutionStatusDto> getUserSolutionsByTask(Integer taskId) {
        Task task = taskService.getTaskEntityById(taskId);
        Account account = accountService.getCurrentUserAccount();
        List<Solution> solutions = solutionDAO.getSolutions(account, task);
        return solutions.stream()
                .map(solution -> new SolutionStatusDto(solution))
                .collect(Collectors.toList());
    }

    @Transactional
    public SolutionDto getSolutionByIdForUser(Integer solutionId) {
        Solution solution = getSolutionEntity(solutionId);
        Account userAccount = accountService.getCurrentUserAccount();
        if (!solution.getAccount().equals(userAccount)) {
            throw new CheckupException(Response.Status.FORBIDDEN,
                    ErrorMessages.UNAUTHORIZED_ACCESS.getErrorMessage());
        }
        return new SolutionDto(solution);
    }


    public Solution getSolutionEntity(Integer solutionId) {
        Solution solution = solutionDAO.findById(solutionId);
        if (solution == null) {
            throw new CheckupException(Response.Status.NOT_FOUND, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return solution;
    }

    @Transactional
    public SolutionDto getBrowserSolution(Integer taskId) {
        BrowserCode browserCode = getBrowserSolutionEntity(taskId);
        return new SolutionDto(browserCode);
    }

    @Transactional
    public void setBrowserSolution(Integer taskId, SolutionDto solutionDTO) {
        BrowserCode browserCode = getBrowserSolutionEntity(taskId);
        browserCode.update(solutionDTO);
        browserCodeDAO.save(browserCode);
    }

    public BrowserCode getBrowserSolutionEntity(Integer taskId) {
        Account account = accountService.getCurrentUserAccount();
        Task task = taskService.getTaskEntityById(taskId);
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("account", account);
        criterias.put("task", task);
        List<BrowserCode> browserCodes = browserCodeDAO.findByCriterias(criterias);
        BrowserCode browserCode;
        if (browserCodes == null || browserCodes.size() < 1) {
            browserCode = new BrowserCode(account, task, ProgLanguage.PYTHON, "# Good luck!");
            browserCodeDAO.save(browserCode);
        } else {
            browserCode = browserCodes.get(0);
        }
        return browserCode;
    }

    @Transactional
    public void updateSolution(Solution solution, SolutionStatus status) {
        solution.setStatusId(status);
        solutionDAO.save(solution);
    }

}
