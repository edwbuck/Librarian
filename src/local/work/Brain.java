package local.work;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class Brain {

    private static FileSystem fileSystem;
    private static String rootDir;

    public String getRootDir() {
        return rootDir;
    }

    public Brain() {
        fileSystem = FileSystems.getDefault();
        rootDir = String.valueOf('/'); // Change this logic if extending this applicatation to Windows or Mac.

    }
}
