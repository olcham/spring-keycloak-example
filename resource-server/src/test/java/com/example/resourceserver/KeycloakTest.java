package com.example.resourceserver;

import com.example.resourceserver.keycloak.KeycloakApi;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.*;

import javax.ws.rs.core.*;
import java.security.*;
import java.util.*;

public class KeycloakTest {
    @Test
    public void test_createUser() {
        KeycloakApi api = new KeycloakApi();
        api.createUser("test", "test");
    }

    @Test
    public void test_createUserAndRole_andMap() {
        KeycloakApi api = new KeycloakApi();

        String role = "testers";
        String username = "test";
        String password = "test";

        // create role
//        api.createRealmRoles(role);

        // create user
//        api.createUser(username, password);

        // map user to role
        api.mapUserRole(username, role);

    }

    @Test
    public void test_exportImport() {
        KeycloakApi api = new KeycloakApi();

        UserRepresentation user = api.newUser("importedUser", "importedUser");

        RealmRepresentation realm = api.exportRealm();

        PartialImportRepresentation representation = new PartialImportRepresentation();

        representation.setClients(realm.getClients());
        representation.setGroups(realm.getGroups());
        representation.setIdentityProviders(realm.getIdentityProviders());
        representation.setRoles(realm.getRoles());
        representation.setIfResourceExists("OVERWRITE");

        List<UserRepresentation> users = api.getUsers();
        users.add(user);
        representation.setUsers(users);

        Response response = api.importRealm(representation);
        System.out.println(response);
    }
}
