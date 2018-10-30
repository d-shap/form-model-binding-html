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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HtmlDocumentBindingSourceImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlDocumentBindingSourceImplTest {

    /**
     * Test class constructor.
     */
    public HtmlDocumentBindingSourceImplTest() {
        super();
    }

    /**
     * {@link HtmlDocumentBindingSourceImpl} class test.
     */
    @Test
    public void getDocumentTest() {
        String html = createHtml();
        Document document = Jsoup.parse(html);
        HtmlBindingSource htmlBindingSource = new HtmlDocumentBindingSourceImpl(document);
        Assertions.assertThat(htmlBindingSource.getDocument()).isNotNull();
        Assertions.assertThat(htmlBindingSource.getDocument()).isSameAs(document);
    }

    private String createHtml() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page</title>";
        html += "</head>";
        html += "<body>Test page</body>";
        html += "</html>";
        return html;
    }

}
