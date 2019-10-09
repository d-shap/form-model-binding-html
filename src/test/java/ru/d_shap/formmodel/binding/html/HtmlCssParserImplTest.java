///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model html binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of Form model html binding.
//
// Form model html binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model html binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.formmodel.binding.html;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.w3c.css.sac.InputSource;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HtmlCssParserImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlCssParserImplTest {

    /**
     * Test class constructor.
     */
    public HtmlCssParserImplTest() {
        super();
    }

    /**
     * {@link HtmlCssParserImpl} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void getCssPropertiesTest() throws Exception {
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource(""))).isEmpty();

        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("wrong text"))).isEmpty();

        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name:value"))).hasSize(1);
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name:value"))).containsEntry("name", "value");

        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name:value;"))).hasSize(1);
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name:value;"))).containsEntry("name", "value");

        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name:value;"))).hasSize(1);
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name:value;"))).containsEntry("name", "value");

        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name: value;"))).hasSize(1);
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("name: value;"))).containsEntry("name", "value");

        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("display: block; color: red;"))).hasSize(2);
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("display: block; color: red;"))).containsEntry("display", "block");
        Assertions.assertThat(new HtmlCssParserImpl().getCssProperties(getInputSource("display: block; color: red;"))).containsEntry("color", "red");
    }

    /**
     * {@link HtmlCssParserImpl} class test.
     */
    @Test
    public void errorHandlerTest() {
        new HtmlCssParserImpl.IgnoreCssExceptionErrorHandler().warning(null);
        new HtmlCssParserImpl.IgnoreCssExceptionErrorHandler().error(null);
        new HtmlCssParserImpl.IgnoreCssExceptionErrorHandler().fatalError(null);
    }

    /**
     * {@link HtmlCssParserImpl} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void stderrTest() throws Exception {
        PrintStream err = System.err;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream, true, TestHelper.ENCODING);
                System.setErr(printStream);
                new HtmlCssParserImpl().getCssProperties(getInputSource("wrong text"));
                String message = new String(byteArrayOutputStream.toByteArray(), TestHelper.ENCODING);
                Assertions.assertThat(message).isBlank();
            } catch (UnsupportedEncodingException ex) {
                Assertions.fail(ex.getMessage());
            }
        } finally {
            System.setErr(err);
        }
    }

    private InputSource getInputSource(final String style) {
        Reader reader = new StringReader(style);
        return new InputSource(reader);
    }

}
