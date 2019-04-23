package ru.hh.school.checkup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.checkup.resource.TodoResource;

@Configuration
@Import({TodoResource.class})
public class JerseyConfig {
}
