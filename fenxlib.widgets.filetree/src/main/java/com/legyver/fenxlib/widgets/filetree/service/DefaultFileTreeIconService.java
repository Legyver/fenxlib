package com.legyver.fenxlib.widgets.filetree.service;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;

import java.io.File;

/**
 * Provide the default icons for the file tree
 */
public class DefaultFileTreeIconService implements FileTreeIconService {

    @Override
    public int order() {
        return 0;
    }

    /**
     * Get the default icon font family
     * @return the default font family (icomoon)
     */
    protected String getDefaultIconFontFamily() {
        return "icomoon";
    }

    /**
     * Get the default icon color
     * @return the icon color (blueish)
     */
    protected String getDefaultIconColor() {
        return "3a73cf";
    }

    /**
     * Get the default icon size
     * @return the default icon size (18.0)
     */
    protected double getDefaultIconSize() {
        return 18.0;
    }

    /**
     * Get the default icon options with
     * {@link #getDefaultIconFontFamily()}
     * {@link #getDefaultIconColor()}
     * {@link #getDefaultIconSize()}
     * @return default builder with above options specified
     */
    protected IconOptions.Builder getDefaultIconOptionsBuilder() {
        return new IconOptions.Builder()
                .family(getDefaultIconFontFamily())
                .iconColor(getDefaultIconColor())
                .iconSize(getDefaultIconSize());
    }

    /**
     * Get the default icon to use if the file-type is indeterminable
     * @return the default icon (file-empty)
     */
    protected String getDefaultIconForUnknownFile() {
        return "file-empty";
    }

    @Override
    public IconOptions getCodeForDirectory(IFileReference fileReference) {
        return getDefaultIconOptionsBuilder()
                .icon("folder")
                .build();
    }

    @Override
    public IconOptions getCodeForFile(IFileReference fileReference) {
        IconOptions result;
        if (fileReference.getFile().isDirectory()) {
            result = getCodeForDirectory(fileReference);
        } else if (fileReference.getFile() != null) {
            String fileExtension = getFileExtension(fileReference.getFile());
            result = lookupByFileExtension(fileExtension);
        } else {
            result = getDefaultIconOptionsBuilder()
                    .icon(getDefaultIconForUnknownFile())
                    .build();
        }
        return  result;
    }

    /**
     * Check if a file is a text file
     * @param fileExtension the file extension for the file
     * @return true if the file extension matches a known text file type
     */
    protected boolean isTextFile(String fileExtension) {
        boolean result;
        switch (fileExtension) {
            case ".txt":
                result = true;
                break;
            default:
                result = false;
        }
        return result;
    }

    /**
     * Check if a file is an image file
     * @param fileExtension the file extension for the file
     * @return true if the file extension matches a known image file type
     */
    protected boolean isImageFile(String fileExtension) {
        boolean result;
        switch (fileExtension) {
            case ".jpg" :
            case ".jpeg" :
            case ".png" :
            case ".gif" :
            case ".psd" :
            case ".eps" :
            case ".ai" :
            case ".indd" :
            case ".raw" :
                result = true;
                break;
            default:
                result = false;
        }
        return result;
    }

    /**
     * Check if a file is an audio file
     * @param fileExtension the file extension for the file
     * @return true if the file extension matches a known audio file type
     */
    protected boolean isAudioFile(String fileExtension) {
        boolean result;
        switch (fileExtension) {
            case ".wav":
            case ".mp3":
            case ".aac":
            case ".flac":
            case ".alac":
            case ".aiff":
            case ".dsd":
            case ".pcm":
                result = true;
                break;
            default:
                result = false;
        }
        return result;
    }

    /**
     * Check if a file is a video file
     * @param fileExtension the file extension for the file
     * @return true if the file extension matches a known video file type
     */
    protected boolean isVideoFile(String fileExtension) {
        boolean result;
        switch (fileExtension) {
            case ".mp4":
            case ".mov":
            case ".wmv":
            case ".avi":
            case ".avchd":
            case ".flv":
            case ".f4v":
            case ".swf":
            case ".mkv":
            case ".webm":
            case ".mpeg":
            case ".mpg":
                result = true;
                break;
            default:
                result = false;
        }
        return result;
    }

    /**
     * Check if a file is a compressed file
     * @param fileExtension the file extension for the file
     * @return true if the file extension matches a known compressed file type
     */
    protected boolean isCompressedFile(String fileExtension) {
        boolean result;
        switch (fileExtension) {
            case ".zip":
            case ".7z":
            case ".gz":
            case ".tar":
            case ".rar":
            case ".war":
            case ".ear":
                result = true;
                break;
            default:
                result = false;
        }
        return result;
    }

    /**
     * Lookup icon options by the file extension
     * @param fileExtension the file extension to base the icon options on
     * @return the appropriate icon options if the file extension is known, or IconOptions with {@link #getDefaultIconForUnknownFile()} if not
     */
    protected IconOptions lookupByFileExtension(String fileExtension) {
        IconOptions result = null;
        if (fileExtension == null) {
            result = getDefaultIconOptionsBuilder()
                    .icon(getDefaultIconForUnknownFile())
                    .build();
        } else {
            String extLC = fileExtension.toLowerCase();
            if (isTextFile(extLC)) {
                result = getDefaultIconOptionsBuilder()
                        .icon("file-text2")
                        .build();
            } else if (isImageFile(extLC)) {
                result = getDefaultIconOptionsBuilder()
                        .icon("file-picture")
                        .build();
            } else if (isAudioFile(extLC)) {
                result = getDefaultIconOptionsBuilder()
                        .icon("file-music")
                        .build();
            } else if (isVideoFile(extLC)) {
                result = getDefaultIconOptionsBuilder()
                        .icon("file-video")
                        .build();
            } else if (isCompressedFile(extLC)) {
                result = getDefaultIconOptionsBuilder()
                        .icon("file-zip")
                        .build();
            }
        }
        return result;
    }

    /**
     * Extract the file extension from the file
     * @param file the file to get the extension of
     * @return the file extension if any
     */
    protected String getFileExtension(File file) {
        String fileExtension = null;
        String fileName = file.getName();
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0) {
            fileExtension = fileName.substring(lastDot);
        }
        return fileExtension;
    }

}
