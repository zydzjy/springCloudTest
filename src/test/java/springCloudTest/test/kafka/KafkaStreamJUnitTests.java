package springCloudTest.test.kafka;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.junit.Test;

public class KafkaStreamJUnitTests {

	@Test
	public void basicTest() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "yelling_app_id");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		Serde<String> stringSerde = Serdes.String();

		StreamsBuilder builder = new StreamsBuilder();

		KStream<String, String> simpleFirstStream = builder.stream("src-topic",
				Consumed.with(stringSerde, stringSerde));
		KStream<String, String> upperCasedStream = simpleFirstStream.mapValues(new ValueMapper<String, String>() {
			@Override
			public String apply(String value) {
				return value.toUpperCase();
			}
		});

		upperCasedStream.to("out-topic", Produced.with(stringSerde, stringSerde));
		//upperCasedStream.print(Printed.<String, String>toSysOut().withLabel("Yelling App"));

		KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), props);
		kafkaStreams.start();
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(35000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			  @Override
			  public void run() {
				  kafkaStreams.close();
			  }
			}));
	}
}
