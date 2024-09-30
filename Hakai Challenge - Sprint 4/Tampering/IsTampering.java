package com.sprint4.redribbon.tampering;

public class IsTampering {
    public static boolean check(String filePath, String gitHubFileUrl) {
        String localFileHash = FileHashUtils.getFileSha256Hash(filePath);
        String gitHubHash = FileHashUtils.getHashFromGitHub(gitHubFileUrl);

        if (localFileHash != null && localFileHash.equals(gitHubHash)) {
            System.out.println("Passou: O arquivo não foi adulterado.");
            return false;
        } else {
            System.out.println("Tampering Alert: O arquivo foi adulterado.");
            return true;
        }
    }
}




