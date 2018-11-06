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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.formmodel.document.DocumentProcessor;

/**
 * Tests for {@link HtmlFormBinder}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlFormBinderTest {

    /**
     * Test class constructor.
     */
    public HtmlFormBinderTest() {
        super();
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "id");
        List<Element> elements = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements).hasSize(1);
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(1);
        Assertions.assertThat(bindedElements.get(0).getElement().tagName()).isEqualTo("div");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithGroupAndIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "group", "id");
        List<Element> elements = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements).hasSize(1);
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(1);
        Assertions.assertThat(bindedElements.get(0).getElement().tagName()).isEqualTo("div");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlAndProcessWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        List<String> tagNames = htmlFormBinder.bindHtml(html, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlAndProcessWithGroupAndIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        List<String> tagNames = htmlFormBinder.bindHtml(html, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlAndProcessWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlAndProcessWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlStringWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlStringWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlStringAndProcessWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlStringAndProcessWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlObjectWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlObjectWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlObjectAndProcessWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindUrlObjectAndProcessWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentStringWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentStringWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentStringAndProcessWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentStringAndProcessWithGroupAndIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getElementsTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getElementsWithIdTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getElementsWithAttributeTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getBindedElementsTest() {

    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getBindedAttributesTest() {

    }

    private String createHtml() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page title</title>";
        html += "</head>";
        html += "<body>";
        html += "<div id='divid' class='divclass' style='display: none;'>";
        html += "</div>";
        html += "</body>";
        html += "</html>";
        return html;
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class TagNameDocumentProcessor implements DocumentProcessor<List<String>> {

        private final HtmlFormBinder _htmlFormBinder;

        private final String _id;

        TagNameDocumentProcessor(final HtmlFormBinder htmlFormBinder, final String id) {
            super();
            _htmlFormBinder = htmlFormBinder;
            _id = id;
        }

        @Override
        public List<String> process(final Document document) {
            List<Element> elements = _htmlFormBinder.getElementsWithId(document, _id);
            List<HtmlBindedElement> bindedElements = _htmlFormBinder.getBindedElements(elements);
            List<String> result = new ArrayList<>();
            for (HtmlBindedElement bindedElement : bindedElements) {
                result.add(bindedElement.getElement().tagName());
            }
            return result;
        }
    }

}
