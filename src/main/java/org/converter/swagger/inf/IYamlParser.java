package org.converter.swagger.inf;

import io.swagger.oas.models.media.Schema;

import java.util.Map;

public interface IYamlParser {

    public Map<String , Schema> yamlToSchemaMap(String yaml);
}
