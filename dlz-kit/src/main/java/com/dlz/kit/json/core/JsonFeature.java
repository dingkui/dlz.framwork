package com.dlz.kit.json.core;

/** Optional parsing and writing behavior. Strict JSON is the default. */
public enum JsonFeature {
    ALLOW_COMMENTS,
    ALLOW_SINGLE_QUOTES,
    ALLOW_UNQUOTED_KEYS,
    ALLOW_UNQUOTED_STRING_VALUES,
    ALLOW_TRAILING_COMMA,
    FAIL_ON_DUPLICATE_KEYS,
    WRITE_NULLS,
    WRITE_PRETTY,
    ESCAPE_NON_ASCII
}
