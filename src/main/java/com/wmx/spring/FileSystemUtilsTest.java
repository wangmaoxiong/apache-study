package com.wmx.spring;

import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Spring-core 核心工具类 之 文件系统工具类，用于文件/目录复制与删除
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 11:04
 */
public class FileSystemUtilsTest {
    /**
     * copyRecursively(File src, File dest)
     * copyRecursively(Path src, Path dest)
     * 1、递归复制源文件或者目录到目标文件或目录，底层使用 {@link Files#copy(java.nio.file.Path, java.nio.file.Path, java.nio.file.CopyOption...)}
     * 2、src、dest 不能为 null，否则非法参数异常。
     * 3、src 不存在时抛异常，dest 不存在时会自动创建
     */
    @Test
    public void copyRecursively() {
        try {
            File file1 = new File("C:\\wmx\\temp\\git\\MyDocument\\images");
            File file2 = new File("C:\\wmx\\temp\\git\\MyDocument\\images2");
            FileSystemUtils.copyRecursively(file1, file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * boolean deleteRecursively(@Nullable File root)
     * boolean deleteRecursively(@Nullable Path root)
     * 1、递归删除，root 为null，或者不存在，都返回 false。底层使用 {@link Files#delete(java.nio.file.Path)}
     */
    @Test
    public void deleteRecursively() {
        try {
            Path root = new File("C:\\wmx\\temp\\git\\MyDocument\\images2").toPath();
            boolean recursively = FileSystemUtils.deleteRecursively(root);
            System.out.println(recursively);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
