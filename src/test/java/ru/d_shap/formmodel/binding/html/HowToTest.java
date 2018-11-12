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
import ru.d_shap.formmodel.definition.model.FormDefinitions;

/**
 * How To examples.
 *
 * @author Dmitry Shapovalov
 */
public final class HowToTest {

    /**
     * Test class constructor.
     */
    public HowToTest() {
        super();
    }

    /**
     * How To example.
     */
    @Test
    public void bindElements01Form01Test() {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("bindElements-01.html");

        Document document = formBinder.bindHtml(html, "bindElements-01", "form-01");
        List<Element> elements = formBinder.getElementsWithId(document, "elementId");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(3);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Some text.");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Some other text.");
        Assertions.assertThat(bindedElements.get(2).getText()).isEqualTo("Some more text.");
    }

    /**
     * How To example.
     */
    @Test
    public void bindElementsWithSameId01Form01Test() {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("bindElementsWithSameId-01.html");

        Document document = formBinder.bindHtml(html, "bindElementsWithSameId-01", "form-01");
        List<Element> elements = formBinder.getElementsWithId(document, "elementId");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(6);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Header 1");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Header 1.1");
        Assertions.assertThat(bindedElements.get(2).getText()).isEqualTo("Header 1.2");
        Assertions.assertThat(bindedElements.get(3).getText()).isEqualTo("Some text.");
        Assertions.assertThat(bindedElements.get(4).getText()).isEqualTo("Some other text.");
        Assertions.assertThat(bindedElements.get(5).getText()).isEqualTo("Some more text.");
    }

    /**
     * How To example.
     */
    @Test
    public void bindElementsWithSameId01Form02Test() {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("bindElementsWithSameId-01.html");

        Document document = formBinder.bindHtml(html, "bindElementsWithSameId-01", "form-02");
        List<Element> elements = formBinder.getElementsWithId(document, "elementId");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(6);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Header 1");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Header 1.1");
        Assertions.assertThat(bindedElements.get(2).getText()).isEqualTo("Some text.");
        Assertions.assertThat(bindedElements.get(3).getText()).isEqualTo("Some other text.");
        Assertions.assertThat(bindedElements.get(4).getText()).isEqualTo("Header 1.2");
        Assertions.assertThat(bindedElements.get(5).getText()).isEqualTo("Some more text.");
    }

    /**
     * How To example.
     */
    @Test
    public void bindElementsWithClass01Form01Test() {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("bindElementsWithClass-01.html");

        Document document = formBinder.bindHtml(html, "bindElementsWithClass-01", "form-01");
        List<Element> elements = formBinder.getElementsWithId(document, "elementId");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(2);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Some other text.");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Some more text.");
    }

    /**
     * How To example.
     */
    @Test
    public void bindChildElements01Form01Test() {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("bindChildElements-01.html");

        Document document = formBinder.bindHtml(html, "bindChildElements-01", "form-01");
        List<Element> elements = formBinder.getElementsWithId(document, "elementId");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(3);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Row 1 column 2");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Row 2 column 2");
        Assertions.assertThat(bindedElements.get(2).getText()).isEqualTo("Row 3 column 2");
    }

    /**
     * How To example.
     */
    @Test
    public void bindChildElements01Form02Test() {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("bindChildElements-01.html");

        Document document = formBinder.bindHtml(html, "bindChildElements-01", "form-02");
        List<Element> elements = formBinder.getElementsWithId(document, "elementId");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(3);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Row 1 column 2");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Row 2 column 2");
        Assertions.assertThat(bindedElements.get(2).getText()).isEqualTo("Row 3 column 2");
    }

}
