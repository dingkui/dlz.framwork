package com.dlz.kit.json.core;

/** Encodes Java values to JSON text. */
public interface JsonEncoder {
    String encode(Object value);
}
