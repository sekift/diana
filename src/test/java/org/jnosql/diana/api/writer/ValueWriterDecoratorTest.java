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

package org.jnosql.diana.api.writer;

import org.jnosql.diana.api.ValueWriter;
import org.junit.Before;
import org.junit.Test;

import java.time.temporal.Temporal;
import java.util.Optional;

import static org.junit.Assert.*;

public class ValueWriterDecoratorTest {

    private ValueWriter valueWriter;

    @Before
    public void setUp() {
        valueWriter = ValueWriterDecorator.getInstance();
    }

    @Test
    public void shouldVerifyCompatibility() {
        assertTrue(valueWriter.isCompatible(Optional.class));
        assertTrue(valueWriter.isCompatible(Temporal.class));
        assertFalse(valueWriter.isCompatible(Boolean.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldConvert() {
        String diana = "diana";
        Optional<String> optinal = Optional.of(diana);
        Object result = valueWriter.write(optinal);
        assertEquals(diana, result);
    }
}