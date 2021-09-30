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

import java.net.URL;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ru.d_shap.fm.formmodel.binding.FormBinder;
import ru.d_shap.fm.formmodel.definition.model.FormDefinitions;
import ru.d_shap.fm.formmodel.document.DocumentLookup;
import ru.d_shap.fm.formmodel.document.DocumentProcessor;

/**
 * The HTML form binder.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlFormBinder {

    private final FormBinder _formBinder;

    private final DocumentLookup _documentLookup;

    /**
     * Create new object.
     *
     * @param formDefinitions container for all form definitions.
     */
    public HtmlFormBinder(final FormDefinitions formDefinitions) {
        super();
        _formBinder = new FormBinder(formDefinitions, new HtmlFormInstanceBinder());
        _documentLookup = DocumentLookup.getDocumentLookup();
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
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html), id);
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
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html), group, id);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html              the source HTML string.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindHtml(final String html, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html), id, documentProcessor);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html              the source HTML string.
     * @param group             the specified form's group.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindHtml(final String html, final String group, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html), group, id, documentProcessor);
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
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html, baseUrl), id);
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
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html, baseUrl), group, id);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html              the source HTML string.
     * @param baseUrl           the base URL to resolve relative links.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindHtmlWithBaseUrl(final String html, final String baseUrl, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html, baseUrl), id, documentProcessor);
    }

    /**
     * Bind the specified form definition with the source HTML string.
     *
     * @param html              the source HTML string.
     * @param baseUrl           the base URL to resolve relative links.
     * @param group             the specified form's group.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindHtmlWithBaseUrl(final String html, final String baseUrl, final String group, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlStringBindingSourceImpl(html, baseUrl), group, id, documentProcessor);
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
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), id);
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
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), group, id);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url               the source URL.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindUrl(final String url, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), id, documentProcessor);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url               the source URL.
     * @param group             the specified form's group.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindUrl(final String url, final String group, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), group, id, documentProcessor);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url the source URL.
     * @param id  the specified form's ID.
     *
     * @return the binding result.
     */
    public Document bindUrl(final URL url, final String id) {
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), id);
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
    public Document bindUrl(final URL url, final String group, final String id) {
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), group, id);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url               the source URL.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindUrl(final URL url, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), id, documentProcessor);
    }

    /**
     * Bind the specified form definition with the source URL.
     *
     * @param url               the source URL.
     * @param group             the specified form's group.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindUrl(final URL url, final String group, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlUrlBindingSourceImpl(url), group, id, documentProcessor);
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
        return _formBinder.bind(new HtmlDocumentBindingSourceImpl(document), id);
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
    public Document bindDocument(final org.jsoup.nodes.Document document, final String group, final String id) {
        return _formBinder.bind(new HtmlDocumentBindingSourceImpl(document), group, id);
    }

    /**
     * Bind the specified form definition with the source HTML document.
     *
     * @param document          the source HTML document.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindDocument(final org.jsoup.nodes.Document document, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlDocumentBindingSourceImpl(document), id, documentProcessor);
    }

    /**
     * Bind the specified form definition with the source HTML document.
     *
     * @param document          the source HTML document.
     * @param group             the specified form's group.
     * @param id                the specified form's ID.
     * @param documentProcessor the document processor.
     * @param <T>               the generic type of the result of the document processing.
     *
     * @return the result of the document processing.
     */
    public <T> T bindDocument(final org.jsoup.nodes.Document document, final String group, final String id, final DocumentProcessor<T> documentProcessor) {
        return _formBinder.bind(new HtmlDocumentBindingSourceImpl(document), group, id, documentProcessor);
    }

    /**
     * Perform lookup and return the XML elements found.
     *
     * @param node   the source node.
     * @param lookup the XPath lookup expression.
     *
     * @return the XML elements found.
     */
    public List<Element> getElements(final Node node, final String lookup) {
        return _documentLookup.getElements(node, lookup);
    }

    /**
     * Perform lookup and return the XML elements with the specified ID.
     *
     * @param node the source node.
     * @param id   the specified ID.
     *
     * @return the XML elements found.
     */
    public List<Element> getElementsWithId(final Node node, final String id) {
        return _documentLookup.getElementsWithId(node, id);
    }

    /**
     * Perform lookup and return the XML elements with the specified attribute value for the specified attribute name.
     *
     * @param node           the source node.
     * @param attributeName  the specified attribute name.
     * @param attributeValue the specified attribute value.
     *
     * @return the XML elements found.
     */
    public List<Element> getElementsWithAttribute(final Node node, final String attributeName, final String attributeValue) {
        return _documentLookup.getElementsWithAttribute(node, attributeName, attributeValue);
    }

    /**
     * Obtain the binded elements from the specified XML elements.
     *
     * @param elements the specified XML elements.
     *
     * @return the binded elements.
     */
    public List<HtmlBindedElement> getBindedElements(final List<Element> elements) {
        return _documentLookup.getBindedElements(elements, HtmlBindedElement.class);
    }

    /**
     * Obtain the binded attributes from the specified XML elements.
     *
     * @param elements the specified XML elements.
     *
     * @return the binded attributes.
     */
    public List<HtmlBindedAttribute> getBindedAttributes(final List<Element> elements) {
        return _documentLookup.getBindedAttributes(elements, HtmlBindedAttribute.class);
    }

}
