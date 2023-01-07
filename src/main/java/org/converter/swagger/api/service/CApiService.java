package org.converter.swagger.api.service;

import io.swagger.oas.models.media.Schema;
import org.converter.swagger.inf.ISchemaParser;
import org.converter.swagger.inf.IYamlParser;
import org.converter.swagger.model.clang.output.CHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CApiService implements ICApiService {

    private IYamlParser yamlParser;

    private ISchemaParser schemaParser;

    @Autowired
    public CApiService(IYamlParser yamlParser, ISchemaParser schemaParser) {
        this.yamlParser = yamlParser;
        this.schemaParser = schemaParser;
    }

    @Override
    public CHeader createHeader(String yaml) {
        Map<String, Schema> schemaMap = yamlParser.yamlToSchemaMap(yaml);
        CHeader header = schemaParser.getHeader(schemaMap);
        return header;

    }
}
