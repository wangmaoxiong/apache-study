package com.wmx;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FileUtils 通用文件操作工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/11/21 15:30
 */

public class FileUtilsTest {
    /**
     * write(final File file, final CharSequence data, final Charset encoding)
     * 1、file：写入的文件对象，路径不存在时，自动新建
     * 2、data：写如文件的内容
     * 3、encoding：文件内容编码
     *
     * @throws IOException
     */
    @Test
    public void write1() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write1.txt");
        String context = "{\"name\":\"BeJson帅\",\"url\":\"http://www.bejson.com\"page\":88,\"isNonProfit\":true}";
        FileUtils.write(file, context, Charset.forName("UTF-8"));
        System.out.println("写入文件：" + file.getAbsoluteFile());
    }

    /**
     * write(final File file, final CharSequence data, final Charset encoding, final boolean append)
     * 1、file：写入的文件对象，路径不存在时，自动新建
     * 2、data：写如文件的内容
     * 3、encoding：文件内容编码
     * 4、append：内容是否追加
     *
     * @throws IOException
     */
    @Test
    public void write2() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write2.txt");
        String context = "{\"name\":\"BeJson帅\",\"url\":\"http://www.bejson.com\"page\":88,\"isNonProfit\":true}\r\n";
        FileUtils.write(file, context, Charset.forName("UTF-8"), true);
        System.out.println("写入文件：" + file.getAbsoluteFile());
    }

    /**
     * 将字节数组写入到指定文件，路径不存在时，自动新建
     * writeByteArrayToFile(final File file, final byte[] data)
     * writeByteArrayToFile(final File file, final byte[] data, final boolean append)
     * writeByteArrayToFile(final File file, final byte[] data, final int off, final int len)
     * writeByteArrayToFile(final File file, final byte[] data, final int off, final int len,final boolean append)
     *
     * @throws IOException
     */
    @Test
    public void write3() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write3.txt");
        String context = "{\"name\":\"汪茂雄\",\"url\":\"http://www.bejson.com\"page\":88,\"isNonProfit\":true}\r\n";
        FileUtils.writeByteArrayToFile(file, context.getBytes(), true);
        System.out.println("写入文件：" + file.getAbsoluteFile());
    }

    /**
     * writeLines(final File file, final String encoding, final Collection<?> lines,final boolean append)
     * file：待写入的文件，路径不存在时，自动新建；
     * lines：要写入的行，null 表示插入空行
     * append：内容是否追加
     * lineEnding：要使用的行分隔符，null 表示使用系统默认值
     * encoding：要使用的编码，{@code null}表示平台默认值
     *
     * @throws IOException
     */
    @Test
    public void write4() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write4.txt");
        List<String> lines = new ArrayList<>();
        lines.add(null);
        for (int i = 0; i < 10; i++) {
            lines.add("{\"id\":" + i + ",\"name\":\"汪茂雄\",\"url\":\"http://www.bejson.com\"page\":88,\"isNonProfit\":true}");
        }
        FileUtils.writeLines(file, "UTF-8", lines, true);
        System.out.println("写入文件：" + file.getAbsoluteFile());
    }

    /**
     * long sizeOf(final File file)
     * long sizeOfDirectory(final File directory)
     * sizeOf 可以统计文件大小，也可以统计目录大小，单位为 字节，如果文件或者目录不存在，则报错
     * sizeOfDirectory 只统计目录的大小，单位为 字节，如果是文件则报错
     * <p>
     * String byteCountToDisplaySize(final long size)
     * String byteCountToDisplaySize(final BigInteger size)
     * 将文件字节大小转为可视化的 KB、MB、GB 等形式的字符串，一共有：bytes、KB、MB、GB、TB、PB、EB.
     *
     * @throws IOException
     */
    @Test
    public void sizeOf() {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write4.txt");

        Long sizeFile = FileUtils.sizeOf(file);
        Long sizeDirectory = FileUtils.sizeOf(file.getParentFile());
        Long sizeOfDirectory = FileUtils.sizeOfDirectory(file.getParentFile());

        //2582,2661,2661
        System.out.println(sizeFile + "," + sizeDirectory + "," + sizeOfDirectory);

        String size1Show = FileUtils.byteCountToDisplaySize(sizeFile);
        String size2Show = FileUtils.byteCountToDisplaySize(sizeDirectory);
        String size3Show = FileUtils.byteCountToDisplaySize(sizeOfDirectory);

        //2 KB,2 KB,2 KB
        System.out.println(size1Show + "," + size2Show + "," + size3Show);
    }

    /**
     * copyDirectory(final File srcDir, final File destDir)
     * copyDirectory(final File srcDir, final File destDir,final boolean preserveFileDate)
     * copyDirectory(final File srcDir, final File destDir,final FileFilter filter)
     * copyDirectory(final File srcDir, final File destDir,final FileFilter filter, final boolean preserveFileDate)
     * 将指定目录下所有子孙目录和文件复制到指定的目标目录下，如果目标目录不存在，则创建该目录。如果目标目录确实存在，则此方法将源目录与目标目录合并，源目录优先。注意只能是目录，如果是文件则异常。
     *
     * @throws IOException
     */
    @Test
    public void copyDirectory() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File srdDir = new File(homeDirectory, "wmx");
        File destDir = new File(homeDirectory, "wmx_copy");
        FileUtils.copyDirectory(srdDir, destDir);
        System.out.println("复制目录：" + destDir.getAbsoluteFile());
    }

    /**
     * copyToDirectory(final File src, final File destDir)
     * copyToDirectory(final Iterable<File> srcs, final File destDir)
     * 将源文件或目录及其所有内容复制到指定目标目录中同名的目录中。如果目标目录不存在，则创建该目录。如果目标目录确实存在，则此方法将源目录与目标目录合并，源目录优先。
     *
     * @throws IOException
     */
    @Test
    public void copyToDirectory() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File srdDir = new File(homeDirectory, "wmx");
        File destDir = new File(homeDirectory, "wmx_copy");
        FileUtils.copyToDirectory(srdDir, destDir);
        System.out.println("复制目录：" + destDir.getAbsoluteFile());
    }

    /**
     * copyURLToFile(final URL source, final File destination)
     * copyURLToFile(final URL source, final File destination,final int connectionTimeout, final int readTimeout)
     * 将 URL 网络资源复制到目标文件中，可以用于下载，未设置超时时间时，可能出现永久阻塞
     * connectionTimeout：连接超时时间，单位毫秒
     * readTimeout：读取超时时间，单位毫秒
     *
     * @throws IOException
     */
    @Test
    public void copyURLToFile() throws IOException {
        URL url = new URL("https://avatar.csdnimg.cn/6/D/4/2_wangmx1993328.jpg");

        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/1.jpg");

        FileUtils.copyURLToFile(url, file, 60 * 1000, 60 * 1000);
        System.out.println("复制目录：" + file.getAbsoluteFile());
    }

    /**
     * LineIterator lineIterator(final File file)
     * LineIterator lineIterator(final File file, final String encoding)
     * 为文件打开一个 InputStream 的行迭代器，完成迭代器之后，应该关闭流以释放内部资源。
     * LineIterator implements Iterator：可以很方便的一行一行读取文件内容
     *
     * @throws IOException
     */
    @Test
    public void lineIterator() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write4.txt");
        LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
        int i = 1;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            System.out.println((i++) + "：" + next);
        }
        //关闭底层{@code Reader}。如果只想处理较大文件的前几行，则此方法非常有用。
        // 如果不关闭迭代器，则{@code Reader}仍然存在打开。这个方法可以安全地调用多次。
        lineIterator.close();
    }

    /**
     * Collection<File> listFiles(final File directory, final String[] extensions, final boolean recursive)
     * 查找给定目录（及其子目录）中与扩展名数组匹配的文件。
     * directory 要搜索的目录
     * extensions：要过滤的扩展数组，例如{“java”、“xml”}，为 null，返回所有文件。
     * ecursive：true 表示搜索所有子目录
     */
    @Test
    public void listFiles() {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx");
        Collection<File> fileCollection = FileUtils.listFiles(file, null, true);
        for (File file1 : fileCollection) {
            System.out.println(file1.getAbsoluteFile());
        }
    }

    /**
     * String readFileToString(final File file, final String encoding)
     * String readFileToString(final File file, final Charset encoding)
     * String readFileToString(final File file)
     * <p>
     * 将文件的内容读入字符串。文件始终处于关闭状态。
     * file：要读取的文件，不能是 null
     * encoding:要使用的编码，null表示使用平台默认值
     *
     * @throws IOException
     */
    @Test
    public void readFileToString() throws IOException {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(homeDirectory, "wmx/write4.txt");
        String fileToString = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        System.out.println(fileToString);
    }
}