package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

public class HelloApplicationInitializer 
	implements ApplicationContextInitializer<ConfigurableApplicationContext>{

	private static final List<String> validLocalProfiles = Arrays.asList("local", "dev", "cloud");
	
	public static final String DEFAULT_PROFILE = "local";
	
	@Override
	public void initialize(ConfigurableApplicationContext context) {
		System.out.println("****** in the initializer class ******");
		
		ConfigurableEnvironment env = context.getEnvironment();
		
		String[] connectionProfiles = getActiveProfile(env);
		
		if (connectionProfiles == null) {
			connectionProfiles = new String[] {DEFAULT_PROFILE};
		}
		
		for (String connectionProfile : connectionProfiles) {
			System.out.println("Profile name is " + connectionProfile);
			env.addActiveProfile(connectionProfile);
		}
		
	}

	private String[] getActiveProfile(ConfigurableEnvironment env) {
		List<String> serviceProfiles = new ArrayList<String>();

        for (String profile : env.getActiveProfiles()) {
            if (validLocalProfiles.contains(profile)) {
                serviceProfiles.add(profile);
            }
        }
        
        if (serviceProfiles.size() > 1) {
            throw new IllegalStateException("Only one active Spring profile may be set among the following: " +
                    validLocalProfiles.toString() + ". " +
                    "These profiles are active: [" +
                    StringUtils.collectionToCommaDelimitedString(serviceProfiles) + "]");
        }

        if (serviceProfiles.size() > 0) {
            return createProfileNames(serviceProfiles.get(0));
        }

        return null;
	}

	private String[] createProfileNames(String baseName) {
		String[] profileNames = {baseName};
		return profileNames;
	}
}
