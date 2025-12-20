package local.work;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class Brain {

    private FileSystem fileSystem;
    private String rootDir;

    public String getRootDir() {
        return rootDir;
    }

    public Brain() {
        fileSystem = FileSystems.getDefault();
        rootDir = fileSystem.getRootDirectories().toString();
    }























}
