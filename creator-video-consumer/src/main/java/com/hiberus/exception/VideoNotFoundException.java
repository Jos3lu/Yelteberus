package com.hiberus.exception;

public class VideoNotFoundException extends Exception {
    public VideoNotFoundException(String videoId) {
        super("Video not found: " + videoId);
    }
}
