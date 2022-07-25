package com.blacksoft.storagemanager.utils;


import com.blacksoft.storagemanager.config.StorageConfig;

import java.io.File;
import java.util.Date;
import java.util.Random;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * This class holds basic utils related to files
 */

public final class StorageUtils {

    /**
     * Giving files random names based on some inputs
     *
     * @param prefix)   file name prefix
     * @param fileType) {@link FileType}
     */
    public static String randomName(String prefix,
                                    FileType fileType) {
        if (prefix == null || prefix.isEmpty()) prefix = "not_prefixed";

        String typePrefix = "other_files";

        if (fileType == FileType.HTML || fileType == FileType.CSS)
            typePrefix = "web_file";

        else if (fileType == FileType.MICROSOFT_EXCEL || fileType == FileType.MICROSOFT_WORD
                || fileType == FileType.MICROSOFT_POWERPOINT)
            typePrefix = "microsoft_office";
        else if (fileType == FileType.SQL_DATABASE) typePrefix = "database";
        else if (fileType == FileType.ANDROID_APPLICATION) typePrefix = "android_application";
        else if (fileType == FileType.JAR) typePrefix = "jar";
        else if (fileType == FileType.IMAGE) typePrefix = "picture";
        else if (fileType == FileType.VIDEO) typePrefix = "movie";
        else if (fileType == FileType.AUDIO) typePrefix = "audio";
        else if (fileType == FileType.PDF) typePrefix = "pdf";
        else if (fileType == FileType.TEXT) typePrefix = "text";
        else if (fileType == FileType.XML) typePrefix = "xml";
        

    //
    long time = new Date().getTime();
    Random random = new Random();


        return(prefix +"_"+typePrefix +"_"+time +"_"+
            random.nextLong()+"_"+(random.nextLong()+"_"+
    time *random.nextInt()+"_"+random.nextLong()));
}

    /**
     * Giving files random names based on the mimeType
     *
     * @param prefix)   file name prefix
     * @param mimeType) http header value (Content-type) for ex) image/png...
     */
    public static String randomNameWithExtension(String prefix,
                                                 MimeType mimeType) {
        FileType fileType = StorageUtils.guessFileType(mimeType.toString());
        return randomName(prefix, fileType)
                + StorageUtils.guessFileExtension(mimeType);

    }

    /**
     * Giving file random name based on default configs like app name
     *
     * @param fileType) {@link FileType}
     */

    public static final String defaultRandomName(FileType fileType) {
        return randomName(StorageConfig.LIBRARY_NAME, fileType);
    }


