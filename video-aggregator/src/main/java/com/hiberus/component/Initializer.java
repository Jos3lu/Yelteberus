package com.hiberus.component;

import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Initializer implements org.apache.kafka.streams.kstream.Initializer<VideoAggregatorValue> {

    @Override
    public VideoAggregatorValue apply() {
        return VideoAggregatorValue.newBuilder()
                .setVideos(new ArrayList<>())
                .build();
    }

}
