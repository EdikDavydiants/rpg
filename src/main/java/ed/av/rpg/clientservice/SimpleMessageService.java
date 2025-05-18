package ed.av.rpg.clientservice;

import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.model.dto.Topical;
import org.springframework.stereotype.Service;

@Service
public class SimpleMessageService extends ClientService{

    @Override
    public void processTopicalDto(Topical topical) {
        var simpleMessageDto = castToChildDto(topical);
        System.out.println(simpleMessageDto.getMessage());
    }

    @Override
    public SimpleMessageDto castToChildDto(Topical topical) {
        return (SimpleMessageDto) topical;
    }
}
