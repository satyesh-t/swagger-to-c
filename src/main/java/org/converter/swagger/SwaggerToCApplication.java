package org.converter.swagger;

import io.swagger.oas.models.media.Schema;
import io.swagger.parser.OpenAPIParser;
import io.swagger.parser.models.SwaggerParseResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileCopyUtils;
import org.yaml.snakeyaml.Yaml;

import javax.naming.Name;
import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class SwaggerToCApplication {


	public static void main(String[] args) {

		Yaml yaml=new Yaml();
		SwaggerToCApplication app=new SwaggerToCApplication();
		InputStream is=app.getClass().getClassLoader().getResourceAsStream("petstore.yaml");
		//Map<String,Object> obj=yaml.load(is);

		try (Reader reader = new InputStreamReader(is, UTF_8)) {
			String yamlString =FileCopyUtils.copyToString(reader);
			SwaggerParseResult result = new OpenAPIParser().readContents(yamlString,null,null);
		Map<String, Schema> schemaMap=result.getOpenAPI().getComponents().getSchemas();
		String struct="struct ";

			Set<Map.Entry<String, Schema>> entries  = schemaMap.entrySet();
			for( Map.Entry <String, Schema> entry :entries)
			{
				String ObjName=entry.getKey();
				Schema s= entry.getValue();
				struct+=ObjName.toLowerCase()+"_st {";
				Map<String, Schema> properties=s.getProperties();
				Set<Map.Entry<String, Schema>> propEntries  = schemaMap.entrySet();
				for( Map.Entry <String, Schema> propEntry :propEntries)
				{
					String PropName=propEntry.getKey();
					Schema ps= propEntry.getValue();
					String propType=ps.getType();

					struct+=propType.toLowerCase()+" "+PropName.toLowerCase()+";\n";
					System.out.println(struct+"\n");

				}
				struct+="}"+s.getName().toLowerCase()+"_t ;";
				System.out.println(struct+"\n");
			}

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	//	System.out.println(obj);

		SpringApplication.run(SwaggerToCApplication.class, args);

	}

}
