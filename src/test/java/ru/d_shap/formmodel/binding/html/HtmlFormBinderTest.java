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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
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
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
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
        String html = createHtml();
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
        String html = createHtml();

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
        String html = createHtml();

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
        String html = createHtml();
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
        String html = createHtml();
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
        String html = createHtml();

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
        String html = createHtml();

        List<String> tagNames = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
        Assertions.assertThat(tagNames).containsExactly("div");

        List<String> references = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://example.com", "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
        Assertions.assertThat(references).containsExactly("path/to/resource", "http://example.com/path/to/resource");
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlStringWithIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            String url = server.getUrl();
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
            Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlStringWithGroupAndIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            String url = server.getUrl();
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
            Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlStringAndProcessWithIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            String url = server.getUrl();

            List<String> tagNames = htmlFormBinder.bindUrl(url, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
            Assertions.assertThat(tagNames).containsExactly("div");

            List<String> references = htmlFormBinder.bindUrl(url, "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
            Assertions.assertThat(references).containsExactly("path/to/resource", url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlStringAndProcessWithGroupAndIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            String url = server.getUrl();

            List<String> tagNames = htmlFormBinder.bindUrl(url, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
            Assertions.assertThat(tagNames).containsExactly("div");

            List<String> references = htmlFormBinder.bindUrl(url, "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
            Assertions.assertThat(references).containsExactly("path/to/resource", url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlObjectWithIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            URL url = new URL(server.getUrl());
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
            Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlObjectWithGroupAndIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            URL url = new URL(server.getUrl());
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
            Assertions.assertThat(bindedElements2.get(0).getAbsoluteAttribute("href")).isEqualTo(url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlObjectAndProcessWithIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            URL url = new URL(server.getUrl());

            List<String> tagNames = htmlFormBinder.bindUrl(url, "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
            Assertions.assertThat(tagNames).containsExactly("div");

            List<String> references = htmlFormBinder.bindUrl(url, "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
            Assertions.assertThat(references).containsExactly("path/to/resource", url + "/path/to/resource");
        } finally {
            server.stop();
        }
    }

    /**
     * {@link HtmlFormBinder} class test.
     *
     * @throws IOException IO exception
     */
    @Test
    public void bindUrlObjectAndProcessWithGroupAndIdTest() throws IOException {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form group='group' id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='d' lookup='.divclass'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='a' lookup='a'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        NanoHttpdImpl server = new NanoHttpdImpl(html);
        try {
            server.start();
            URL url = new URL(server.getUrl());

            List<String> tagNames = htmlFormBinder.bindUrl(url, "group", "id", new TagNameDocumentProcessor(htmlFormBinder, "d"));
            Assertions.assertThat(tagNames).containsExactly("div");

            List<String> references = htmlFormBinder.bindUrl(url, "group", "id", new ReferenceDocumentProcessor(htmlFormBinder, "a"));
            Assertions.assertThat(references).containsExactly("path/to/resource", url + "/path/to/resource");
        } finally {
            server.stop();
        }
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
        String html = createHtml();
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
        String html = createHtml();
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
        String html = createHtml();
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
        String html = createHtml();
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
        html += "<a href='path/to/resource'>link</a>";
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
