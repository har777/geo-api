package api;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class Helper {
    static Timestamp getPostgresTimestampFromDateString(String dateString) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT).withZone(ZoneOffset.UTC));
        return Timestamp.from(zonedDateTime.toInstant());
    }

    static Timestamp getPostgresTimestampFromEpoch(long epoch) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.of("UTC"));
        return Timestamp.from(zonedDateTime.toInstant());
    }
}