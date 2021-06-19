package pirv.yolo.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * maven本地仓库清理，仅保留含有.jar和.pom的文件夹，其余均删除
 */
public final class CleanMvnRepo {
    private static final FolderFilter folderFilter = new FolderFilter();

    public static void main(String[] args) throws IOException {
        String filePath = "";
        clean(filePath);
    }

    private static void clean(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("文件路径不能为空！");
            return;
        }
        Path curFile = Paths.get(filePath);
        if (Files.notExists(curFile)) {
            System.out.println("非法文件路径或文件路径不存在！");
            return;
        }

        doClean(curFile);
    }

    private static void doClean(Path curFile) throws IOException {
        // 当前文件夹是否含有子文件夹
        boolean isContainsSubFolders = false;
        // 当前文件夹是否含有以".pom"结尾的文件
        boolean isContainsFilesEndsWithPom = false;
        // 当前文件夹是否含有以".jar"结尾的文件
        boolean isContainsFilesEndsWithJar = false;

        File[] subFiles = curFile.toFile().listFiles();
        if (isEmptyFolder(subFiles)) {
            delete(curFile);
        } else {
            for (File file : subFiles) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".pom")) {
                        isContainsFilesEndsWithPom = true;
                        continue;
                    }
                    if (file.getName().endsWith(".jar")) {
                        isContainsFilesEndsWithJar = true;
                        continue;
                    }
                    if (file.getName().endsWith(".sha1")) continue;
                    // 文件只保留".pom"，".jar"，".sha1"等3种类型的文件，其余类型文件（包含隐藏文件）均删除
                    delete(file.toPath());
                } else {
                    doClean(file.toPath());
                    isContainsSubFolders = !isEmptyFolder(curFile.toFile().listFiles(folderFilter));
                }
            }

            if (!isContainsSubFolders && !isContainsFilesEndsWithPom && !isContainsFilesEndsWithJar) {
                delete(curFile);
            }
        }
    }

    private static void delete(Path path) throws IOException {
        if (Files.deleteIfExists(path)) {
            System.out.println("删除：" + path.toAbsolutePath());
        }
    }

    private static boolean isEmptyFolder(File[] files) {
        return files == null || files.length == 0;
    }
}

/**
 * 文件夹过滤器
 */
class FolderFilter implements FileFilter {
    @Override
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
