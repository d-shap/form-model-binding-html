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

    @Test
    public void readmeExample01Test() throws IOException {
        FormDefinitions formDefinitions = TestHelper.loadFormDefinitions();
        HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
        String html = TestHelper.loadHtml("readmeExample-01.html");

        Document document = formBinder.bindHtml(html, "readme-example-01", "p-extractor");
        List<Element> elements = formBinder.getElementsWithId(document, "p-element");
        List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
        Assertions.assertThat(bindedElements).hasSize(3);
        Assertions.assertThat(bindedElements.get(0).getText()).isEqualTo("Some text.");
        Assertions.assertThat(bindedElements.get(1).getText()).isEqualTo("Some other text.");
        Assertions.assertThat(bindedElements.get(2).getText()).isEqualTo("Some more text.");
    }

}
