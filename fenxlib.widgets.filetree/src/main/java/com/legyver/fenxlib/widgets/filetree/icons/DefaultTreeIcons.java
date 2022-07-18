package com.legyver.fenxlib.widgets.filetree.icons;

import com.legyver.fenxlib.api.icons.application.IconAliasBuilder;
import com.legyver.fenxlib.api.icons.application.IconAliasMap;
import com.legyver.fenxlib.api.icons.options.IconOptions;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Predicate;

/**
 * Default tree icons.
 * - There are separate icons for an open folder and a closed folder
 * - There are separate icons for several file types
 *   - Word, Excel, PDF, Image, Video, Audio, Text, Zip (compressed)
 */
public class DefaultTreeIcons {

    /**
     * Name of the predicate testing if the Excel icon should be applied to the file
     */
    public static final String IS_EXCEL = "is_excel";
    /**
     * Name of the predicate testing if the Word icon should be applied to the file
     */
    public static final String IS_WORD = "is_word";
    /**
     * Name of the predicate testing if the PDF icon should be applied to the file
     */
    public static final String IS_PDF = "is_pdf";
    /**
     * Name of the predicate testing if the text icon should be applied to the file
     */
    public static final String IS_TEXT = "is_text";
    /**
     * Name of the predicate testing if the image icon should be applied to the file
     */
    public static final String IS_IMAGE = "is_image";
    /**
     * Name of the predicate testing if the audio icon should be applied to the file
     */
    public static final String IS_AUDIO = "is_audio";
    /**
     * Name of the predicate testing if the video icon should be applied to the file
     */
    public static final String IS_VIDEO = "is_video";
    /**
     * Name of the predicate testing if the zip icon should be applied to the file
     */
    public static final String IS_ZIP = "is_zip";

    /**
     * Get the default tree icons
     * @return the default tree icons
     */
    public static IconAliasBuilder defaultTreeIcons() {
        Double size = 12.0;
        Color manilla = (Color) Paint.valueOf("#e7c9a9");
        Color oracleRed = (Color) Paint.valueOf("#f80000");
        Color oracleBlack = (Color) Paint.valueOf("#000000");
        Color officeGreen = (Color) Paint.valueOf("#7FBA00");
        Color officeBlue = (Color) Paint.valueOf("#00A4EF");
        TreeIconOptionsFactory factory = new TreeIconOptionsFactory("icomoon", size);
        return IconAliasMap
                .given(TreeNodeType.DIRECTORY)
                    //when not open use the closed folder
                    .when((Predicate<Boolean>) aBoolean -> !aBoolean)
                        .thenUseIcon(factory.iconOptions(FolderIcons.ICON_FOLDER.getIconName(), manilla))
                    //when open use the open folder
                    .when((Predicate<Boolean>) aBoolean -> aBoolean)
                        .thenUseIcon(factory.iconOptions(FolderIcons.ICON_FOLDER_OPEN.getIconName(), manilla))
                .given(TreeNodeType.FILE)
                    .when(new FileExtensionPredicate(IS_EXCEL, ".xls|.xlsx"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_EXCEL.getIconName(), officeGreen))
                    .when(new FileExtensionPredicate(IS_WORD, ".doc|.docx"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_WORD.getIconName(), officeBlue))
                    .when(new FileExtensionPredicate(IS_PDF,".pdf"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_PDF.getIconName(), oracleRed))
                    .when(new FileExtensionPredicate(IS_TEXT,".txt|.text"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_TEXT.getIconName(), oracleRed))
                    .when(new FileExtensionPredicate(IS_IMAGE,".jpg|.jpeg|.png|.gif|.psd|.eps|.ai|.indd|.raw"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_PICTURE.getIconName(), oracleBlack))
                    .when(new FileExtensionPredicate(IS_AUDIO, ".wav|.mp3|.aac|.flac|.alac|.aiff|.dsd|.pcm"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_MUSIC.getIconName(), oracleBlack))
                    .when(new FileExtensionPredicate(IS_VIDEO,".mp4|.mov|.wmv|.avi|.avchd|.flv|.f4v|.swf|.mkv|.webm|.mpeg|.mpg"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_VIDEO.getIconName(), oracleBlack))
                    .when(new FileExtensionPredicate(IS_ZIP,".zip|.7z|.gz|.tar|.rar|.war|.ear"))
                        .thenUseIcon(factory.iconOptions(FileIcons.ICON_FILE_ZIP.getIconName(), oracleBlack))
                    .otherwise(factory.iconOptions(FileIcons.ICON_FILE_EMPTY.getIconName(), oracleBlack));
    }

    private static class TreeIconOptionsFactory {
        private final String defaultFamily;
        private final Double defaultSize;

        private TreeIconOptionsFactory(String defaultFamily, Double defaultSize) {
            this.defaultFamily = defaultFamily;
            this.defaultSize = defaultSize;
        }

        IconOptions iconOptions(String icon, Color color) {
            return new IconOptions.Builder<>()
                    .iconSize(defaultSize)
                    .family(defaultFamily)
                    .icon(icon)
                    .iconColor(color).build();
        }
    }
}
