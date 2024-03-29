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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.fm.formmodel.binding.FormInstanceBinder;
import ru.d_shap.fm.formmodel.binding.model.BindedAttribute;
import ru.d_shap.fm.formmodel.binding.model.BindedElement;
import ru.d_shap.fm.formmodel.binding.model.BindedForm;
import ru.d_shap.fm.formmodel.binding.model.BindingSource;
import ru.d_shap.fm.formmodel.definition.model.AttributeDefinition;
import ru.d_shap.fm.formmodel.definition.model.ElementDefinition;
import ru.d_shap.fm.formmodel.definition.model.FormDefinition;

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
        org.jsoup.nodes.Document document = ((HtmlBindingSource) bindingSource).getDocument();
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
        String lookup = elementDefinition.getLookup();
        List<org.jsoup.nodes.Element> childElements = element.select(lookup);
        List<BindedElement> result = new ArrayList<>(childElements.size());
        for (org.jsoup.nodes.Element childElement : childElements) {
            HtmlBindedElement htmlBindedElement = new HtmlBindedElementImpl(childElement);
            result.add(htmlBindedElement);
        }
        return result;
    }

    @Override
    public BindedAttribute bindAttributeDefinition(final BindingSource bindingSource, final BindedForm lastBindedForm, final BindedElement lastBindedElement, final Element parentElement, final AttributeDefinition attributeDefinition) {
        String attributeName = attributeDefinition.getLookup();
        if (((HtmlBindedElement) lastBindedElement).hasAttribute(attributeName)) {
            String attributeValue = ((HtmlBindedElement) lastBindedElement).getAttribute(attributeName);
            String absoluteAttributeValue = ((HtmlBindedElement) lastBindedElement).getAbsoluteAttribute(attributeName);
            return new HtmlBindedAttributeImpl(attributeName, attributeValue, absoluteAttributeValue);
        } else {
            return null;
        }
    }

}
