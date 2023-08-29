package org.mentalizr.infra.buildEntities;

import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.buildEntities.connections.ConnectionMaria;
import org.mentalizr.persistence.rdbms.barnacle.connectionManager.EntityNotFoundException;
import org.mentalizr.persistence.rdbms.barnacle.dao.RoleAdminDAO;
import org.mentalizr.persistence.rdbms.barnacle.dao.UserDAO;
import org.mentalizr.persistence.rdbms.barnacle.dao.UserLoginDAO;
import org.mentalizr.persistence.rdbms.barnacle.vo.RoleAdminVO;
import org.mentalizr.persistence.rdbms.barnacle.vo.UserLoginVO;
import org.mentalizr.persistence.rdbms.barnacle.vo.UserVO;
import org.mentalizr.persistence.rdbms.utils.Argon2Hash;

import java.sql.Connection;
import java.sql.SQLException;

public class M7rAdmin {

    private static final String ADMIN_ID = "aaaaaaaa-bbbb-1234567890abcdefaaaaaa";

    public static boolean isAdminUserInitialized() {
        try (Connection connection = ConnectionMaria.getConnectionToDbAsRoot()) {

            try {
                UserDAO.load(ADMIN_ID, connection);
                return true;
            } catch (EntityNotFoundException e) {
                return false;
            }
        } catch (SQLException e) {
            throw new InfraRuntimeException("Exception when querying maria for admin user: " + e.getMessage(), e);
        }
    }

    public static void init() {
        InfraUserConfiguration infraUserConfiguration = ApplicationContext.getInfraUserConfiguration();
        String m7rAdminUser = infraUserConfiguration.getM7rAdminUser();
        String m7rAdminPassword = infraUserConfiguration.getM7rAdminPassword();

        String pwHash = Argon2Hash.getHash(m7rAdminPassword.toCharArray());

        try (Connection connection = ConnectionMaria.getConnectionToDbAsRoot()) {
            UserVO userVO = new UserVO(ADMIN_ID);
            userVO.setActive(true);
            userVO.setCreation(System.currentTimeMillis());
            UserDAO.create(userVO, connection);

            RoleAdminVO roleAdminVO = new RoleAdminVO(ADMIN_ID);
            RoleAdminDAO.create(roleAdminVO, connection);

            UserLoginVO userLoginVO = new UserLoginVO(ADMIN_ID);
            userLoginVO.setUsername(m7rAdminUser);
            userLoginVO.setEmail("mentalizr@example.org");
            userLoginVO.setFirstName("admin");
            userLoginVO.setLastName("admin");
            userLoginVO.setGender(1);
            userLoginVO.setPasswordHash(pwHash);
            userLoginVO.setSecondFA(false);
            userLoginVO.setEmailConfirmation(0L);
            userLoginVO.setRenewPasswordRequired(false);
            UserLoginDAO.create(userLoginVO, connection);

        } catch (SQLException e) {
            throw new InfraRuntimeException("Exception on initializing m7r admin user: " + e.getMessage(), e);
        }

    }

}
