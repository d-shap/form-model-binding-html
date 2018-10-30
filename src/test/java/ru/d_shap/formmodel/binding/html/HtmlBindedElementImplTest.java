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
 * Tests for {@link HtmlBindedElementImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlBindedElementImplTest {

    /**
     * Test class constructor.
     */
    public HtmlBindedElementImplTest() {
        super();
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getElementTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='blockclass' lookup='.blockclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "pclass");
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(2);
        Assertions.assertThat(bindedElements1.get(0).getElement()).isNotNull();
        Assertions.assertThat(bindedElements1.get(0).getElement().tag().getName()).isEqualTo("p");
        Assertions.assertThat(bindedElements1.get(1).getElement()).isNotNull();
        Assertions.assertThat(bindedElements1.get(1).getElement().tag().getName()).isEqualTo("p");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "blockclass");
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(2);
        Assertions.assertThat(bindedElements2.get(0).getElement()).isNotNull();
        Assertions.assertThat(bindedElements2.get(0).getElement().tag().getName()).isEqualTo("div");
        Assertions.assertThat(bindedElements2.get(1).getElement()).isNotNull();
        Assertions.assertThat(bindedElements2.get(1).getElement().tag().getName()).isEqualTo("span");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getOwnTextTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='blockclass' lookup='.blockclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "pclass");
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(2);
        Assertions.assertThat(bindedElements1.get(0).getOwnText()).isEqualTo("This is a to somewhere.");
        Assertions.assertThat(bindedElements1.get(1).getOwnText()).isEqualTo("");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "blockclass");
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(2);
        Assertions.assertThat(bindedElements2.get(0).getOwnText()).isEqualTo("");
        Assertions.assertThat(bindedElements2.get(1).getOwnText()).isEqualTo("Span in Div 2.");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getTextTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='blockclass' lookup='.blockclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "pclass");
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(2);
        Assertions.assertThat(bindedElements1.get(0).getText()).isEqualTo("This is a link to somewhere.");
        Assertions.assertThat(bindedElements1.get(1).getText()).isEqualTo("");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "blockclass");
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(2);
        Assertions.assertThat(bindedElements2.get(0).getText()).isEqualTo("Span in Div 1.");
        Assertions.assertThat(bindedElements2.get(1).getText()).isEqualTo("Span in Div 2.");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getAttributeValueTest() {

    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getAbsoluteAttributeValueTest() {

    }

    private String createHtml() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page</title>";
        html += "</head>";
        html += "<body>";
        html += "<p class='pclass'>";
        html += "This is a ";
        html += "<a href='linkref'>link</a>";
        html += " to somewhere.";
        html += "</p>";
        html += "<p class='pclass'>";
        html += "<div class='blockclass'>";
        html += "<span>Span in Div 1.</span>";
        html += "</div>";
        html += "<div>";
        html += "<span class='blockclass'>Span in Div 2.</span>";
        html += "</div>";
        html += "</p>";
        html += "</body>";
        html += "</html>";
        return html;
    }

}
