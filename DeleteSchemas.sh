#!/bin/bash

CREATORS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-key)
CREATORS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-value)
CREATORS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-key?permanent=true)
CREATORS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/creators-value?permanent=true)
echo $CREATORS_KEY_SOFT
echo $CREATORS_VALUE_SOFT
echo $CREATORS_VALUE_HARD
echo $CREATORS_KEY_HARD

VIDEOS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-key)
VIDEOS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-value)
VIDEOS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-key?permanent=true)
VIDEOS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-value?permanent=true)
echo $VIDEOS_KEY_SOFT
echo $VIDEOS_VALUE_SOFT
echo $VIDEOS_KEY_HARD
echo $VIDEOS_VALUE_HARD

VIDEOS_PCS_KEY_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-key)
VIDEOS_PCS_VALUE_SOFT=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-value)
VIDEOS_PCS_KEY_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-key?permanent=true)
VIDEOS_PCS_VALUE_HARD=$(curl --silent -X DELETE -u client:client http://localhost:8081/subjects/videos-pcs-value?permanent=true)
echo $VIDEOS_PCS_KEY_SOFT
echo $VIDEOS_PCS_VALUE_SOFT
echo $VIDEOS_PCS_KEY_HARD
echo $VIDEOS_PCS_VALUE_HARD