package com.hiberus.model;

import com.hiberus.exception.VideoNotValidException;
import com.hiberus.videosProducer.avro.Category;
import com.hiberus.videosProducer.avro.Format;
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

    private static final Pattern PATTERN_DURATION = Pattern
            .compile("^(?:.*\\d:)?[0-5]\\d:[0-5]\\d$");

    private Long identifier;
    private String title;
    private String duration;
    private LocalDate uploadDate;
    private Format format;
    protected List<Category> categories;
    private String description;
    private Long creatorIdentifier;

    public void validVideo() throws VideoNotValidException {
        if (incompleteFields() || negativeIdentifier() || invalidDuration())
            throw new VideoNotValidException();
    }

    private boolean incompleteFields() {
        return identifier == null || title.isBlank() || duration.isBlank() || uploadDate == null ||
                format == null || categories == null || description.isBlank() || creatorIdentifier == null;
    }

    private boolean negativeIdentifier() {
        return identifier < 0;
    }

    private boolean invalidDuration() {
        return !PATTERN_DURATION.matcher(this.duration).find();
    }

}
