package ed.av.rpg.service.client;

import ed.av.rpg.auth.model.dto.Topical;

public abstract class ClientService {

    public abstract void processTopicalDto(Topical topical);

    public abstract Topical castToChildDto(Topical topical);
}
