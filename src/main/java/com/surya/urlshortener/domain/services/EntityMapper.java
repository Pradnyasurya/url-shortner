package com.surya.urlshortener.domain.services;

import com.surya.urlshortener.domain.entities.ShortUrl;
import com.surya.urlshortener.domain.entities.User;
import com.surya.urlshortener.domain.models.ShortUrlDto;
import com.surya.urlshortener.domain.models.UserDto;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

        public ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
            UserDto userDto = null;
            if(shortUrl.getCreatedBy() != null) {
                userDto = toUserDto(shortUrl.getCreatedBy());
            }

            return new ShortUrlDto(
                    shortUrl.getId(),
                    shortUrl.getShortKey(),
                    shortUrl.getOriginalUrl(),
                    shortUrl.getIsPrivate(),
                    shortUrl.getExpiresAt(),
                    userDto,
                    shortUrl.getClickCount(),
                    shortUrl.getCreatedAt()
            );
        }

        public UserDto toUserDto(User user) {
            return new UserDto(user.getId(), user.getName());
        }
}
