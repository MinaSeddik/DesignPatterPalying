package kafka;

import java.time.Duration;
import java.util.Properties;
import kafka.model.PageView;
import kafka.model.Search;
import kafka.model.UserActivity;
import kafka.model.UserProfile;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.StreamJoined;

public class ClickstreamEnrichment {
    public ClickstreamEnrichment() {
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("application.id", "clicks");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("auto.offset.reset", "earliest");
        StreamsBuilder builder = new StreamsBuilder();
        KStream<Integer, PageView> views = builder.stream("clicks.pages.views", Consumed.with(Serdes.Integer(), new PageViewSerde()));
        KTable<Integer, UserProfile> profiles = builder.table("clicks.user.profile", Consumed.with(Serdes.Integer(), new ProfileSerde()));
        KStream<Integer, Search> searches = builder.stream("clicks.search", Consumed.with(Serdes.Integer(), new SearchSerde()));
        KStream<Integer, UserActivity> viewsWithProfile = views.leftJoin(profiles, (page, profile) -> {
            return profile != null ? new UserActivity(profile.getUserID(), profile.getUserName(), profile.getZipcode(), profile.getInterests(), "", page.getPage()) : new UserActivity(-1, "", "", (String[])null, "", page.getPage());
        });
        KStream<Integer, UserActivity> userActivityKStream = viewsWithProfile.leftJoin(searches, (userActivity, search) -> {
            if (search != null) {
                userActivity.updateSearch(search.getSearchTerms());
            } else {
                userActivity.updateSearch("");
            }

            return userActivity;
        }, JoinWindows.of(Duration.ofSeconds(1L)), StreamJoined.with(Serdes.Integer(), new UserActivitySerde(), new SearchSerde()));
        userActivityKStream.to("clicks.user.activity", Produced.with(Serdes.Integer(), new UserActivitySerde()));
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp();
        streams.start();
        Thread.sleep(60000L);
        streams.close();
    }

    public static final class UserActivitySerde extends Serdes.WrapperSerde<UserActivity> {
        public UserActivitySerde() {
            super((Serializer)null, (Deserializer)null);
        }
    }

    public static final class SearchSerde extends Serdes.WrapperSerde<Search> {
        public SearchSerde() {
            super((Serializer)null, (Deserializer)null);
        }
    }

    public static final class ProfileSerde extends Serdes.WrapperSerde<UserProfile> {
        public ProfileSerde() {
            super((Serializer)null, (Deserializer)null);
        }
    }

    public static final class PageViewSerde extends Serdes.WrapperSerde<PageView> {
        public PageViewSerde() {
            super((Serializer)null, (Deserializer)null);
        }
    }
}
