package org.mentalizr.infra.buildEntities;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.connections.ConnectionMaria;
import org.mentalizr.persistence.rdbms.barnacle.connectionManager.EntityNotFoundException;
import org.mentalizr.persistence.rdbms.barnacle.dao.RoleAdminDAO;
import org.mentalizr.persistence.rdbms.barnacle.dao.UserDAO;
import org.mentalizr.persistence.rdbms.barnacle.dao.UserLoginDAO;
import org.mentalizr.persistence.rdbms.barnacle.vo.RoleAdminVO;
import org.mentalizr.persistence.rdbms.barnacle.vo.UserLoginVO;
import org.mentalizr.persistence.rdbms.barnacle.vo.UserVO;
import org.mentalizr.persistence.rdbms.utils.Argon2Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class M7rAdmin {

    private static final String ADMIN_ID = "aaaaaaaa-bbbb-1234567890abcdefaaaaaa";

    private static final Logger logger = LoggerFactory.getLogger(M7rAdmin.class.getSimpleName());

    private static final String m7rAdminUser = Configuration.getM7rAdminUser();
    private static final String m7rAdminPassword = Configuration.getM7rAdminPassword();

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
        String pwHash = Argon2Hash.getHash(m7rAdminPassword.toCharArray());

        try (Connection connection = ConnectionMaria.getConnectionToDbAsRoot()) {
            UserVO userVO = new UserVO(ADMIN_ID);
            userVO.setActive(true);
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
            UserLoginDAO.create(userLoginVO, connection);
        } catch (SQLException e) {
            throw new InfraRuntimeException("Exception on initializing m7r admin user: " + e.getMessage(), e);
        }

    }

}
