package org.converter.swagger.parser;


import io.swagger.oas.models.media.Schema;
import io.swagger.oas.models.media.XML;
import lombok.extern.slf4j.Slf4j;
import org.converter.swagger.inf.ISchemaParser;
import org.converter.swagger.model.clang.output.CHeader;
import org.converter.swagger.model.clang.output.CStructure;
import org.converter.swagger.model.clang.output.CType;
import org.converter.swagger.model.clang.output.StructElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

@Component
@Slf4j
public class SchemaParser implements ISchemaParser {

    @Autowired
    private TypeParser typeParser;

    @Override
    public List<CStructure> getStructs(Map<String, Schema> schemaMap) {
        List<CStructure> stuctureList = new ArrayList<>();
        schemaMap.forEach((k, v) -> {
            stuctureList.add(parserEach(v, k));
        });
        return stuctureList;
    }

    @Override
    public CHeader getHeader(final Map<String, Schema> schemaMap) {
        CHeader header = new CHeader();
        header.setHeaderName("PET_STORE");
        header.setStructureList(getStructs(schemaMap));
        return header;
    }

    private @NotNull CStructure parserEach(@NotNull Schema<Schema> schema, String key) {
        log.info("parsing schmea for {}", key);
        CStructure cstruct = new CStructure(key.toLowerCase());
        cstruct.setFullExample(Optional.ofNullable(schema.getXml()).orElse(new XML()).toString());
        List<StructElement> structElements = cstruct.getStructElementList();

        schema.getProperties().forEach(getStringSchemaBiConsumer(structElements));
        cstruct.createStuctString();
        return cstruct;
    }

    @Contract(pure = true)
    private @NotNull BiConsumer<String, Schema> getStringSchemaBiConsumer(List<StructElement> structElements) {
        return (propName, value) -> {
            CType type = typeParser.getCType(value);
            structElements.add(StructElement.builder()
                    .elementName(propName)
                    .type(type)
                    .example(Optional.ofNullable(value.getExample()).orElse("").toString())
                    .build()
            );
        };
    }


}
