package com.hiberus.model;

import com.hiberus.exception.VideoNotValidException;
import com.hiberus.videoEnum.avro.Category;
import com.hiberus.videoEnum.avro.Format;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VideoTest {
    private static final String VIDEO_IDENTIFIER = "VIDEO-1";
    private static final String CREATOR_IDENTIFIER = "CREATOR-1";
    private static final String TITLE = "Video-1";
    private static final String DURATION = "01:23:12";
    private static final LocalDate UPLOAD_DATE = LocalDate.parse("2017-02-15");
    private static final Format FORMAT = Format.MP4;
    private static final List<Category> CATEGORIES = Arrays.asList(Category.ANIMATION, Category.COMEDY);
    private static final String DESCRIPTION = "Description of video";

    @Test
    public void videoIdentifierShouldNotBeNull() {
        // Given & When
        Video video = new Video(null, CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void videoIdentifierShouldNotBeEmpty() {
        // Given & When
        Video video = new Video("", CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "ddhdh", "293848", "ddhh3838DDH3", "VIDEO", "VIDEO-"}
    )
    public void videoIdentifierShouldFollowPattern(String videoIdentifier) {
        // When
        Video video = new Video(videoIdentifier, CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void creatorIdentifierShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, null, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void creatorIdentifierShouldNotBeEmpty() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, "", TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "ddhdh", "293848", "ddhh3838DDH3", "CREATOR", "CREATOR-"}
    )
    public void creatorIdentifierShouldFollowPattern(String creatorIdentifier) {
        // When
        Video video = new Video(VIDEO_IDENTIFIER, creatorIdentifier, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void titleShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, null, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void titleShouldNotBeEmpty() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, "", DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void durationShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, null, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void durationShouldNotBeEmpty() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, "", UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "3737", "ddjdjd", ":29:30", "70:30", "30:100", "02:100:30", "02:30:75"
    })
    public void durationShouldFollowPattern(String duration) {
        // When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, duration, UPLOAD_DATE, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void uploadDateShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, DURATION, null, FORMAT, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void formatShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, null, CATEGORIES, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void categoriesShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, FORMAT, null, DESCRIPTION);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void descriptionShouldNotBeNull() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, null);

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

    @Test
    public void descriptionShouldNotBeEmpty() {
        // Given & When
        Video video = new Video(VIDEO_IDENTIFIER, CREATOR_IDENTIFIER, TITLE, DURATION, UPLOAD_DATE, FORMAT, CATEGORIES, "");

        // Then
        assertThrows(VideoNotValidException.class, video::validVideo);
    }

}
