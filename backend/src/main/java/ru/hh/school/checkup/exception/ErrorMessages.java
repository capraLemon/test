package ru.hh.school.checkup.exception;


public enum ErrorMessages {
    ILLEGAL_FIELD_VALUE("Illegal field value"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    ENTITY_CANNT_BE_SAVED("Entity cann't be saved"),
    NO_RECORD_FOUND("No record found."),
    SOLUTION_CANT_BE_BLANK("Solution cann't be blank"),
    ILLEGAL_CONTEST_SUBSCRIPTION("Illegal contest subscription"),
    UNAUTHORIZED_ACCESS("Unauthorized access"),
    USER_LOGIN_OR_PASSWORD_IS_INCORRECT("User login or password is incorrect"),
    NOT_AUTHENTICATED("User is not authenticated."),
    NOT_REGISTRED_FOR_ACTIVE_CONTEST("User is not registered for any active contest"),
    ILLEGAL_PROGRAMMING_LANGUAGE("Illegal programming language is provided"),
    CONTEST_PERIOD_FOR_USER_HAS_FINISHED("Contest period for user has finished");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
