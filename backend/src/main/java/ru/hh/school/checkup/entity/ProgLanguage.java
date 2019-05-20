package ru.hh.school.checkup.entity;

public enum ProgLanguage {
    // Programming languages ids are mapped to those of judje0  https://api.judge0.com/#statuses-and-languages-languages-get
    JAVA(26),
    JAVA_SCRIPT(29),
    PYTHON(34);

    private Integer languageId;
    ProgLanguage(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getLanguageId() {
        return languageId;
    }


}