    /**
     * Guessing the file type from its name or from a string containing its type
     *
     * @param path) file path
     * @return short integer file type
     */
    public static FileType guessFileType(String path) {

        if (path == null || path.isEmpty()) return FileType.NOT_A_FILE;

        String lCPath = path.trim().toLowerCase();

        if (lCPath.contains("image/") || lCPath.contains(".webp") || lCPath.contains(".png")
                || lCPath.contains(".jpg") || lCPath.contains(".jpeg")
                || lCPath.contains(".gif") || lCPath.contains(".bmp")
                || lCPath.contains(".gifv") || lCPath.contains(".apng")
                || lCPath.contains(".avif") || lCPath.contains(".jfif")
                || lCPath.contains(".pjpeg") || lCPath.contains(".pjp")
                || lCPath.contains(".svg") || lCPath.contains(".ico")
                || lCPath.contains(".cur") || lCPath.contains(".tif")
        ) return FileType.IMAGE;

        else if (lCPath.contains("video/") || lCPath.contains(".mp4") || lCPath.contains(".mpg")
                || lCPath.contains(".mpeg") || lCPath.contains(".3gp")
                || lCPath.contains(".mkv") || lCPath.contains(".webm")
                || lCPath.contains(".flv") || lCPath.contains(".vob")
                || lCPath.contains(".ogv") || lCPath.contains(".ovv")
                || lCPath.contains(".drc") || lCPath.contains(".f4b")
                || lCPath.contains(".mnv") || lCPath.contains(".avi")
                || lCPath.contains("ts") || lCPath.contains(".mov")
                || lCPath.contains(".qt") || lCPath.contains(".wmv")
                || lCPath.contains(".yuv")
                || lCPath.contains(".viv") || lCPath.contains(".asf")
                || lCPath.contains(".amv")
                || lCPath.contains(".m4v") || lCPath.contains(".svi")
                || lCPath.contains(".3g2") || lCPath.contains(".mxf")
                || lCPath.contains(".roq") || lCPath.contains(".nsv")
                || lCPath.contains(".f4v") || lCPath.contains(".f4p")
                || lCPath.contains(".f4a")
        ) return FileType.VIDEO;


        else if (lCPath.contains("audio/") || lCPath.contains(".aa") || lCPath.contains(".act")
                || lCPath.contains(".aiff") || lCPath.contains(".alac")
                || lCPath.contains(".ape") || lCPath.contains(".amr")
                || lCPath.contains(".au") || lCPath.contains(".awb")
                || lCPath.contains(".dss") || lCPath.contains(".dvf")
                || lCPath.contains(".flac") || lCPath.contains(".gsm")
                || lCPath.contains(".iklax") || lCPath.contains(".ivs")
                || lCPath.contains(".m4a") || lCPath.contains(".m4b")
                || lCPath.contains(".m4p") || lCPath.contains(".mmf")
                || lCPath.contains(".mp3") || lCPath.contains(".mpc")
                || lCPath.contains(".msv") || lCPath.contains(".nmf")
                || lCPath.contains(".ogg") || lCPath.contains(".oga")
                || lCPath.contains(".mogg") || lCPath.contains(".opus")
                || lCPath.contains(".org") || lCPath.contains(".ra")
                || lCPath.contains(".rm") || lCPath.contains(".raw")
                || lCPath.contains(".rf64") || lCPath.contains(".sln")
                || lCPath.contains(".tta") || lCPath.contains(".voc")
                || lCPath.contains(".vox") || lCPath.contains(".wav")
                || lCPath.contains(".wma") || lCPath.contains(".wv")
                || lCPath.contains(".8svx") || lCPath.contains(".cda")

        ) return FileType.AUDIO;

        else if (lCPath.contains(".txt")) return FileType.TEXT;

        else if (lCPath.contains(".xls") || lCPath.contains(".xlt")
                || lCPath.contains(".xla") || lCPath.contains(".xlsx")
                || lCPath.contains(".xlsm")
                || lCPath.contains(".xltx") || lCPath.contains(".xltm")
                || lCPath.contains(".xlam")
        ) return FileType.MICROSOFT_EXCEL;

        else if (lCPath.contains(".doc") || lCPath.contains(".dot")
                || lCPath.contains(".wbk")
                || lCPath.contains(".docx") || lCPath.contains(".docm")
                || lCPath.contains(".dotx") || lCPath.contains(".dotm")
                || lCPath.contains(".docb")
        ) return FileType.MICROSOFT_WORD;

        else if (lCPath.contains(".ppt")) return FileType.MICROSOFT_POWERPOINT;

        else if (lCPath.contains(".pdf")) return FileType.PDF;

        else if (lCPath.contains(".htm")) return FileType.HTML;

        else if (lCPath.contains(".css")) return FileType.CSS;

        else if (lCPath.contains(".xml")) return FileType.XML;

        else if (lCPath.contains(".exe")) return FileType.WINDOWS_EXECUTABLE;

        else if (lCPath.contains(".lib")) return FileType.WINDOWS_EXTERNAL_LIBRARY;

        else if (lCPath.contains(".db") || lCPath.contains(".mdf")
                || lCPath.contains(".sdf")
        ) return FileType.SQL_DATABASE;

        else if (lCPath.equals("application/vnd.android.package-archive")
                || lCPath.contains(".apk")
                || lCPath.contains(".aab"))
            return FileType.ANDROID_APPLICATION;

        else if (lCPath.equals(MimeType.JAR_FILE)
                || lCPath.contains(".jar")) return FileType.JAR;

        else return FileType.OTHERS;
    }

    /**
     * guessing file type from it's name but the input here is a file
     *
     * @param file) your file
     */
    public static FileType guessFileType(File file) {
        String path;
        if (file == null || !file.exists()) path = null;
        else path = file.getName();
        return guessFileType(path);
    }

