/*
 * Copyright 2017 Otavio Santana and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package org.jnosql.diana.api.reader;


import org.jnosql.diana.api.ValueReader;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class to reads and converts to both {@link Boolean} and {@link AtomicBoolean}
 */
@SuppressWarnings("unchecked")
public final class BooleanValueReader implements ValueReader {

    @Override
    public <T> boolean isCompatible(Class<T> clazz) {
        return Boolean.class.equals(clazz) || AtomicBoolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    @Override
    public <T> T read(Class<T> clazz, Object value) {

        boolean isAtomicBoolean = AtomicBoolean.class.equals(clazz);

        if (isAtomicBoolean && AtomicBoolean.class.isInstance(value)) {
            return (T) value;
        }
        Boolean bool = null;
        if (Boolean.class.isInstance(value)) {
            bool = Boolean.class.cast(value);
        } else if (AtomicBoolean.class.isInstance(value)) {
            bool = AtomicBoolean.class.cast(value).get();
        } else if (Number.class.isInstance(value)) {
            bool = Number.class.cast(value).longValue() != 0;
        } else if (String.class.isInstance(value)) {
            bool = Boolean.valueOf(value.toString());
        }

        if (isAtomicBoolean) {
            return (T) new AtomicBoolean(bool);
        }

        return (T) bool;
    }


}
