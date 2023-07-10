package org.mentalizr.infra.buildEntities.initFiles.tomcat;

import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.buildEntities.initFiles.InitFile;

public class TomcatContextXml implements InitFile {

    private final String userName;
    private final String password;

    public static TomcatContextXml getInstanceFromConfiguration() {
        InfraUserConfiguration infraUserConfiguration = ApplicationContext.getInfraUserConfiguration();
        String userName = infraUserConfiguration.getUserDbUser();
        String password = infraUserConfiguration.getUserDbPassword();
        return new TomcatContextXml(userName, password);
    }

    private TomcatContextXml(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getFileName() {
        return "context.xml";
    }

    @Override
    public String getContent() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Context>\n" +
                "\n" +
                "    <!-- Default set of monitored resources. If one of these changes, the    -->\n" +
                "    <!-- web application will be reloaded.                                   -->\n" +
                "    <WatchedResource>WEB-INF/web.xml</WatchedResource>\n" +
                "    <WatchedResource>WEB-INF/tomcat-web.xml</WatchedResource>\n" +
                "    <WatchedResource>${catalina.base}/conf/web.xml</WatchedResource>\n" +
                "\n" +
                "    <!-- Uncomment this to disable session persistence across Tomcat restarts -->\n" +
                "    <!--\n" +
                "    <Manager pathname=\"\" />\n" +
                "    -->\n" +
                "    \n" +
                "    <Resource name=\"jdbc/MentalizrUserDb\" auth=\"Container\" type=\"javax.sql.DataSource\"\n" +
                "               maxTotal=\"100\" maxIdle=\"30\" maxWaitMillis=\"10000\"\n" +
                "               username=\"" + this.userName + "\" password=\"" + this.password + "\" driverClassName=\"org.mariadb.jdbc.Driver\"\n" +
                "               url=\"jdbc:mysql://maria:3306/mentalizr\"/>\n" +
                "    \n" +
                "</Context>";
    }

}
