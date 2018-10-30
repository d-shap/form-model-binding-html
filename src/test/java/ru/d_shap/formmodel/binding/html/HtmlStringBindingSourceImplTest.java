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

import org.jsoup.nodes.Document;
import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HtmlStringBindingSourceImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlStringBindingSourceImplTest {

    /**
     * Test class constructor.
     */
    public HtmlStringBindingSourceImplTest() {
        super();
    }

    /**
     * {@link HtmlStringBindingSourceImpl} class test.
     */
    @Test
    public void getDocumentTest() {
        String html = createHtml();

        HtmlBindingSource htmlBindingSource1 = new HtmlStringBindingSourceImpl(html);
        Document resultDocument1 = htmlBindingSource1.getDocument();
        Assertions.assertThat(resultDocument1).isNotNull();
        Assertions.assertThat(resultDocument1.getElementsByTag("body").first().ownText()).isEqualTo("Test page body");

        HtmlBindingSource htmlBindingSource2 = new HtmlStringBindingSourceImpl(html, "http://foo.com");
        Document resultDocument2 = htmlBindingSource2.getDocument();
        Assertions.assertThat(resultDocument2).isNotNull();
        Assertions.assertThat(resultDocument2.getElementsByTag("body").first().ownText()).isEqualTo("Test page body");
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
