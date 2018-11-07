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

        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass' type='required+'>";
        xml += "<ns1:element id='s' lookup='span'>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:element>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";

        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(2);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(2);
        Assertions.assertThat(bindedElements1.get(0).getAttribute("id")).isEqualTo("divid1");
        Assertions.assertThat(bindedElements1.get(1).getAttribute("id")).isEqualTo("divid2");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "s");
        Assertions.assertThat(elements2).hasSize(2);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(2);
        Assertions.assertThat(bindedElements2.get(0).getElement().tagName()).isEqualTo("span");
        Assertions.assertThat(bindedElements2.get(1).getElement().tagName()).isEqualTo("span");

        List<Element> elements3 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements3).hasSize(2);
        List<HtmlBindedElement> bindedElements3 = htmlFormBinder.getBindedElements(elements3);
        Assertions.assertThat(bindedElements3).hasSize(2);
        Assertions.assertThat(bindedElements3.get(0).getAttribute("href")).isEqualTo("resource1");
        Assertions.assertThat(bindedElements3.get(1).getAttribute("href")).isEqualTo("resource2");
    }

    /**
     * {@link HtmlFormInstanceBinder} class test.
     */
    @Test
    public void bindAttributeDefinitionTest() {
        String html1 = "";
        html1 += "<html>";
        html1 += "<head>";
        html1 += "<title>Test page title</title>";
        html1 += "</head>";
        html1 += "<body>";
        html1 += "<div class='divclass'>";
        html1 += "<a>Link</a>";
        html1 += "</div>";
        html1 += "</body>";
        html1 += "</html>";

        String html2 = "";
        html2 += "<html>";
        html2 += "<head>";
        html2 += "<title>Test page title</title>";
        html2 += "</head>";
        html2 += "<body>";
        html2 += "<div class='divclass' style='display: none;'>";
        html2 += "<a href='/path/to/resource'>Link</a>";
        html2 += "</div>";
        html2 += "</body>";
        html2 += "</html>";

        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element lookup='.divclass'>";
        xml += "<ns1:attribute id='a1' lookup='style' type='optional'>";
        xml += "</ns1:attribute>";
        xml += "<ns1:attribute id='a2' lookup='style.padding' type='optional'>";
        xml += "</ns1:attribute>";
        xml += "<ns1:attribute id='a3' lookup='style.display' type='optional'>";
        xml += "</ns1:attribute>";
        xml += "<ns1:element lookup='a'>";
        xml += "<ns1:attribute id='a4' lookup='href' type='optional'>";
        xml += "</ns1:attribute>";
        xml += "</ns1:element>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";

        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        Document document1 = htmlFormBinder.bindHtmlWithBaseUrl(html1, "http://example.com", "id");
        List<Element> elements11 = htmlFormBinder.getElementsWithId(document1, "a1");
        Assertions.assertThat(elements11).hasSize(0);
        List<HtmlBindedAttribute> bindedAttributes11 = htmlFormBinder.getBindedAttributes(elements11);
        Assertions.assertThat(bindedAttributes11).hasSize(0);
        List<Element> elements12 = htmlFormBinder.getElementsWithId(document1, "a2");
        Assertions.assertThat(elements12).hasSize(0);
        List<HtmlBindedAttribute> bindedAttributes12 = htmlFormBinder.getBindedAttributes(elements12);
        Assertions.assertThat(bindedAttributes12).hasSize(0);
        List<Element> elements13 = htmlFormBinder.getElementsWithId(document1, "a3");
        Assertions.assertThat(elements13).hasSize(0);
        List<HtmlBindedAttribute> bindedAttributes13 = htmlFormBinder.getBindedAttributes(elements13);
        Assertions.assertThat(bindedAttributes13).hasSize(0);
        List<Element> elements14 = htmlFormBinder.getElementsWithId(document1, "a4");
        Assertions.assertThat(elements14).hasSize(0);
        List<HtmlBindedAttribute> bindedAttributes14 = htmlFormBinder.getBindedAttributes(elements14);
        Assertions.assertThat(bindedAttributes14).hasSize(0);

        Document document2 = htmlFormBinder.bindHtmlWithBaseUrl(html2, "http://example.com", "id");
        List<Element> elements21 = htmlFormBinder.getElementsWithId(document2, "a1");
        Assertions.assertThat(elements21).hasSize(1);
        List<HtmlBindedAttribute> bindedAttributes21 = htmlFormBinder.getBindedAttributes(elements21);
        Assertions.assertThat(bindedAttributes21).hasSize(1);
        Assertions.assertThat(bindedAttributes21.get(0).getValue()).isEqualTo("display: none;");
        List<Element> elements22 = htmlFormBinder.getElementsWithId(document2, "a2");
        Assertions.assertThat(elements22).hasSize(0);
        List<HtmlBindedAttribute> bindedAttributes22 = htmlFormBinder.getBindedAttributes(elements22);
        Assertions.assertThat(bindedAttributes22).hasSize(0);
        List<Element> elements23 = htmlFormBinder.getElementsWithId(document2, "a3");
        Assertions.assertThat(elements23).hasSize(1);
        List<HtmlBindedAttribute> bindedAttributes23 = htmlFormBinder.getBindedAttributes(elements23);
        Assertions.assertThat(bindedAttributes23).hasSize(1);
        Assertions.assertThat(bindedAttributes23.get(0).getValue()).isEqualTo("none");
        List<Element> elements24 = htmlFormBinder.getElementsWithId(document2, "a4");
        Assertions.assertThat(elements24).hasSize(1);
        List<HtmlBindedAttribute> bindedAttributes24 = htmlFormBinder.getBindedAttributes(elements24);
        Assertions.assertThat(bindedAttributes24).hasSize(1);
        Assertions.assertThat(bindedAttributes24.get(0).getValue()).isEqualTo("/path/to/resource");
        Assertions.assertThat(bindedAttributes24.get(0).getAbsoluteValue()).isEqualTo("http://example.com/path/to/resource");
    }

}
