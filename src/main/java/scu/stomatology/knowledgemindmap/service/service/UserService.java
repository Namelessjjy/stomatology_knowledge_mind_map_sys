package scu.stomatology.knowledgemindmap.service.service;

import scu.stomatology.knowledgemindmap.util.Response;

public interface UserService {

    Response login(String username, String password);

    Response register(String username, String password);


}
