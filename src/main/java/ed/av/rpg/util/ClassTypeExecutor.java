package ed.av.rpg.util;

import ed.av.rpg.auth.model.dto.request.RegisterDtoRequest;
import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.auth.model.dto.response.LogInDtoResponse;
import ed.av.rpg.auth.model.dto.response.RegisterDtoResponse;
import ed.av.rpg.auth.service.client.LogInClientService;
import ed.av.rpg.auth.service.client.RegisterClientService;
import ed.av.rpg.service.client.SimpleMessageService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@RequiredArgsConstructor
public class ClassTypeExecutor {

    private final SimpleMessageService simpleMessageService;
    private final LogInClientService logInClientService;
    private final RegisterClientService registerClientService;

    private final Map<String, Class<?>> classNameMap = new HashMap<>();

    @PostConstruct
    public void init() {
        List<Class<? extends Topical>> dtoList = new ArrayList<>();

        dtoList.add(SimpleMessageDto.class);
        dtoList.add(RegisterDtoResponse.class);
        dtoList.add(LogInDtoResponse.class);

        dtoList.forEach(dto -> classNameMap.put(dto.getSimpleName(), dto));
    }

    public Class<?> getType(String className) {
        return classNameMap.get(className);
    }

    public void handleTopical(Topical topical) {
        topical.chooseClientService(this).processTopicalDto(topical);
    }
}
