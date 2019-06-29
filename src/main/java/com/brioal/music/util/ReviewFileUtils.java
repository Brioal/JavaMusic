package com.brioal.music.util;


import com.brioal.music.bean.tool.FileBean;
import com.brioal.music.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2018/3/13.
 */

public class ReviewFileUtils {
    // 记录器
    public static Logger logger = LoggerFactory.getLogger(ReviewFileUtils.class);


    /**
     * 根据文件获取相对于Qixiu的相对路径
     *
     * @param file
     * @return
     */
    public static String getRelativePath(File file) {
        logger.info("文件保存,路径转换:");
        if (file == null) {
            return null;
        }
        String originalFileName = file.getAbsolutePath();
        originalFileName = originalFileName.replaceAll("\\\\", "\\/");
        logger.info("文件绝对路径:" + originalFileName);
        if (TextUtil.isStringError(originalFileName)) {
            return null;
        }
        // 切割文件路径
        String[] strs = originalFileName.split("Qixiu");
        if (strs == null) {
            return null;
        }
        if (strs.length < 2) {
            return null;
        }
        logger.info("转换之后的相对路径:" + strs[1]);
        // 获取正确的路径
        return strs[1];
    }

    /**
     * 将上传的文件保存普通文件并且返回文件实体
     *
     * @param file
     * @param
     */
    public static File saveRegularFileAndReturnPath(MultipartFile file) {
        if (file == null) {
            return null;
        }
        logger.info("将上传的文件保存普通文件并且返回文件实体:");
        // 文件类型
        String dot = getFileDot(file);
        logger.info("文件类型:" + dot);
        // 获取要存储的目标文件
        File targetFile = getRegularFormatFile(dot);
        logger.info("目标文件路径:" + targetFile.getAbsolutePath());
        // 保存文件
        try {
            FileCopyUtils.copy(file.getBytes(), targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回文件
        return targetFile;
    }


    /**
     * 返回文件的名称
     *
     * @param file
     * @return
     */
    public static String getFileName(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (!TextUtil.isStringAvailable(originalName)) {
            return null;
        }
        // 获取后缀
        String dot = originalName.substring(originalName.lastIndexOf("."));
        // 文件名
        String result = System.currentTimeMillis() + dot;
        return result;
    }


    /**
     * 返回带后缀的文件名称
     *
     * @return
     */
    public static String getFileNameWithDot(String fileType) {
        return getFileNameCode() + "." + fileType;
    }

    /**
     * 返回上传文件的编码之后的文件名称
     *
     * @param file
     * @return
     */
    public static String getFileNameWithDotByMultFile(MultipartFile file) {
        if (file == null) {
            return null;
        }
        return getFileNameCode() + "." + getFileDot(file);
    }

    /**
     * 返回一个新的pdf的文件
     *
     * @return
     */
    public static File getNewPdfFile() {
        // 文件名
        return getRegularFormatFile("pdf");
    }

    /**
     * 返回普通文件
     * 指定后缀
     *
     * @param format
     * @return
     */
    public static File getRegularFormatFile(String format) {
        logger.info("生成指定类型的文件路径:" + format);
        // 文件名
        String fileName = getFileNameCode() + "." + format;
        // 文件日期目录
        String dataPath = QixiuDateFormatUtl.getTodayDateForFilePath();
        // 文件的完整目录 项目路径/普通文件的路径/日期路径/类型路径
        String fullPath = Config.PROJECT_DIR + File.separator + dataPath + File.separator + format;
        File dirs = new File(fullPath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        String filePath = fullPath + File.separator + fileName;
        logger.info("生成结果为:" + filePath);
        File result = new File(filePath);
        return result;
    }

    /**
     * 返回文件的后缀名
     *
     * @param file
     * @return
     */
    public static String getFileDot(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        if (TextUtil.isStringError(fileName)) {
            return null;
        }
        String dot = fileName.substring(fileName.lastIndexOf(".") + 1);
        return dot;
    }

    /**
     * 返回pdf的文件名称
     *
     * @return
     */
    public static String getFileNamePDF() {
        return getFileNameWithDot("pdf");
    }

    /**
     * 返回文件名的编码结果
     *
     * @return
     */
    private static String getFileNameCode() {
        // 时间格式
        String time = QixiuDateFormatUtl.formatDate(new Timestamp(System.currentTimeMillis()), "yyyy_MM_dd_HH_mm_ss");
        // 随机字符串
        return time + "_" + RandomUtil.randomFileNameAppend();
    }

    /**
     * 根据文件名,返回完整路径的文件
     *
     * @param fileName
     * @return
     */
    private static File getFileByFileName(String fileName) {
        // 文件夹名称
        String date = QixiuDateFormatUtl.getTodayDateForFilePath();
        File root = new File(Config.PROJECT_DIR + File.separator + date);
        if (!root.exists()) {
            root.mkdirs();
        }
        // 返回包含路径,文件名的File实体
        return new File(root + File.separator + fileName);
    }

    /**
     * 返回要保存的文件,只设置路径和文件名
     *
     * @param dot 传入要生成的文件的后缀
     * @return
     */
    public static File getFile(String dot) {
        // 文件名
        String result = System.currentTimeMillis() + dot;
        return getFileByFileName(result);
    }


    /**
     * 返回filebean的真实路径
     *
     * @param file
     * @return
     */
    public static String getAbsolutePathForFileBean(FileBean file) {
        if (file == null) {
            return null;
        }
        return Config.getFullLocalPath(file.getPath());
    }
}
