package org.converter.swagger;

import io.swagger.oas.models.media.Schema;
import io.swagger.parser.OpenAPIParser;
import io.swagger.parser.models.SwaggerParseResult;
import lombok.extern.slf4j.Slf4j;
import org.converter.swagger.api.service.ICApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
@Slf4j
public class SwaggerToCApplication implements CommandLineRunner {
    @Autowired
    ICApiService service;

    public static void main(String[] args) {

        SpringApplication.run(SwaggerToCApplication.class, args);

		/*Yaml yaml=new Yaml();
		SwaggerToCApplication app=new SwaggerToCApplication();
		InputStream is=app.getClass().getClassLoader().getResourceAsStream("petstore.yaml");
		//Map<String,Object> obj=yaml.load(is);

		try (Reader reader = new InputStreamReader(is, UTF_8)) {
			String yamlString =FileCopyUtils.copyToString(reader);
			SwaggerParseResult result = new OpenAPIParser().readContents(yamlString,null,null);
		Map<String, Schema> schemaMap=result.getOpenAPI().getComponents().getSchemas();

		//app.sp.getStructs(schemaMap);
		System.out.println(	app.sp.getHeader(schemaMap).toHeaderString());*/
		/*String struct="struct ";

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
				struct+="}"+ObjName.toLowerCase()+"_t ;\n";
				System.out.println(struct+"\n");
			}*/

	/*	} catch (IOException e) {
			throw new UncheckedIOException(e);
		}*/
        //	System.out.println(obj);


    }

    @Override
    public void run(String... args) throws Exception {
        SwaggerToCApplication app = new SwaggerToCApplication();
        InputStream is = app.getClass().getClassLoader().getResourceAsStream("petstore.yaml");
        //Map<String,Object> obj=yaml.load(is);

        try (Reader reader = new InputStreamReader(is, UTF_8)) {
            String yamlString = FileCopyUtils.copyToString(reader);
            SwaggerParseResult result = new OpenAPIParser().readContents(yamlString, null, null);
            Map<String, Schema> schemaMap = result.getOpenAPI().getComponents().getSchemas();

            //app.sp.getStructs(schemaMap);
            System.out.println(service.createHeader(yamlString).toHeaderString());


        } catch (Exception e) {

            log.error("Error in run", e);
            throw e;
        }
    }
}
