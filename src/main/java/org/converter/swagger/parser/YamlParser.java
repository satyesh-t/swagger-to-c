package org.converter.swagger.parser;

import io.swagger.oas.models.media.Schema;
import io.swagger.parser.OpenAPIParser;
import io.swagger.parser.models.SwaggerParseResult;
import org.converter.swagger.inf.IYamlParser;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Scope(scopeName = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class YamlParser implements IYamlParser {


    @Override
    public Map<String, Schema> yamlToSchemaMap(String yaml) {
        SwaggerParseResult swaggerParser=new OpenAPIParser().readContents(yaml,null,null);
        return swaggerParser.getOpenAPI().getComponents().getSchemas();
    }
}