    /**
     * getting file extension from its name or its path
     */
    public static final String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()
                || !fileName.contains(".")) return "";

        String[] parts = fileName.replace(".", "##").split("##");
        return "." + parts[parts.length - 1];
    }

    /**
     * @param mimeType) a string containing the mime type of the file downloaded
     *                  from server for example image/jpeg
     * @return) String containing the file extension for example image/jpeg gives you (.jpg),
     * or an empty String in else if( fileType== mime is null or empty, or even (.bin) if the mime type is unknown.
     */
    public static String guessFileExtension(MimeType mimeType) {
        if (mimeType == null || mimeType.toString().isEmpty()) return ".bin";
        else {
            String lcMime = mimeType.toString().toLowerCase();
            String extension = "";

            /**
             *For Image Files
             */
            if (lcMime.contains(MimeType.IMAGE_APNG.toString())) extension = ".apng";
            else if (lcMime.contains(MimeType.IMAGE_AVIF.toString())) extension = ".avif";
            else if (lcMime.contains(MimeType.IMAGE_GIF.toString())) extension = ".gif";
            else if (lcMime.contains(MimeType.IMAGE_JPEG.toString())) extension = ".jpg";
            else if (lcMime.contains(MimeType.IMAGE_PNG.toString())) extension = ".png";
            else if (lcMime.contains(MimeType.IMAGE_SVG.toString())) extension = ".svg";
            else if (lcMime.contains(MimeType.IMAGE_WEBP.toString())) extension = ".webp";
            else if (lcMime.contains(MimeType.IMAGE_BITMAP.toString())) extension = ".bmp";
            else if (lcMime.contains(MimeType.IMAGE_IEF.toString())) extension = ".ief";
            else if (lcMime.contains(MimeType.IMAGE_PIPEG.toString())) extension = ".pipeg";
            else if (lcMime.contains(MimeType.IMAGE_TIFF.toString())) extension = ".tiff";
            else if (lcMime.contains(MimeType.IMAGE_ICON.toString())) extension = ".ico";

            /**
             *For Audio Files
             */
            else if (lcMime.contains(MimeType.AUDIO_FLAC.toString())) extension = ".flac";
            else if (lcMime.contains(MimeType.AUDIO_M3U.toString())) extension = ".m3u";
            else if (lcMime.contains(MimeType.AUDIO_M4B.toString())) extension = ".m4b";
            else if (lcMime.contains(MimeType.AUDIO_MP3.toString())) extension = ".mp3";
            else if (lcMime.contains(MimeType.AUDIO_OGG.toString())) extension = ".ogg";
            else if (lcMime.contains(MimeType.AUDIO_PLS.toString())) extension = ".pls";
            else if (lcMime.contains(MimeType.AUDIO_WAV.toString())) extension = ".wav";
            else if (lcMime.contains(MimeType.AUDIO_AAC.toString())) extension = ".aac";
            else if (lcMime.contains(MimeType.AUDIO_WEBM.toString())) extension = ".webm";
            else if (lcMime.contains(MimeType.AUDIO_WMA.toString())) extension = ".wma";
            else if (lcMime.contains(MimeType.AUDIO_AU.toString())) extension = ".au";
            else if (lcMime.contains(MimeType.AUDIO_MID.toString())) extension = ".mid";
            else if (lcMime.contains(MimeType.AUDIO_3G2.toString())) extension = ".3g2";
            else if (lcMime.contains(MimeType.AUDIO_XSPF.toString())) extension = ".xspf";


            /**
             *For Video Files
             */
            else if (lcMime.contains(MimeType.VIDEO_FLV.toString())) extension = ".flv";
            else if (lcMime.contains(MimeType.VIDEO_MP4.toString())) extension = ".mp4";
            else if (lcMime.contains(MimeType.VIDEO_3GP.toString())) extension = ".3gp";
            else if (lcMime.contains(MimeType.VIDEO_MOV.toString())) extension = ".mov";
            else if (lcMime.contains(MimeType.VIDEO_AVI.toString())) extension = ".avi";
            else if (lcMime.contains(MimeType.VIDEO_WMV.toString())) extension = ".wmv";
            else if (lcMime.contains(MimeType.VIDEO_MPEG.toString())) extension = ".mpeg";
            else if (lcMime.contains(MimeType.VIDEO_OGV.toString())) extension = ".ogv";
            else if (lcMime.contains(MimeType.VIDEO_WEBM.toString())) extension = ".webm";

            /**
             * for Android app
             */
            else if (lcMime.contains(MimeType.APPLICATION_ANDROID.toString()))
                extension = ".apk";

            /**
             *For Others
             */
            else if (lcMime.contains(MimeType.APPLICATION_XML.toString())) extension = ".xml";
            else if (lcMime.contains(MimeType.ZIP.toString())) extension = ".zip";
            else if (lcMime.contains(MimeType.JAR_FILE.toString())) extension = ".jar";
            else if (lcMime.contains(MimeType.RAR.toString())) extension = ".rar";
            else if (lcMime.contains(MimeType.TAR.toString())) extension = ".tar";
            else if (lcMime.contains(MimeType.XLS.toString())) extension = ".xls";
            else if (lcMime.contains(MimeType.XLSX.toString())) extension = ".xlsx";
            else if (lcMime.contains(MimeType.TEXT_HTML.toString())) extension = ".html";
            else if (lcMime.contains(MimeType.TEXT_CSS.toString())) extension = ".css";
            else if (lcMime.contains(MimeType.TEXT_ICS.toString())) extension = ".ics";
            else if (lcMime.contains(MimeType.TEXT_CSV.toString())) extension = ".csv";
            else if (lcMime.contains(MimeType.TEXT_XML.toString())) extension = ".xml";
            else if (lcMime.contains(MimeType.TEXT_PLAIN.toString())) extension = ".txt";
            else if (lcMime.contains(MimeType.APPLICATION_JSON.toString())) extension = ".json";
            else if (lcMime.contains(MimeType.APPLICATION_OCTET_STREAM.toString()))
                extension = ".bin";
            else if (lcMime.contains(MimeType.APPLICATION_XHTML_XML.toString()))
                extension = ".xhtml";

            else extension = ".bin";

            return extension;
        }
    }

    /**
     * Tells if this file is stored locally on this device or not
     */
    public static boolean isStoredLocally(String filePath) {
        if (filePath == null || filePath.isEmpty()) return false;
        File file = new File(filePath);
        return (file.exists());
    }

}