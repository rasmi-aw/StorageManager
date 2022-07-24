package com.blacksoft.storagemanager.utils;

/**
 * Possible MimeTypes
 */
public enum MimeType {
    APPLICATION_ATOM_XML("application/atom+xml"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON("application/json"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    APPLICATION_SVG_XML("application/svg+xml"),
    APPLICATION_XHTML_XML("application/xhtml+xml"),
    APPLICATION_XML("application/xml"),
    APPLICATION_JSON_GITHUB("application/vnd.github.v3+json"),
    APPLICATION_ANDROID("application/vnd.android.package-archive"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    MEDIA_TYPE_WILDCARD("*"),
    WILDCARD("*/*"),
    ZIP("application/zip"),
    RAR("application/x-rar-compressed"),
    JAR_FILE("application/java-archive"),
    TAR("application/x-tar"),
    XLS("application/vnd.ms-excel"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    //
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    TEXT_CSS("text/css"),
    TEXT_CSV("text/csv"),
    TEXT_ICS("text/calendar"),
    //
    IMAGE_APNG("image/apng"),
    IMAGE_AVIF("image/avif"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_SVG("image/svg+xml"),
    IMAGE_WEBP("image/webp"),
    IMAGE_BITMAP("image/bmp"),
    IMAGE_IEF("image/ief"),
    IMAGE_PIPEG("image/pipeg"),
    IMAGE_TIFF("image/tiff"),
    IMAGE_ICON("image/x-icon"),
    //
    AUDIO_FLAC("audio/flac"),
    AUDIO_M3U("audio/mpegurl"),
    AUDIO_M4B("audio/mp4"),
    AUDIO_MP3("audio/mpeg"),
    AUDIO_OGG("audio/ogg"),
    AUDIO_PLS("audio/x-scpls"),
    AUDIO_WAV("audio/wav"),
    AUDIO_AAC("audio/aac"),
    AUDIO_WEBM("audio/webm"),
    AUDIO_WMA("audio/x-ms-wma"),
    AUDIO_XSPF("application/xspf+xml"),
    AUDIO_AU("audio/basic"),
    AUDIO_MID("audio/mid"),
    AUDIO_3G2("audio/3gpp"),
    //
    VIDEO_FLV("video/x-flv"),
    VIDEO_MP4("video/mp4"),
    VIDEO_3GP("video/3gpp"),
    VIDEO_MOV("video/quicktime"),
    VIDEO_AVI("video/x-msvideo"),
    VIDEO_WMV("video/x-ms-wmv"),
    VIDEO_MPEG("video/mpeg"),
    VIDEO_OGV("video/ogg"),
    VIDEO_WEBM("video/webm");

    /**
     * Constructor and value
     */
    private final String value;

    MimeType(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }


}
