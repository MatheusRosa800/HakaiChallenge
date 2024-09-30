import com.sprint4.redribbon.tampering.IsTampering;

public class caller {
    public boolean isTampering(String filePath, String gitHubFileUrl) {
        return IsTampering.check(filePath, gitHubFileUrl);
    }
}
