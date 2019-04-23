package ru.hh.school.checkup;

import ru.hh.school.checkup.filter.CORSResponseFilter;
import ru.hh.nab.starter.NabApplication;

public class Main {

  public static void main(String[] args) {
    NabApplication.builder()
            .configureJersey(JerseyConfig.class)
            .registerResources(CORSResponseFilter.class).bindToRoot()
            .build().run(ProdConfig.class);
  }
}
