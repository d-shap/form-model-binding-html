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
package ru.d_shap.fm.formmodel.binding.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.w3c.dom.Element;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.fm.formmodel.binding.FormInstanceBuilder;

/**
 * Tests for {@link HtmlBindedFormImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlBindedFormImplTest {

    /**
     * Test class constructor.
     */
    public HtmlBindedFormImplTest() {
        super();
    }

    /**
     * {@link HtmlBindedFormImpl} class test.
     */
    @Test
    public void getDocumentTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='nonexisting' lookup='nonexisting' type='optional'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        String html = createHtml();
        Document sourceDocument = Jsoup.parse(html);
        org.w3c.dom.Document resultDocument = htmlFormBinder.bindDocument(sourceDocument, "id");
        Element element = resultDocument.getDocumentElement();
        HtmlBindedForm bindedForm = (HtmlBindedForm) element.getUserData(FormInstanceBuilder.USER_DATA_BINDED_OBJECT);

        Document bindedFormDocument = bindedForm.getDocument();
        Assertions.assertThat(bindedFormDocument).isNotNull();
        Assertions.assertThat(bindedFormDocument).isSameAs(sourceDocument);
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
