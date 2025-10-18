package cncware.cncwareserviceportalbackend.models.entities;

import cncware.cncwareserviceportalbackend.models.enums.Role;

public class User {
    private int id;
    private String email;
    private String password;
    private Role role;
}
