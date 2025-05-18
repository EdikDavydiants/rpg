package ed.av.rpg.auth.model.dto;

import ed.av.rpg.clientservice.ClientService;
import ed.av.rpg.util.ClassTypeExecutor;

import java.io.Serializable;

public interface Topical extends Serializable {

    default String getTopicalClassName() {
        return getClass().getSimpleName();
    }

    ClientService chooseClientService(ClassTypeExecutor classTypeExecutor);
}
