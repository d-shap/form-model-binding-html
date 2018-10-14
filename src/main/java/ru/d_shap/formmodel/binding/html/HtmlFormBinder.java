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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ru.d_shap.formmodel.binding.FormBinder;
import ru.d_shap.formmodel.definition.model.FormDefinitions;
import ru.d_shap.formmodel.document.DocumentLookup;

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
     * Bind the specified form definition with the source HTML string.
     *
     * @param html the source HTML string.
     * @param id   the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindHtml(final String html, final String id) {
        return _formBinder.bind(new HtmlStringBindingSource(html), id);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html  the source HTML string.
     * @param group the specified form's group.
     * @param id    the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindHtml(final String html, final String group, final String id) {
        return _formBinder.bind(new HtmlStringBindingSource(html), group, id);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html    the source HTML string.
     * @param baseUrl the base URL to resolve relative links.
     * @param id      the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindHtmlWithBaseUrl(final String html, final String baseUrl, final String id) {
        return _formBinder.bind(new HtmlStringBindingSource(html, baseUrl), id);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html    the source HTML string.
     * @param baseUrl the base URL to resolve relative links.
     * @param group   the specified form's group.
     * @param id      the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindHtmlWithBaseUrl(final String html, final String baseUrl, final String group, final String id) {
        return _formBinder.bind(new HtmlStringBindingSource(html, baseUrl), group, id);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url the source URL.
     * @param id  the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindUrl(final String url, final String id) {
        return _formBinder.bind(new HtmlUrlBindingSource(url), id);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url   the source URL.
     * @param group the specified form's group.
     * @param id    the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindUrl(final String url, final String group, final String id) {
        return _formBinder.bind(new HtmlUrlBindingSource(url), group, id);
    }

    /**
     * Bind the specified form definition with the source HTML document.
     *
     * @param document the source HTML document.
     * @param id       the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindDocument(final org.jsoup.nodes.Document document, final String id) {
        return _formBinder.bind(new HtmlDocumentBindingSource(document), id);
    }

    /**
     * Bind the specified form definition with the source HTML document.
     *
     * @param document the source HTML document.
     * @param group    the specified form's group.
     * @param id       the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindUrl(final org.jsoup.nodes.Document document, final String group, final String id) {
        return _formBinder.bind(new HtmlDocumentBindingSource(document), group, id);
    }

    /**
     * Perform XPath lookup and return the XML elements found.
     *
     * @param node   the source node.
     * @param lookup the XPath lookup expression.
     *
     * @return the XML elements found.
     */
    public static List<Element> getElements(final Node node, final String lookup) {
        return DocumentLookup.getElements(node, lookup);
    }

    /**
     * Perform XPath lookup and return the XML elements with the specified ID.
     *
     * @param node the source node.
     * @param id   the specified ID.
     *
     * @return the XML elements with the specified ID.
     */
    public static List<Element> getElementsWithId(final Node node, final String id) {
        return DocumentLookup.getElementsWithId(node, id);
    }

    /**
     * Obtain the binded elements from the specified XML elements.
     *
     * @param elements the specified XML elements.
     *
     * @return the binded elements.
     */
    public static List<HtmlBindedElement> getBindedElements(final List<Element> elements) {
        return DocumentLookup.getBindedElements(elements, HtmlBindedElement.class);
    }

    /**
     * Obtain the binded attributes from the specified XML elements.
     *
     * @param elements the specified XML elements.
     *
     * @return the binded attributes.
     */
    public static List<HtmlBindedAttribute> getBindedAttributes(final List<Element> elements) {
        return DocumentLookup.getBindedAttributes(elements, HtmlBindedAttribute.class);
    }

}
