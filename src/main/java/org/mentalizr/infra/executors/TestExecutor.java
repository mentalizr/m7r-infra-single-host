package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;

public class TestExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("test called.");

//        init();
    }

//    public static void init() {
//        InfraUserConfiguration infraUserConfiguration = ApplicationContext.getInfraUserConfiguration();
////        String m7rAdminUser = infraUserConfiguration.getM7rAdminUser();
//        String m7rAdminPassword = infraUserConfiguration.getM7rAdminPassword();
//
//        String pwHash = Argon2Hash.getHash(m7rAdminPassword.toCharArray());
//
//        int counter = 3;
//        System.out.println("counter: " + counter);
//
//
//        final String USER = "test-user-" + counter;
//        final String ID = "tttttttt-" + counter;
//
//
//
//        try (Connection connection = ConnectionMaria.getConnectionToDbAsRoot()) {
//            UserVO userVO = new UserVO(ID);
//            userVO.setActive(true);
////            userVO.setFirstActive(0L);
////            userVO.setLastActive(0L);
//            UserDAO.create(userVO, connection);
//
//            RoleAdminVO roleAdminVO = new RoleAdminVO(ID);
//            RoleAdminDAO.create(roleAdminVO, connection);
//
//            UserLoginVO userLoginVO = new UserLoginVO(ID);
//            userLoginVO.setUsername(USER);
//            userLoginVO.setEmail("mentalizr@example.org");
//            userLoginVO.setFirstName("admin");
//            userLoginVO.setLastName("admin");
//            userLoginVO.setGender(1);
//            userLoginVO.setPasswordHash(pwHash);
//            userLoginVO.setSecondFA(false);
//            userLoginVO.setEmailConfirmation(0L);
//            userLoginVO.setRenewPasswordRequired(false);
//            UserLoginDAO.create(userLoginVO, connection);
//
//        } catch (SQLException e) {
//            throw new InfraRuntimeException("Exception on initializing m7r admin user: " + e.getMessage(), e);
//        }
//
//    }

}
