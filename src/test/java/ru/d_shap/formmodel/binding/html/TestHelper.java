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

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ru.d_shap.formmodel.XmlDocumentBuilder;
import ru.d_shap.formmodel.definition.loader.xml.FormXmlDefinitionsElementLoader;
import ru.d_shap.formmodel.definition.model.FormDefinition;
import ru.d_shap.formmodel.definition.model.FormDefinitions;

/**
 * Helper class for tests.
 *
 * @author Dmitry Shapovalov
 */
public final class TestHelper {

    private TestHelper() {
        super();
    }

    /**
     * Create container for all form definitions.
     *
     * @param xmls the source XML documents.
     *
     * @return container for all form definitions.
     */
    public static FormDefinitions createFormDefinitionsFromXml(final String... xmls) {
        XmlDocumentBuilder xmlDocumentBuilder = XmlDocumentBuilder.getDocumentBuilder();
        List<FormDefinition> formDefinitions1 = new ArrayList<>();
        for (String xml : xmls) {
            Reader reader = new StringReader(xml);
            InputSource inputSource = new InputSource(reader);
            Document document = xmlDocumentBuilder.parse(inputSource);
            FormXmlDefinitionsElementLoader formXmlDefinitionsElementLoader = new FormXmlDefinitionsElementLoader(document.getDocumentElement(), "source");
            List<FormDefinition> formDefinitions2 = formXmlDefinitionsElementLoader.load();
            formDefinitions1.addAll(formDefinitions2);
        }
        FormDefinitions formDefinitions = new FormDefinitions();
        formDefinitions.addFormDefinitions(formDefinitions1);
        return formDefinitions;
    }

    /**
     * Create the HTML form binder.
     *
     * @param xmls the source XML documents.
     *
     * @return the HTML form binder.
     */
    public static HtmlFormBinder createHtmlFormBinder(final String... xmls) {
        FormDefinitions formDefinitions = createFormDefinitionsFromXml(xmls);
        return new HtmlFormBinder(formDefinitions);
    }

}
