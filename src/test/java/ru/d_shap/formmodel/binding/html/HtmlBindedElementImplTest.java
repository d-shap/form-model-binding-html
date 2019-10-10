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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.formmodel.InputSourceException;

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
        xml += "<ns1:element id='someid' lookup='#someid' type='optional+'>";
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

        List<Element> elements3 = htmlFormBinder.getElementsWithId(document, "someid");
        List<HtmlBindedElement> bindedElements3 = htmlFormBinder.getBindedElements(elements3);
        Assertions.assertThat(bindedElements3).hasSize(1);
        Assertions.assertThat(bindedElements3.get(0).getElement()).isNotNull();
        Assertions.assertThat(bindedElements3.get(0).getElement().tag().getName()).isEqualTo("span");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void cssSelectorTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='blockclass' lookup='.blockclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='someid' lookup='#someid' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "pclass");
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(2);
        Assertions.assertThat(bindedElements1.get(0).cssSelector()).isEqualTo("html > body > p.pclass.xx:nth-child(1)");
        Assertions.assertThat(bindedElements1.get(1).cssSelector()).isEqualTo("html > body > p.pclass.xx:nth-child(2)");

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "blockclass");
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(2);
        Assertions.assertThat(bindedElements2.get(0).cssSelector()).isEqualTo("html > body > div.blockclass.xx");
        Assertions.assertThat(bindedElements2.get(1).cssSelector()).isEqualTo("html > body > div:nth-child(4) > span.blockclass.xx");

        List<Element> elements3 = htmlFormBinder.getElementsWithId(document, "someid");
        List<HtmlBindedElement> bindedElements3 = htmlFormBinder.getBindedElements(elements3);
        Assertions.assertThat(bindedElements3).hasSize(1);
        Assertions.assertThat(bindedElements3.get(0).cssSelector()).isEqualTo("#someid");
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
        xml += "<ns1:element id='someid' lookup='#someid' type='optional+'>";
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

        List<Element> elements3 = htmlFormBinder.getElementsWithId(document, "someid");
        List<HtmlBindedElement> bindedElements3 = htmlFormBinder.getBindedElements(elements3);
        Assertions.assertThat(bindedElements3).hasSize(1);
        Assertions.assertThat(bindedElements3.get(0).getOwnText()).isEqualTo("Span in Div 1.");
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
        xml += "<ns1:element id='someid' lookup='#someid' type='optional+'>";
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

        List<Element> elements3 = htmlFormBinder.getElementsWithId(document, "someid");
        List<HtmlBindedElement> bindedElements3 = htmlFormBinder.getBindedElements(elements3);
        Assertions.assertThat(bindedElements3).hasSize(1);
        Assertions.assertThat(bindedElements3.get(0).getText()).isEqualTo("Span in Div 1.");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void hasAttributeTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='ref' lookup='a' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();

        Document document = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements1 = htmlFormBinder.getElementsWithId(document, "pclass");
        List<HtmlBindedElement> bindedElements1 = htmlFormBinder.getBindedElements(elements1);
        Assertions.assertThat(bindedElements1).hasSize(2);
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("style")).isTrue();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("style.padding")).isTrue();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("style.display")).isTrue();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("style.border")).isFalse();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("class")).isTrue();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("class.pclass")).isTrue();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("class.xx")).isTrue();
        Assertions.assertThat(bindedElements1.get(0).hasAttribute("class.yy")).isFalse();
        Assertions.assertThat(bindedElements1.get(1).hasAttribute("style")).isFalse();
        Assertions.assertThat(bindedElements1.get(1).hasAttribute("style.padding")).isFalse();
        Assertions.assertThat(bindedElements1.get(1).hasAttribute("class")).isTrue();
        Assertions.assertThat(bindedElements1.get(1).hasAttribute("class.pclass")).isTrue();
        Assertions.assertThat(bindedElements1.get(1).hasAttribute("class.xx")).isTrue();
        Assertions.assertThat(bindedElements1.get(1).hasAttribute("class.yy")).isFalse();

        List<Element> elements2 = htmlFormBinder.getElementsWithId(document, "ref");
        List<HtmlBindedElement> bindedElements2 = htmlFormBinder.getBindedElements(elements2);
        Assertions.assertThat(bindedElements2).hasSize(1);
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("href")).isTrue();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("href.wrongvalue")).isFalse();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("style")).isTrue();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("style.color")).isTrue();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("style.padding")).isFalse();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("class")).isFalse();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("class.pclass")).isFalse();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("wrongprop")).isFalse();
        Assertions.assertThat(bindedElements2.get(0).hasAttribute("wrongprop.xx")).isFalse();
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getAttributeTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='ref' lookup='a' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();

        Document document1 = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements11 = htmlFormBinder.getElementsWithId(document1, "pclass");
        List<HtmlBindedElement> bindedElements11 = htmlFormBinder.getBindedElements(elements11);
        Assertions.assertThat(bindedElements11).hasSize(2);
        Assertions.assertThat(bindedElements11.get(0).getAttribute("style")).isEqualTo("padding: 0px; display: none;");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("style.padding")).isEqualTo("0px");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("style.display")).isEqualTo("none");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("style.border")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("class")).isEqualTo("pclass xx");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("class.pclass")).isEqualTo("pclass");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("class.xx")).isEqualTo("xx");
        Assertions.assertThat(bindedElements11.get(0).getAttribute("class.yy")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAttribute("class")).isEqualTo("pclass xx");
        Assertions.assertThat(bindedElements11.get(1).getAttribute("class.pclass")).isEqualTo("pclass");
        Assertions.assertThat(bindedElements11.get(1).getAttribute("class.xx")).isEqualTo("xx");
        Assertions.assertThat(bindedElements11.get(1).getAttribute("class.yy")).isEqualTo("");

        List<Element> elements12 = htmlFormBinder.getElementsWithId(document1, "ref");
        List<HtmlBindedElement> bindedElements12 = htmlFormBinder.getBindedElements(elements12);
        Assertions.assertThat(bindedElements12).hasSize(1);
        Assertions.assertThat(bindedElements12.get(0).getAttribute("href")).isEqualTo("linkref/id");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("href.wrongvalue")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("style")).isEqualTo("color: blue;");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("style.color")).isEqualTo("blue");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("class")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("wrongprop")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAttribute("wrongprop.xx")).isEqualTo("");

        Document document2 = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://foo.com", "id");

        List<Element> elements21 = htmlFormBinder.getElementsWithId(document2, "pclass");
        List<HtmlBindedElement> bindedElements21 = htmlFormBinder.getBindedElements(elements21);
        Assertions.assertThat(bindedElements21).hasSize(2);
        Assertions.assertThat(bindedElements21.get(0).getAttribute("style")).isEqualTo("padding: 0px; display: none;");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("style.padding")).isEqualTo("0px");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("style.display")).isEqualTo("none");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("style.border")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("class")).isEqualTo("pclass xx");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("class.pclass")).isEqualTo("pclass");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("class.xx")).isEqualTo("xx");
        Assertions.assertThat(bindedElements21.get(0).getAttribute("class.yy")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAttribute("class")).isEqualTo("pclass xx");
        Assertions.assertThat(bindedElements21.get(1).getAttribute("class.pclass")).isEqualTo("pclass");
        Assertions.assertThat(bindedElements21.get(1).getAttribute("class.xx")).isEqualTo("xx");
        Assertions.assertThat(bindedElements21.get(1).getAttribute("class.yy")).isEqualTo("");

        List<Element> elements22 = htmlFormBinder.getElementsWithId(document2, "ref");
        List<HtmlBindedElement> bindedElements22 = htmlFormBinder.getBindedElements(elements22);
        Assertions.assertThat(bindedElements22).hasSize(1);
        Assertions.assertThat(bindedElements22.get(0).getAttribute("href")).isEqualTo("linkref/id");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("href.wrongvalue")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("style")).isEqualTo("color: blue;");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("style.color")).isEqualTo("blue");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("class")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("wrongprop")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAttribute("wrongprop.xx")).isEqualTo("");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void getAbsoluteAttributeTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='ref' lookup='a' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();

        Document document1 = htmlFormBinder.bindHtml(html, "id");

        List<Element> elements11 = htmlFormBinder.getElementsWithId(document1, "pclass");
        List<HtmlBindedElement> bindedElements11 = htmlFormBinder.getBindedElements(elements11);
        Assertions.assertThat(bindedElements11).hasSize(2);
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("style.display")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("style.border")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("class")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("class.xx")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(0).getAbsoluteAttribute("class.yy")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAbsoluteAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAbsoluteAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAbsoluteAttribute("class")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAbsoluteAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAbsoluteAttribute("class.xx")).isEqualTo("");
        Assertions.assertThat(bindedElements11.get(1).getAbsoluteAttribute("class.yy")).isEqualTo("");

        List<Element> elements12 = htmlFormBinder.getElementsWithId(document1, "ref");
        List<HtmlBindedElement> bindedElements12 = htmlFormBinder.getBindedElements(elements12);
        Assertions.assertThat(bindedElements12).hasSize(1);
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("href")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("href.wrongvalue")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("style.color")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("class")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("wrongprop")).isEqualTo("");
        Assertions.assertThat(bindedElements12.get(0).getAbsoluteAttribute("wrongprop.xx")).isEqualTo("");

        Document document2 = htmlFormBinder.bindHtmlWithBaseUrl(html, "http://foo.com", "id");

        List<Element> elements21 = htmlFormBinder.getElementsWithId(document2, "pclass");
        List<HtmlBindedElement> bindedElements21 = htmlFormBinder.getBindedElements(elements21);
        Assertions.assertThat(bindedElements21).hasSize(2);
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("style.display")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("style.border")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("class")).isEqualTo("http://foo.com/pclass xx");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("class.xx")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(0).getAbsoluteAttribute("class.yy")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAbsoluteAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAbsoluteAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAbsoluteAttribute("class")).isEqualTo("http://foo.com/pclass xx");
        Assertions.assertThat(bindedElements21.get(1).getAbsoluteAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAbsoluteAttribute("class.xx")).isEqualTo("");
        Assertions.assertThat(bindedElements21.get(1).getAbsoluteAttribute("class.yy")).isEqualTo("");

        List<Element> elements22 = htmlFormBinder.getElementsWithId(document2, "ref");
        List<HtmlBindedElement> bindedElements22 = htmlFormBinder.getBindedElements(elements22);
        Assertions.assertThat(bindedElements22).hasSize(1);
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("href")).isEqualTo("http://foo.com/linkref/id");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("href.wrongvalue")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("style")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("style.color")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("style.padding")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("class")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("class.pclass")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("wrongprop")).isEqualTo("");
        Assertions.assertThat(bindedElements22.get(0).getAbsoluteAttribute("wrongprop.xx")).isEqualTo("");
    }

    /**
     * {@link HtmlBindedElementImpl} class test.
     */
    @Test
    public void htmlCssParserFailTest() {
        String xml = "<?xml version='1.0'?>\n";
        xml += "<ns1:form id='id' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>";
        xml += "<ns1:element id='pclass' lookup='p.pclass' type='optional+'>";
        xml += "</ns1:element>";
        xml += "<ns1:element id='ref' lookup='a' type='optional+'>";
        xml += "</ns1:element>";
        xml += "</ns1:form>";
        HtmlFormBinder htmlFormBinder = TestHelper.createHtmlFormBinder(xml);
        String html = createHtml();
        Document document = htmlFormBinder.bindHtml(html, "id");
        List<Element> elements = htmlFormBinder.getElementsWithId(document, "pclass");
        List<HtmlBindedElement> bindedElements = htmlFormBinder.getBindedElements(elements);
        try {
            HtmlBindedElement bindedElement = new HtmlBindedElementImpl(bindedElements.get(0).getElement(), new HtmlCssParserPropertiesError());
            bindedElement.getAttribute("style.padding");
            Assertions.fail("HtmlBindedElementImpl test fail");
        } catch (InputSourceException ex) {
            Assertions.assertThat(ex).hasCause(IOException.class);
            Assertions.assertThat(ex).hasCauseMessage("IOException!!!");
        }
    }

    private String createHtml() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page</title>";
        html += "</head>";
        html += "<body>";
        html += "<p class='pclass xx' style='padding: 0px; display: none;'>";
        html += "This is a ";
        html += "<a href='linkref/id' style='color: blue;'>link</a>";
        html += " to somewhere.";
        html += "</p>";
        html += "<p class='pclass xx'>";
        html += "<div class='blockclass xx'>";
        html += "<span id='someid'>Span in Div 1.</span>";
        html += "</div>";
        html += "<div>";
        html += "<span class='blockclass xx'>Span in Div 2.</span>";
        html += "</div>";
        html += "</p>";
        html += "</body>";
        html += "</html>";
        return html;
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class HtmlCssParserPropertiesError implements HtmlCssParser {

        HtmlCssParserPropertiesError() {
            super();
        }

        @Override
        public Map<String, String> getCssProperties(final InputSource inputSource) throws IOException {
            throw new IOException("IOException!!!");
        }

    }

}
