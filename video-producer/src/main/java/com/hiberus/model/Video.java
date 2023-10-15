package com.hiberus.model;

import com.hiberus.exception.VideoNotValidException;
import com.hiberus.videoEnum.avro.Category;
import com.hiberus.videoEnum.avro.Format;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

    private static final Pattern PATTERN_VIDEO_IDENTIFIER = Pattern
            .compile("^VIDEO-[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_DURATION = Pattern
            .compile("^(?:.*\\d:)?[0-5]\\d:[0-5]\\d$");
    private static final Pattern PATTERN_CREATOR_IDENTIFIER = Pattern
            .compile("^CREATOR-[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);

    private String videoIdentifier;
    private String creatorIdentifier;
    private String title;
    private String duration;
    private LocalDate uploadDate;
    private Format format;
    protected List<Category> categories;
    private String description;

    public void validVideo() throws VideoNotValidException {
        if (incompleteFields() || emptyFields() || invalidVideoIdentifier() || invalidCreatorIdentifier() || invalidDuration())
            throw new VideoNotValidException();
    }

    private boolean incompleteFields() {
        return videoIdentifier == null || creatorIdentifier == null || title == null || duration == null ||
                uploadDate == null || format == null || categories == null || description == null;
    }

    private boolean emptyFields() {
        return videoIdentifier.isBlank() || creatorIdentifier.isBlank() || title.isBlank() ||
                duration.isBlank() || description.isBlank();
    }

    private boolean invalidVideoIdentifier() {
        return !PATTERN_VIDEO_IDENTIFIER.matcher(videoIdentifier).find();
    }

    private boolean invalidCreatorIdentifier() {
        return !PATTERN_CREATOR_IDENTIFIER.matcher(creatorIdentifier).find();
    }

    private boolean invalidDuration() {
        return !PATTERN_DURATION.matcher(this.duration).find();
    }

}
