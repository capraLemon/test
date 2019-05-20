package ru.hh.school.checkup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.checkup.resource.AuthenticationResource;
import ru.hh.school.checkup.resource.CodeResource;
import ru.hh.school.checkup.resource.ContestResource;
import ru.hh.school.checkup.resource.MessageResource;
import ru.hh.school.checkup.resource.TestResource;
import ru.hh.school.checkup.resource.admin.AdminContestResouce;
import ru.hh.school.checkup.resource.tmp.TempDevResource;
import ru.hh.school.checkup.resource.admin.AdminTaskResource;
import ru.hh.school.checkup.resource.TaskResource;
import ru.hh.school.checkup.resource.admin.AdminTestResource;
import ru.hh.school.checkup.resource.tmp.TodoResource;

@Configuration
@Import({AdminTaskResource.class, AdminTestResource.class, AdminContestResouce.class,
        CodeResource.class, ContestResource.class, MessageResource.class,
        TaskResource.class, TempDevResource.class, TodoResource.class, AuthenticationResource.class, TestResource.class})
public class JerseyConfig {
}
