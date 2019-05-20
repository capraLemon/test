package ru.hh.school.checkup.dto;

import java.math.BigInteger;

public class MessageTitlesDto {

    public Long quantity;
    public String title;
    public Integer topicId;

    public MessageTitlesDto(Object[] arr) {
        topicId = (Integer) arr[0];
        title = (String) arr[1];
        quantity = ((BigInteger) arr[2]).longValue();
    }
}
