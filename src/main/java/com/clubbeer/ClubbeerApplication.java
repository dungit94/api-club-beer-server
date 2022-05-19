package com.clubbeer;

import com.clubbeer.common.velocityTemplate.FieldTemplate;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class ClubbeerApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(ClubbeerApplication.class, args);
//	}

    static String modelName = "Demo113";
    static String packageName = "com.clubbeer.demo.entity";

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("vtemplates/entityTemplate.vm");

        VelocityContext context = new VelocityContext();

        if (packageName != null) {
            context.put("packagename", packageName);
        }

        List<FieldTemplate> properties = new ArrayList<>();
        properties.add(new FieldTemplate("id", "int"));
        properties.add(new FieldTemplate("firstName", "String"));
        properties.add(new FieldTemplate("lastName", "String"));
        properties.add(new FieldTemplate("dob", "LocalDate"));
        context.put("className", modelName);
        context.put("properties", properties);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(ClasspathResourceLoader.class.getName());
        PrintWriter out = new PrintWriter("F:\\formos\\project\\api-club-beer-server\\src\\main\\java\\com\\clubbeer\\demo\\entity\\" + modelName + ".java", "UTF-8");
        out.print(writer.toString());
        out.close();
        System.out.println(writer.toString());
    }

}
