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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.formmodel.binding.FormInstanceBinder;
import ru.d_shap.formmodel.binding.model.BindedAttribute;
import ru.d_shap.formmodel.binding.model.BindedElement;
import ru.d_shap.formmodel.binding.model.BindedForm;
import ru.d_shap.formmodel.binding.model.BindingSource;
import ru.d_shap.formmodel.definition.model.AttributeDefinition;
import ru.d_shap.formmodel.definition.model.ElementDefinition;
import ru.d_shap.formmodel.definition.model.FormDefinition;

/**
 * Form instance binder HTML implementation.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlFormInstanceBinder implements FormInstanceBinder {

    /**
     * Create new object.
     */
    public HtmlFormInstanceBinder() {
        super();
    }

    @Override
    public void preBind(final BindingSource bindingSource, final FormDefinition formDefinition) {
        // Ignore
    }

    @Override
    public void postBind(final BindingSource bindingSource, final FormDefinition formDefinition, final Document document) {
        // Ignore
    }

    @Override
    public BindedForm bindFormDefinition(final BindingSource bindingSource, final BindedForm lastBindedForm, final BindedElement lastBindedElement, final Element parentElement, final FormDefinition formDefinition) {
        org.jsoup.nodes.Document document;
        if (lastBindedForm == null) {
            document = ((HtmlBindingSource) bindingSource).getDocument();
        } else {
            document = ((HtmlBindedForm) lastBindedForm).getDocument();
        }
        return new HtmlBindedFormImpl(document);
    }

    @Override
    public List<BindedElement> bindElementDefinition(final BindingSource bindingSource, final BindedForm lastBindedForm, final BindedElement lastBindedElement, final Element parentElement, final ElementDefinition elementDefinition) {
        org.jsoup.nodes.Element element;
        if (lastBindedElement == null) {
            element = ((HtmlBindedForm) lastBindedForm).getDocument();
        } else {
            element = ((HtmlBindedElement) lastBindedElement).getElement();
        }
        List<org.jsoup.nodes.Element> childElements = element.select(elementDefinition.getLookup());
        List<BindedElement> result = new ArrayList<>(childElements.size());
        for (org.jsoup.nodes.Element childElement : childElements) {
            HtmlBindedElement htmlBindedElement = new HtmlBindedElementImpl(childElement);
            result.add(htmlBindedElement);
        }
        return result;
    }

    @Override
    public BindedAttribute bindAttributeDefinition(final BindingSource bindingSource, final BindedForm lastBindedForm, final BindedElement lastBindedElement, final Element parentElement, final AttributeDefinition attributeDefinition) {
        org.jsoup.nodes.Element element = ((HtmlBindedElement) lastBindedElement).getElement();
        String attributeName = attributeDefinition.getLookup();
        if (element.attributes().hasKey(attributeName)) {
            return new HtmlBindedAttributeImpl(element, attributeName);
        } else {
            return null;
        }
    }

}
