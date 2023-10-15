#!/bin/bash

CREATORS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-key)
CREATORS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-value)
CREATORS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-key?permanent=true)
CREATORS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-value?permanent=true)
echo $CREATORS_KEY_SOFT
echo $CREATORS_VALUE_SOFT
echo $CREATORS_VALUE_HARD
echo $CREATORS_KEY_HARD

CREATORS_DLQ_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-dlq-key)
CREATORS_DQL_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-dlq-value)
CREATORS_DLQ_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-dlq-key?permanent=true)
CREATORS_DQL_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-dlq-value?permanent=true)
echo $CREATORS_DLQ_KEY_SOFT
echo $CREATORS_DLQ_VALUE_SOFT
echo $CREATORS_DLQ_KEY_HARD
echo $CREATORS_DLQ_VALUE_HARD

VIDEOS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-key)
VIDEOS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-value)
VIDEOS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-key?permanent=true)
VIDEOS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-value?permanent=true)
echo $VIDEOS_KEY_SOFT
echo $VIDEOS_VALUE_SOFT
echo $VIDEOS_KEY_HARD
echo $VIDEOS_VALUE_HARD

VIDEOS_DLQ_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-dlq-key)
VIDEOS_DQL_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-dlq-value)
VIDEOS_DLQ_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-dlq-key?permanent=true)
VIDEOS_DQL_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-dlq-value?permanent=true)
echo $VIDEOS_DLQ_KEY_SOFT
echo $VIDEOS_DLQ_VALUE_SOFT
echo $VIDEOS_DLQ_KEY_HARD
echo $VIDEOS_DLQ_VALUE_HARD

VIDEOS_PCS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-key)
VIDEOS_PCS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-value)
VIDEOS_PCS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-key?permanent=true)
VIDEOS_PCS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-value?permanent=true)
echo $VIDEOS_PCS_KEY_SOFT
echo $VIDEOS_PCS_VALUE_SOFT
echo $VIDEOS_PCS_KEY_HARD
echo $VIDEOS_PCS_VALUE_HARD

VIDEOS_AGGREGATOR_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-aggregator-key)
VIDEOS_AGGREGATOR_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-aggregator-value)
VIDEOS_AGGREGATOR_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-aggregator-key?permanent=true)
VIDEOS_AGGREGATOR_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-aggregator-value?permanent=true)
echo $VIDEOS_AGGREGATOR_KEY_SOFT
echo $VIDEOS_AGGREGATOR_VALUE_SOFT
echo $VIDEOS_AGGREGATOR_KEY_HARD
echo $VIDEOS_AGGREGATOR_VALUE_HARD

VIDEOS_AGGREGATOR_CHANGELOG_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-changelog-key)
VIDEOS_AGGREGATOR_CHANGELOG_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-changelog-value)
VIDEOS_AGGREGATOR_CHANGELOG_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-changelog-key?permanent=true)
VIDEOS_AGGREGATOR_CHANGELOG_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-changelog-value?permanent=true)
echo $VIDEOS_AGGREGATOR_CHANGELOG_KEY_SOFT
echo $VIDEOS_AGGREGATOR_CHANGELOG_VALUE_SOFT
echo $VIDEOS_AGGREGATOR_CHANGELOG_KEY_HARD
echo $VIDEOS_AGGREGATOR_CHANGELOG_VALUE_HARD

VIDEOS_AGGREGATOR_REPARTITION_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-repartition-key)
VIDEOS_AGGREGATOR_REPARTITION_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-repartition-value)
VIDEOS_AGGREGATOR_REPARTITION_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-repartition-key?permanent=true)
VIDEOS_AGGREGATOR_REPARTITION_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/video-aggregator-VIDEO_AGGREGATOR-repartition-value?permanent=true)
echo $VIDEOS_AGGREGATOR_REPARTITION_KEY_SOFT
echo $VIDEOS_AGGREGATOR_REPARTITION_VALUE_SOFT
echo $VIDEOS_AGGREGATOR_REPARTITION_KEY_HARD
echo $VIDEOS_AGGREGATOR_REPARTITION_VALUE_HARD

CREATORS_VIDEOS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-videos-key)
CREATORS_VIDEOS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-videos-value)
CREATORS_VIDEOS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-videos-key?permanent=true)
CREATORS_VIDEOS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-videos-value?permanent=true)
echo $CREATORS_VIDEOS_KEY_SOFT
echo $CREATORS_VIDEOS_VALUE_SOFT
echo $CREATORS_VIDEOS_KEY_HARD
echo $CREATORS_VIDEOS_VALUE_HARD

CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-changelog-key)
CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-changelog-value)
CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-changelog-key?permanent=true)
CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-changelog-value?permanent=true)
echo $CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_KEY_SOFT
echo $CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_VALUE_SOFT
echo $CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_KEY_HARD
echo $CREATOR_VIDEO_CREATOR_MIXBI_CHANGELOG_VALUE_HARD

CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-repartition-key)
CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-repartition-value)
CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-repartition-key?permanent=true)
CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-CREATOR_MIXBI-repartition-value?permanent=true)
echo $CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_KEY_SOFT
echo $CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_VALUE_SOFT
echo $CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_KEY_HARD
echo $CREATOR_VIDEO_CREATOR_MIXBI_REPARTITION_VALUE_HARD

CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-changelog-key)
CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-changelog-value)
CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-changelog-key?permanent=true)
CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-changelog-value?permanent=true)
echo $CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_KEY_SOFT
echo $CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_VALUE_SOFT
echo $CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_KEY_HARD
echo $CREATOR_VIDEO_VIDEO_MIXBI_CHANGELOG_VALUE_HARD

CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-repartition-key)
CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-repartition-value)
CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-repartition-key?permanent=true)
CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creator-video-mixbi-VIDEO_MIXBI-repartition-value?permanent=true)
echo $CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_KEY_SOFT
echo $CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_VALUE_SOFT
echo $CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_KEY_HARD
echo $CREATOR_VIDEO_VIDEO_MIXBI_REPARTITION_VALUE_HARD