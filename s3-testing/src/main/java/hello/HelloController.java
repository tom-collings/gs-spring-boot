package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
	@Autowired
	ApplicationContext aCxt;
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World!";
    }
    
    @RequestMapping("/s3")
    public String s3() {
    	S3Worker s3Worker = getS3Worker();
    	if (s3Worker != null) {
    		return s3Worker.testGet();
    	}
    	else {
    		return "null worker bean";
    	}

    }
    
    private S3Worker getS3Worker() {
    	return aCxt.getBean(S3Worker.class);
    }
    
}
