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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ru.d_shap.formmodel.InputSourceException;
import ru.d_shap.formmodel.XmlDocumentBuilder;
import ru.d_shap.formmodel.definition.loader.FormDefinitionsLoader;
import ru.d_shap.formmodel.definition.loader.xml.FormXmlDefinitionsElementLoader;
import ru.d_shap.formmodel.definition.loader.xml.FormXmlDefinitionsFileLoader;
import ru.d_shap.formmodel.definition.model.FormDefinition;
import ru.d_shap.formmodel.definition.model.FormDefinitions;

/**
 * Helper class for tests.
 *
 * @author Dmitry Shapovalov
 */
public final class TestHelper {

    public static final String ENCODING = "UTF-8";

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
     * Load the form definitions.
     *
     * @return the form definitions.
     */
    public static FormDefinitions loadFormDefinitions() {
        FormDefinitions formDefinitions = new FormDefinitions();
        URL url = TestHelper.class.getClassLoader().getResource("anchor.txt");
        File parentDirectory = new File(url.getFile()).getParentFile();
        FormDefinitionsLoader formDefinitionsLoader = new FormXmlDefinitionsFileLoader(parentDirectory);
        formDefinitionsLoader.load(formDefinitions);
        return formDefinitions;
    }

    /**
     * Load the HTML from resource with the specified resource name.
     *
     * @param name the specified resource name.
     *
     * @return the loaded HTML.
     */
    public static String loadHtml(final String name) {
        try {
            InputStream inputStream = TestHelper.class.getClassLoader().getResourceAsStream(name);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            byte[] buffer = new byte[1000];
            while (true) {
                read = inputStream.read(buffer);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(buffer, 0, read);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return new String(bytes, ENCODING);
        } catch (IOException ex) {
            throw new InputSourceException(ex);
        }
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
