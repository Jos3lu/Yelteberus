package com.hiberus.service;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
import com.hiberus.utils.Consumer;
import com.hiberus.utils.Producer;
import com.hiberus.videoAggregator.avro.Video;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoEnum.avro.Category;
import com.hiberus.videoEnum.avro.Format;
import com.hiberus.videoEnum.avro.Privacy;
import com.hiberus.videoEnum.avro.Resolution;
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
public class CreatorVideoMixbiServiceTest {

    @Autowired
    private Producer producer;

    @Autowired
    Consumer consumer;

    @Test
    public void shouldJoinVideos() {
        // Given
        CreatorKey creatorKey = CreatorKey.newBuilder().setCreatorIdentifier("CREATOR-1").build();
        CreatorValue creatorValue = CreatorValue.newBuilder().setCreatorIdentifier("CREATOR-1")
                .setName("John").setSurname("Smith").setBirth(LocalDate.now())
                .setEmail("john@gmail.com").setPhone("+34 384 47 49 59").build();

        VideoAggregatorKey videoAggregatorKey = VideoAggregatorKey.newBuilder()
                .setCreatorIdentifier("CREATOR-1").build();
        Video video = Video.newBuilder().setVideoIdentifier("VIDEO-1")
                .setTitle("Title").setDuration("22:20").setFormat(Format.AVI).setViews(37373)
                .setUploadDate(LocalDate.now()).setCategories(List.of(Category.ANIME))
                .setResolution(Resolution.HD).setPrivacy(Privacy.PUBLIC).setDescription("Description").build();
        VideoAggregatorValue videoAggregatorValue = VideoAggregatorValue.newBuilder()
                .setVideos(List.of(video)).build();

        CreatorVideoKey creatorVideoKey = CreatorVideoKey.newBuilder()
                .setCreatorIdentifier("CREATOR-1").build();
        CreatorVideoValue creatorVideoValue = CreatorVideoValue.newBuilder()
                .setCreatorIdentifier("CREATOR-1").setName("John").setSurname("Smith")
                .setVideos(List.of(video)).build();

        // When
        producer.sendCreator("creators", creatorKey, creatorValue);
        producer.sendVideo("videos-aggregator", videoAggregatorKey, videoAggregatorValue);

        // Then
        assertThat(consumer.getCreatorVideoKey()).isEqualTo(creatorVideoKey);
        assertThat(consumer.getCreatorVideoValue()).isEqualTo(creatorVideoValue);
    }

}
