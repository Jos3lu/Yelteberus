package com.hiberus.service;

import com.hiberus.utils.Consumer;
import com.hiberus.utils.Producer;
import com.hiberus.videoAggregator.avro.Video;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoEnum.avro.Category;
import com.hiberus.videoEnum.avro.Format;
import com.hiberus.videoEnum.avro.Privacy;
import com.hiberus.videoEnum.avro.Resolution;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@RunWith(SpringRunner.class)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class VideoAggregatorServiceTest {

    @Autowired
    Producer producer;

    @Autowired
    Consumer consumer;

    @Test
    public void shouldAggregateVideos() {
        // Given
        VideoKey videoKey = VideoKey.newBuilder().setVideoIdentifier("VIDEO-1")
                .setCreatorIdentifier("CREATOR-1").build();
        VideoPcsValue videoPcsValue = VideoPcsValue.newBuilder().setVideoIdentifier("VIDEO-1")
                .setTitle("Title").setDuration("22:20").setFormat(Format.AVI).setViews(37373)
                .setUploadDate(LocalDate.now()).setCategories(List.of(Category.ANIME))
                .setResolution(Resolution.HD).setPrivacy(Privacy.PUBLIC).setDescription("Description").build();
        Video video = Video.newBuilder().setVideoIdentifier("VIDEO-1")
                .setTitle("Title").setDuration("22:20").setFormat(Format.AVI).setViews(37373)
                .setUploadDate(LocalDate.now()).setCategories(List.of(Category.ANIME))
                .setResolution(Resolution.HD).setPrivacy(Privacy.PUBLIC).setDescription("Description").build();

        VideoAggregatorKey videoAggregatorKey = VideoAggregatorKey.newBuilder()
                .setCreatorIdentifier("CREATOR-1").build();
        VideoAggregatorValue videoAggregatorValue = VideoAggregatorValue.newBuilder()
                .setVideos(List.of(video)).build();

        // When
        producer.send("videos-pcs", videoKey, videoPcsValue);

        // Then
        assertThat(consumer.getVideoAggregatorKey()).isEqualTo(videoAggregatorKey);
        assertThat(consumer.getVideoAggregatorValue()).isEqualTo(videoAggregatorValue);
    }

}
