package com.tritpo.training.domain;

import com.tritpo.training.exception.UnknownEntityException;

import java.util.Arrays;


public enum Role implements BaseEntity{

  USER(1),
  TEACHER(2),
  ADMIN(3),
  GUEST(4);

  private int id;

  Role(int id) {this.id = id;}


  public int getId() {
    return id;
  }

  public String getName() {
    Role role = resolveById(id);
    return role.toString();
  }

  public static Role resolveById(int id) {
    Role[] role = Role.values();
    return Arrays.stream(role)
            .filter(role1 -> role1.getId() == id)
            .findFirst()
            .orElseThrow(() -> new UnknownEntityException("Role not found"));
  }

}
