package org.converter.swagger.inf;

import io.swagger.oas.models.media.Schema;
import org.converter.swagger.model.clang.output.CHeader;


import io.swagger.oas.models.media.Schema;
import org.converter.swagger.model.clang.output.CHeader;
import org.converter.swagger.model.clang.output.CStructure;

import java.util.List;
import java.util.Map;

public interface ISchemaParser {

    public CHeader getHeader(final Map<String, Schema> schemaMap);
    public List<CStructure> getStructs(Map<String, Schema> schemaMap);
}
