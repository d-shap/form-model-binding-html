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

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.d_shap.formmodel.binding.FormBinder;
import ru.d_shap.formmodel.binding.instance.DocumentLookup;
import ru.d_shap.formmodel.binding.model.BindedElement;
import ru.d_shap.formmodel.definition.model.FormDefinitions;

/**
 * The HTML form binder.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlFormBinder {

    private final FormBinder _formBinder;

    /**
     * Create new object.
     *
     * @param formDefinitions container for all form definitions.
     */
    public HtmlFormBinder(final FormDefinitions formDefinitions) {
        super();
        _formBinder = new FormBinder(formDefinitions, new HtmlFormInstanceBinder());
    }

    /**
     * Bind the specified form definition with the specified binding source.
     *
     * @param html the specified binding source.
     * @param id   the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindHtml(final String html, final String id) {
        return _formBinder.bind(new HtmlStringBindingSource(html), id);
    }

    /**
     * Bind the specified form definition with the specified binding source.
     *
     * @param html  the specified binding source.
     * @param group the specified form's group.
     * @param id    the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindHtml(final String html, final String group, final String id) {
        return _formBinder.bind(new HtmlStringBindingSource(html), group, id);
    }

    /**
     * Bind the specified form definition with the specified binding source.
     *
     * @param url the specified binding source.
     * @param id  the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindUrl(final String url, final String id) {
        return _formBinder.bind(new HtmlUrlBindingSource(url), id);
    }

    /**
     * Bind the specified form definition with the specified binding source.
     *
     * @param url   the specified binding source.
     * @param group the specified form's group.
     * @param id    the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindUrl(final String url, final String group, final String id) {
        return _formBinder.bind(new HtmlUrlBindingSource(url), group, id);
    }

    /**
     * Perform XPath lookup and return the elements found.
     *
     * @param document the source document.
     * @param lookup   the XPath expression.
     *
     * @return the elements found.
     *
     * @throws XPathExpressionException XPath expression exception.
     */
    public static List<Element> lookupElements(final Document document, final String lookup) throws XPathExpressionException {
        return DocumentLookup.lookupElements(document, lookup);
    }

    /**
     * Perform XPath lookup and return the binded elements found.
     *
     * @param document the source document.
     * @param lookup   the XPath expression.
     *
     * @return the binded elements found.
     *
     * @throws XPathExpressionException XPath expression exception.
     */
    public static List<HtmlBindedElement> lookupBindedElements(final Document document, final String lookup) throws XPathExpressionException {
        List<BindedElement> bindedElements = DocumentLookup.lookupBindedElements(document, lookup);
        List<HtmlBindedElement> htmlBindedElements = new ArrayList<>();
        for (BindedElement bindedElement : bindedElements) {
            if (bindedElement instanceof HtmlBindedElement) {
                htmlBindedElements.add((HtmlBindedElement) bindedElement);
            }
        }
        return htmlBindedElements;
    }

}
