package ru.hh.school.checkup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabProdConfig;

@Configuration
@Import({NabProdConfig.class,
         NabHibernateCommonConfig.class,
         CheckupConfig.class})
public class CommonConfig {

}
