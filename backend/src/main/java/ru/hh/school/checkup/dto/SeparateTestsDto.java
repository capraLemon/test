package ru.hh.school.checkup.dto;

import java.util.List;

public class SeparateTestsDto {
    public List<UserTestDto> userTests;
    public List<UserTestDto> adminTests;
    public SeparateTestsDto(List<UserTestDto> userTests, List<UserTestDto> adminTests) {
        this.userTests = userTests;
        this.adminTests = adminTests;
    }
}
