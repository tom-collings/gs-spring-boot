package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
@Profile("local")
public class HelloAppConfiguration {

	@Bean 
	public S3Worker s3Worker() {
		return new S3Worker(awsCreds());
	}

	@Bean
	public AWSCredentials awsCreds() {
		return new BasicAWSCredentials(
				"MyKeyID", "MySecretAccessKey");
	}

	
}
