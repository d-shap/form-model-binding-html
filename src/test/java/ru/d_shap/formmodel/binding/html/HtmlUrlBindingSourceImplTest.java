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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.formmodel.InputSourceException;

import fi.iki.elonen.NanoHTTPD;

/**
 * Tests for {@link HtmlUrlBindingSourceImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlUrlBindingSourceImplTest {

    private static final int PORT = 43472;

    /**
     * Test class constructor.
     */
    public HtmlUrlBindingSourceImplTest() {
        super();
    }

    /**
     * {@link HtmlUrlBindingSourceImpl} class test.
     */
    @Test
    public void getDocumentTest() throws IOException {
        NanoHTTPD server = new NanoHTTPDImpl();
        try {
            server.start();
            String url = "http://127.0.0.1:" + PORT;
            HtmlBindingSource htmlBindingSource = new HtmlUrlBindingSourceImpl(url);
            Document document = htmlBindingSource.getDocument();
            Assertions.assertThat(document).isNotNull();
            Assertions.assertThat(document.getElementsByTag("body").first().ownText()).isEqualTo("Test page body");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlUrlBindingSourceImpl} class test.
     */
    @Test
    public void getDocumentWrongUrlFailTest() {
        try {
            String url1 = "file://fake_url";
            HtmlBindingSource htmlBindingSource1 = new HtmlUrlBindingSourceImpl(url1);
            htmlBindingSource1.getDocument();
            Assertions.fail("HtmlUrlBindingSourceImpl test fail");
        } catch (InputSourceException ex) {
            Assertions.assertThat(ex).hasCause(MalformedURLException.class);
        }

        try {
            URL url2 = new URL("file://fake_url");
            HtmlBindingSource htmlBindingSource2 = new HtmlUrlBindingSourceImpl(url2);
            htmlBindingSource2.getDocument();
            Assertions.fail("HtmlUrlBindingSourceImpl test fail");
        } catch (MalformedURLException ex) {
            Assertions.fail(ex.getMessage());
        } catch (InputSourceException ex) {
            Assertions.assertThat(ex).hasCause(MalformedURLException.class);
        }
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class NanoHTTPDImpl extends NanoHTTPD {

        NanoHTTPDImpl() {
            super(PORT);
        }

        @Override
        public Response serve(final IHTTPSession session) {
            String html = createHtml();
            return newFixedLengthResponse(html);
        }

        private String createHtml() {
            String html = "";
            html += "<html>";
            html += "<head>";
            html += "<title>Test page title</title>";
            html += "</head>";
            html += "<body>Test page body</body>";
            html += "</html>";
            return html;
        }

    }

}
