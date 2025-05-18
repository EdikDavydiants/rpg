package ed.av.rpg.clientservice;

import ed.av.rpg.auth.model.dto.Topical;

public abstract class ClientService {

    public abstract void processTopicalDto(Topical topical);

    public abstract Topical castToChildDto(Topical topical);
}
