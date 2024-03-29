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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.fm.formmodel.document.DocumentProcessor;

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
     * Set mock for URL Connection.
     */
    @BeforeClass
    public static void setURLStreamHandlerFactory() {
        UrlHandler.setURLStreamHandlerFactory();
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
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo("");
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
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtml(html, "group", "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo("");
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
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();

        List<String> tagNames = htmlFormBinder.bindHtml(html, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindHtml(html, "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "");
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
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();

        List<String> tagNames = htmlFormBinder.bindHtml(html, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindHtml(html, "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo("http://example.com/path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlWithGroupAndIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "group", "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo("http://example.com/path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlAndProcessWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();

        List<String> tagNames = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "http://example.com/path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindHtmlWithBaseUrlAndProcessWithGroupAndIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();

        List<String> tagNames = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "http://example.com/path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlStringWithIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        Document document = htmlFormBinder.bindUrl(UrlHandler.URL_HTML_1, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlStringWithGroupAndIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        Document document = htmlFormBinder.bindUrl(UrlHandler.URL_HTML_1, "group", "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlStringAndProcessWithIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        List<String> tagNames = htmlFormBinder.bindUrl(UrlHandler.URL_HTML_1, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindUrl(UrlHandler.URL_HTML_1, "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlStringAndProcessWithGroupAndIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        List<String> tagNames = htmlFormBinder.bindUrl(UrlHandler.URL_HTML_1, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindUrl(UrlHandler.URL_HTML_1, "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlObjectWithIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        URL url = new URL(UrlHandler.URL_HTML_1);
        Document document = htmlFormBinder.bindUrl(url, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlObjectWithGroupAndIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        URL url = new URL(UrlHandler.URL_HTML_1);
        Document document = htmlFormBinder.bindUrl(url, "group", "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlObjectAndProcessWithIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        URL url = new URL(UrlHandler.URL_HTML_1);

        List<String> tagNames = htmlFormBinder.bindUrl(url, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindUrl(url, "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void bindUrlObjectAndProcessWithGroupAndIdTest() throws Exception {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);

        URL url = new URL(UrlHandler.URL_HTML_1);

        List<String> tagNames = htmlFormBinder.bindUrl(url, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindUrl(url, "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", UrlHandler.URL_HTML_1 + "path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        org.jsoup.nodes.Document sourceDocument = Jsoup.parse(html);
        Document document = htmlFormBinder.bindDocument(sourceDocument, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo("");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentWithGroupAndIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        org.jsoup.nodes.Document sourceDocument = Jsoup.parse(html);
        Document document = htmlFormBinder.bindDocument(sourceDocument, "group", "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements1).hasSize(1);
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(1);
        Assertions.assertThat(bindedElements1.get(0).getElement().tagName()).isEqualTo("div");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "a");
        Assertions.assertThat(elements2).hasSize(1);
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).getAttribute("href")).isEqualTo("path/to/resource");
        Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo("");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentAndProcessWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        org.jsoup.nodes.Document sourceDocument = Jsoup.parse(html);

        List<String> tagNames = htmlFormBinder.bindDocument(sourceDocument, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindDocument(sourceDocument, "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void bindDocumentAndProcessWithGroupAndIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        org.jsoup.nodes.Document sourceDocument = Jsoup.parse(html);

        List<String> tagNames = htmlFormBinder.bindDocument(sourceDocument, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindDocument(sourceDocument, "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getElementsTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements = htmlFormBinder.getElements(document, "//*[local-name() = 'element']");
        Assertions.assertThat(elements).hasSize(2);
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(2);
        Assertions.assertThat(bindedElements.get(0).getElement().tagName()).isEqualTo("div");
        Assertions.assertThat(bindedElements.get(1).getElement().tagName()).isEqualTo("a");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getElementsWithIdTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
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
    public void getElementsWithAttributeTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass' attr='val1'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a' attr='val2'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements = htmlFormBinder.getElementsWithAttribute(document, "attr", "val1");
        Assertions.assertThat(elements).hasSize(1);
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(1);
        Assertions.assertThat(bindedElements.get(0).getElement().tagName()).isEqualTo("div");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getBindedElementsTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "<ns1:attribute id='d' lookup='style'>";
        xml += "</ns1:attribute>";
        xml += "<ns1:attribute id='d' lookup='style.display'>";
        xml += "</ns1:attribute>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements).hasSize(3);
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(1);
        Assertions.assertThat(bindedElements.get(0).getElement().tagName()).isEqualTo("div");
        Assertions.assertThat(bindedElements.get(0).getAttribute("style")).isEqualTo("display: none;");
        Assertions.assertThat(bindedElements.get(0).getAttribute("style.display")).isEqualTo("none");
    }

    /**
     * {@link HtmlFormBinder} class test.
     */
    @Test
    public void getBindedAttributesTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "<ns1:attribute id='d' lookup='style'>";
        xml += "</ns1:attribute>";
        xml += "<ns1:attribute id='d' lookup='style.display'>";
        xml += "</ns1:attribute>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = UrlHandler.getHtml1();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements = htmlFormBinder.getElementsWithId(document, "d");
        Assertions.assertThat(elements).hasSize(3);
        List<HtmlBindedAttribute> bindedAttributes = htmlFormBinder.getBindedAttributes(elements);
        Assertions.assertThat(bindedAttributes).hasSize(2);
        Assertions.assertThat(bindedAttributes.get(0).getValue()).isEqualTo("display: none;");
        Assertions.assertThat(bindedAttributes.get(1).getValue()).isEqualTo("none");
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

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class ReferenceDocumentProcessor implements DocumentProcessor<List<String>> {

        private final HtmlFormBinder _htmlFormBinder;

        private final String _id;

        ReferenceDocumentProcessor(final HtmlFormBinder htmlFormBinder, final String id) {
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
                result.add(bindedElement.getAttribute("href"));
                result.add(bindedElement.getAbsoluteAttribute("href"));
            }
            return result;
        }

    }

}
