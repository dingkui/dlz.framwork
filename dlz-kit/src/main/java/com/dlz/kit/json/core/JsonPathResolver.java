package com.dlz.kit.json.core;

/**
 * Resolves a path against a value that is not a native JSON container.
 *
 * <p>This extension point lets adapter modules support Java beans without
 * adding reflection or mapper dependencies to the JSON core.</p>
 */
public interface JsonPathResolver {
    Object resolve(Object value, String path);
}
