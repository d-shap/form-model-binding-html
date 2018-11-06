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

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HtmlFormInstanceBinder}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlFormInstanceBinderTest {

    /**
     * Test class constructor.
     */
    public HtmlFormInstanceBinderTest() {
        super();
    }

    /**
     * {@link HtmlFormInstanceBinder} class test.
     */
    @Test
    public void bindFormDefinitionTest() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page title</title>";
        html += "</head>";
        html += "<body>";
        html += "<div id='divid1' class='divclass'>";
        html += "<span><a href='resource1'>link</a></span>";
        html += "</div>";
        html += "<div id='divid2' class='divclass'>";
        html += "<span><a href='resource2'>link</a></span>";
        html += "</div>";
        html += "</body>";
        html += "</html>";

        String xml1 = "<?xml version='1.0'?>\n";
        xml1 += "<ns1:form id='id1' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml1 += "<ns1:element lookup='.divclass' type='required+'>";
        xml1 += "<ns1:form-reference id='id2'>";
        xml1 += "</ns1:form-reference>";
        xml1 += "</ns1:element>";
        xml1 += "</ns1:form>";

        String xml2 = "<?xml version='1.0'?>\n";
        xml2 += "<ns1:form id='id2' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml2 += "<ns1:element lookup='span'>";
        xml2 += "<ns1:element id='a' lookup='a'>";
        xml2 += "</ns1:element>";
        xml2 += "</ns1:element>";
        xml2 += "</ns1:form>";

        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml1, xml2);
        Document document = htmlFormBinder.bindHtml(html, "id1");
        List<Element> elements = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements).hasSize(2);
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(2);
        Assertions.assertThat(bindedElements.get(0).getAttribute("href")).isEqualTo("resource1");
        Assertions.assertThat(bindedElements.get(1).getAttribute("href")).isEqualTo("resource2");
    }

    /**
     * {@link HtmlFormInstanceBinder} class test.
     */
    @Test
    public void bindElementDefinitionTest() {

    }

    /**
     * {@link HtmlFormInstanceBinder} class test.
     */
    @Test
    public void bindAttributeDefinitionTest() {

    }

}
