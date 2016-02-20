package com.nbf.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

/**
 * User: Administrator
 * Date: 16-2-9
 * Time: 下午8:54
 */
public class FileTest {

    @Test
    public void testFile(){
        String filename = "/test-hdfs-folder/ff.txt";
        System.out.println(FilenameUtils.getFullPathNoEndSeparator(filename));
        System.out.println(FilenameUtils.getBaseName(filename));
        System.out.println(FilenameUtils.getExtension(filename));
        System.out.println(FilenameUtils.getName(filename));
    }

}
