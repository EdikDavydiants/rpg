package ed.av.rpg.auth.model.dto;

import ed.av.rpg.service.client.ClientService;
import ed.av.rpg.util.ClassTypeExecutor;

import java.io.Serializable;

public interface Topical extends Serializable {

    ClientService chooseClientService(ClassTypeExecutor classTypeExecutor);
}
