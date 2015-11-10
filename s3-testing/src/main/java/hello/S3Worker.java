package hello;

import org.springframework.core.env.AbstractEnvironment;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Worker {
	
	AmazonS3 s3Client = null;
	AWSCredentials awsCreds = null;
	
	public S3Worker() {
		
	}
	
	public S3Worker(AWSCredentials awsCreds) {
		this.awsCreds = awsCreds;
	}

	public String testGet() {
		if (getS3Client() == null) {
			return "get s3 client returned null";
		}
		else {
			return s3Client.getS3AccountOwner().getDisplayName() + "\r\n";
		}
	}
	
	private AmazonS3 getS3Client() {
		if (s3Client == null) {
			System.out.println("setting up amazon s3 client");
			
			s3Client = new AmazonS3Client(awsCreds);
		}
		return s3Client;
	}
	
}
