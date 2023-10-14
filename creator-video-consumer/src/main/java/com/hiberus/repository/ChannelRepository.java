package com.hiberus.repository;

import com.hiberus.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findByCreatorIdentifier(String creatorIdentifier);
}
