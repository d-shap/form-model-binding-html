///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model HTML binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of form model HTML binding.
//
// Form model HTML binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model HTML binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.formmodel.binding.html;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.formmodel.InputSourceException;

/**
 * Tests for {@link HtmlUrlBindingSourceImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlUrlBindingSourceImplTest {

    /**
     * Test class constructor.
     */
    public HtmlUrlBindingSourceImplTest() {
        super();
    }

    /**
     * {@link HtmlUrlBindingSourceImpl} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void getDocumentTest() throws Exception {
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            String url = server.getUrl();
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
