package com.xccaia.service;

import com.xccaia.entity.User;
import java.util.List;

public interface IUserService {


  List<User> getAll();

  User getOne(String id);

  void clear();

}
