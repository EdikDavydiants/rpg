package ed.av.rpg.util;

import ed.av.rpg.auth.model.dto.request.RegisterDto;
import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.auth.model.dto.response.LogInDtoResponse;
import ed.av.rpg.service.client.LogInClientService;
import ed.av.rpg.service.client.RegisterService;
import ed.av.rpg.service.client.SimpleMessageService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@RequiredArgsConstructor
public class ClassTypeExecutor {

    private final SimpleMessageService simpleMessageService;
    private final RegisterService registerService;
    private final LogInClientService logInClientService;

    private final Map<String, Class<?>> classNameMap = new HashMap<>();

    @PostConstruct
    public void init() {
        classNameMap.put(SimpleMessageDto.class.getSimpleName(), SimpleMessageDto.class);
        classNameMap.put(RegisterDto.class.getSimpleName(), RegisterDto.class);
        classNameMap.put(LogInDtoResponse.class.getSimpleName(), LogInDtoResponse.class);
    }

    public Class<?> getType(String className) {
        return classNameMap.get(className);
    }

    public void handleTopical(Topical topical) {
        topical.chooseClientService(this).processTopicalDto(topical);
    }
}
