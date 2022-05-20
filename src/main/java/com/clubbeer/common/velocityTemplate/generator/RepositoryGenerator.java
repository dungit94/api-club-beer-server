package com.clubbeer.common.velocityTemplate.generator;

import com.clubbeer.common.velocityTemplate.ParamTemplate;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RepositoryGenerator {
    String packageName = "com.clubbeer";
    private String packagePath = Paths.get("src\\main\\java\\com\\clubbeer").toAbsolutePath().toString();

    private void createFolderController(String path) {
        File theDir = new File(path);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public void createClassRepository(String tableName)
            throws FileNotFoundException,
            UnsupportedEncodingException {

        String tableNameUpperCase = toUpperCase(tableName);
        String packageNameRepository = String.format("%s.%s.repository", packageName, tableName);
        String packageNameEntity = String.format("%s.%s.entity.%s", packageName, tableName, tableNameUpperCase);

        String className = String.format("I%sRepository", tableNameUpperCase);
        String classEntity = String.format("%s", tableNameUpperCase);

        String path = String.format("%s\\%s\\repository", packagePath, tableName);
        createFolderController(path);

        List<ParamTemplate> paramTemplates = new ArrayList<>();
        paramTemplates.add(new ParamTemplate("packageName", packageNameRepository));
        paramTemplates.add(new ParamTemplate("packageNameEntity", packageNameEntity));
        paramTemplates.add(new ParamTemplate("ClassName", className));
        paramTemplates.add(new ParamTemplate("ClassEntity", classEntity));
        generaterRepository(paramTemplates, className, path);
    }

    private void generaterRepository(
            List<ParamTemplate> paramTemplates,
            String className,
            String path) throws
            FileNotFoundException,
            UnsupportedEncodingException {

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("vtemplates/repositoryTemplate.vm");
        VelocityContext context = new VelocityContext();
        paramTemplates.forEach((item) ->
                context.put(item.getFieldKey(), item.getFieldVariable()));
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        createFileController(path, className, writer);
    }

    private void createFileController(String path,
                                      String interfaceRepository,
                                      StringWriter writer)
            throws FileNotFoundException,
            UnsupportedEncodingException {
        PrintWriter out = new PrintWriter(path + "\\" + interfaceRepository + ".java", "UTF-8");
        out.print(writer);
        out.close();
    }

    private String toUpperCase(String nameUpperCase) {
        String firstLetStr = nameUpperCase.substring(0, 1).toUpperCase();
        String remLetStr = nameUpperCase.substring(1);
        return firstLetStr + remLetStr;
    }
}
