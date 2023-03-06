import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name = "createUserData")
    public static Object[][] getUserData() {
        return new Object[][]{{"Tarun", "leader"},
                {"Sneha", "zion resident"},
                {"Vatsal", "leader"}
        };
    }
}
