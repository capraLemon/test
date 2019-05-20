package ru.hh.school.checkup;

import ru.hh.school.checkup.exception.CheckupExceptionMapper;
import ru.hh.school.checkup.filter.AuthFilter;
import ru.hh.school.checkup.filter.CORSResponseFilter;
import ru.hh.nab.starter.NabApplication;

public class Main {

  public static void main(String[] args) {
    NabApplication.builder()
            .configureJersey(JerseyConfig.class)
            .registerResources(
                    AuthFilter.class,
                    CORSResponseFilter.class,
                    CheckupExceptionMapper.class)
            .bindToRoot()
            .build().run(ProdConfig.class);
  }
}
