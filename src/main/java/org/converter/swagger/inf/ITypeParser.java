package org.converter.swagger.inf;

import io.swagger.oas.models.media.Schema;
import org.converter.swagger.model.clang.output.CType;

public interface ITypeParser {

    public CType getCType(Schema<?> schema);
}
