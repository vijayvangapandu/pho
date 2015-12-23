package com.eharmony.services.mymatchesservice.service.transform;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eharmony.services.mymatchesservice.service.SimpleMatchedUserDto;

public class MapToMatchedUserDtoTransformer implements Function< Map<String, Map<String, Object>>, SimpleMatchedUserDto> {
    private static final Logger logger = LoggerFactory.getLogger(MapToMatchedUserDtoTransformer.class);
    
    static public final String MATCHED_USER_KEY = "matchedUser";
    static public final String MATCH_KEY = "match";
    static public final String DELIVERED_DATE_KEY = "deliveredDate";
    static public final String NAME_KEY = "firstName";
    static public final String USER_ID_KEY = "userId";
    static public final String PHOTO_KEY = "hasPhoto";
    static public final String AGE_KEY = "age";
    

    @Override
    public SimpleMatchedUserDto apply(Map<String, Map<String, Object>> matchMap) {
        SimpleMatchedUserDto userItem = new SimpleMatchedUserDto();
        try {
            Map<String, Object> userMap = matchMap.get(MATCHED_USER_KEY);
            Long deliveredDateLong = (Long) matchMap.get(MATCH_KEY).get(DELIVERED_DATE_KEY);
            
            userItem.setMatchUserFirstName((String) userMap .get(NAME_KEY));
            userItem.setMatchUserId(Long.toString((Long) userMap.get(USER_ID_KEY)));
            userItem.setHasPrimaryPhoto((Boolean) userMap.get(PHOTO_KEY));
            userItem.setAge((Integer) userMap.get(AGE_KEY));
            Date deliveredDate = new Date(deliveredDateLong);
            userItem.setDeliveredDate(deliveredDate);
        } catch (Exception exp) {
            logger.warn("Error while transforming match map to matched user {}", matchMap, exp);
            return null;
        }
        return userItem;
    }

}
