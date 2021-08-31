package dao;

import entity.Role;

public interface RoleDAO extends AbstractDAO {

    Role getRoleByName(String name);
}
