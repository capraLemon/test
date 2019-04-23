package ru.hh.school.checkup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.school.checkup.dao.TodoDAO;
import ru.hh.school.checkup.entity.Todo;
import ru.hh.school.checkup.service.TodoService;


@Configuration
@Import({NabProdConfig.class,
         NabHibernateCommonConfig.class,
         TodoService.class,
         TodoDAO.class})
public class CommonConfig {

    @Bean
    MappingConfig mappingConfig() {
        return new MappingConfig(
            Todo.class
        );
    }
}
