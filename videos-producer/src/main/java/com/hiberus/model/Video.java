package com.hiberus.model;

import com.hiberus.exception.VideoNotValidException;
import com.hiberus.videosEnum.avro.Category;
import com.hiberus.videosEnum.avro.Format;
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

    private static final Pattern PATTERN_IDENTIFIER = Pattern
            .compile("^VIDEO-[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_DURATION = Pattern
            .compile("^(?:.*\\d:)?[0-5]\\d:[0-5]\\d$");
    private static final Pattern PATTERN_CREATOR_IDENTIFIER = Pattern
            .compile("^CREATOR-[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);

    private String identifier;
    private String title;
    private String duration;
    private LocalDate uploadDate;
    private Format format;
    protected List<Category> categories;
    private String description;
    private String creatorIdentifier;

    public void validVideo() throws VideoNotValidException {
        if (incompleteFields() || invalidIdentifier() || invalidDuration() || invalidCreatorIdentifier())
            throw new VideoNotValidException();
    }

    private boolean incompleteFields() {
        return identifier == null || title.isBlank() || duration.isBlank() || uploadDate == null ||
                format == null || categories == null || description.isBlank() || creatorIdentifier == null;
    }

    private boolean invalidIdentifier() {
        return !PATTERN_IDENTIFIER.matcher(identifier).find();
    }

    private boolean invalidDuration() {
        return !PATTERN_DURATION.matcher(this.duration).find();
    }

    private boolean invalidCreatorIdentifier() {
        return !PATTERN_CREATOR_IDENTIFIER.matcher(creatorIdentifier).find();
    }

}
