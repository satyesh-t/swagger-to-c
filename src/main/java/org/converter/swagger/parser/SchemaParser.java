package org.converter.swagger.parser;


import io.swagger.oas.models.media.Schema;
import org.converter.swagger.model.CStructure;
import org.converter.swagger.model.StructElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class SchemaParser {

    private CStructure parserEach(Schema<Schema> schema,String key)
    {
        CStructure cstruct=new CStructure(key.toLowerCase());
        List<StructElement> structElements=cstruct.getStructElementList();

        schema.getProperties().forEach((propName, value) -> {
            value.getType().toLowerCase()
            structElements.add(StructElement.builder()
                    .elementName(propName)
                    .typeName(value.getType().toLowerCase())
                    .build()
            );
        });

        return  cstruct;
    }

    private List<CStructure> parse(Schema<Schema> schema,String key)
    {

    }
}
