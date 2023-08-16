package com.api.servicesInterfaces;

import org.springframework.stereotype.Service;


import com.api.requestModels.User;
import com.api.responseModels.Response;
@Service
public interface IUserService {
	Response create(User req);
	Response update(User req);
	Response getbyid(String req);
	Response list();
	Response getUserByEmail(String email, String password);
}
