package ed.av.rpg.util;

import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.clientservice.RegisterService;
import ed.av.rpg.clientservice.SimpleMessageService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class ClassTypeExecutor {

    private final SimpleMessageService simpleMessageService;
    private final RegisterService registerService;

    private final Map<String, Class<?>> classNameMap = new HashMap<>();

    public ClassTypeExecutor(
            SimpleMessageService simpleMessageService,
            RegisterService registerService) {
        this.simpleMessageService = simpleMessageService;
        this.registerService = registerService;

        classNameMap.put(RegisterDto.class.getSimpleName(), RegisterDto.class);
        classNameMap.put(SimpleMessageDto.class.getSimpleName(), SimpleMessageDto.class);
    }

    public Class<?> getType(String className) {
        return classNameMap.get(className);
    }

    public void handlePayload(Topical topical) {
        topical.chooseClientService(this).processTopicalDto(topical);
    }
}
